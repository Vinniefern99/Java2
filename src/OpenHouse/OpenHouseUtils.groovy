package OpenHouse

import java.text.*
/*
import com.dozer.groovy.logger.*;
//import com.trulia.dm.dozer.rets.connector.TruliaRetsConnection;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
*/
/**
 * parses out open home times from an input string 
 * @author jason
 * @since 2012-05-15
 */

class OpenHouseUtils {
	//set logger param. String will be output to any error shown
	def static final logger = org.apache.log4j.Logger.getLogger("OpenHouseUtils.groovy" );
	
	/**
	 * This is a data structure of regex's with all the possible
	 * time formats we may encounter
	 */
	public static rules = [
		//return pattern if it's already in military time
		[/^\d{2}:\d{2}-\d{2}:\d{2}$/,   {input -> input}],
		//return pattern if it's already in military time
		[/^\d{2}:\d{2}$/,     {input -> input}],
		//removes seconds from 
		[/\d{2}:\d{2}:\d{2}-\d{2}:\d{2}:\d{2}/,           		{input -> removeSecondsFromTwoTimestamps(input)}],
		//deletes the trailing seconds and returns. Assumes it's already in military time
		[/\d{2}:\d{2}:\d{2}/,           {input -> removeSecondsFromTimestamp(input)}],
		//match  number of possible options if both times in same string
		[/\d{1,2}(?::\d{1,2})?(?:[a|p][m])?-\d{1,2}(?::\d{1,2})?(?:[a|p][m])?/, {input -> logicIfBothTimesInSameString(input)}],
		//just needs to convert to military time and return
		[/\d{1,2}:\d{2}[a|p][m]/,           {input -> convertToMilitaryTime(input)}],
		//
		[/\d{1,2}[a|p][m]/,           {input -> convertToMilitaryTime(addColonAndTwoZerosIfSansMinutes(input))}],
		//various possibilties of both times in one string
		[/\d{1,2}/,           {input -> addAmOrPmIfSans(addColonAndTwoZerosIfSansMinutes(input))}],
		
		[/.*/,              {input -> "'" + input + "could not be parsed"}]
	]
			
	
	/**
	 * If both start and end times are in same string, pass the string as one argument,
	 * and this method will send (startEndString, null, null) to overloaded method.
	 *
	 * @param value (raw input)
	 * @return  returns parsed out time, in either military or am/pm format
	 */
	public static applyrules(def value) {
		try {
			for (def rule in rules) {
				def (pattern, callback) = rule // unpack the values.
				// println '    testing ' + pattern + ' against ' + value
				if (value =~ pattern) {
					// println "pattern " + pattern + " found in '" + value + "'"
					def timePattern = parseOutTimePattern(value, pattern)
					//at this point we will have a time pattern with either one time or both times.
					//the problem is that, sometimes logic on one time depends on the value of the second time
					//so I need both times here in order to do much of the necessary logic
					
					// if we find something, return it.
					return callback(timePattern)
				}
			}
		}
		catch(e){
			logger.error("Problem with applyrules(def value)) using values input:" + value);
			println("Problem with applyrules(def value)) using values input:" + value);
			return null
		}
		
	}
	
	/**
	 * If both start and end times are in same string, pass the string as one argument,
	 * and this method will send (startEndString, null, null) to overloaded method.
	 *
	 * @param startEndString
	 * @return  start time in HH:mm format, military time
	 */
	public static parseOHStart(startEndString) {
		try {
			return parseOHStart(startEndString, null, null)
		}catch(e){
			logger.error("parseOHStart - " + e);
			return null;
		}
	}

	/**
	 * If start and end times are in different strings, pass both strings as separate arguments (start time string first),
	 * both start and end times will be parsed out of their strings, and start time will be returned if both start and end times are between 7am-9pm
	 *
	 * @param startTime
	 * @param endTime
	 * @return start time in HH:mm format, military time
	 */
	public static parseOHStart(startString, endString) {
		try {
			return parseOHStart(startString, endString, null)
		}catch(e){
			logger.error("parseOHStart - " + e);
			return null;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @param Enum timeZone
	 * @return start time in HH:mm format, military time
	 */
	public static parseOHStart(startString,endString, Enum timeZone) {
		try {
			return parseTimesAndReturnStartEndArray(startString,endString,timeZone)[0]
		} catch(e){
			logger.error("problem with parseOHStart - " +e);
			println("problem with parseOHStart - ")
			return null
		}
	}
	
	
	/**
	 * If you have a start time and a duration, pass start time string and duration(in minutes)
	 * as separate arguments(start time string first),
	 * function will parses out  start time from its string, calculate the end time,
	 * and then return the start time of both start and end times are between 7am-9pm
	 *
	 * @param startTime
	 * @param durationInMin
	 * @return
	 */
	public static parseOHStartWithDuration(startString, durationInMin) {
		try {
			def startTime = applyrules(startString)
			def endTime = getEndWithStartandDuration(startString, durationInMin)

			return (isValidOpenHomeTime(startTime, endTime))[0]
		}catch(e){
			logger.error("parseOHStartWithDuration - " +e);
			return null
		}
	}

	/**
	 * If both start and end times are in same string, pass the string as one argument,
	 * and this method will send (startEndString, null, null) to overloaded method.
	 *
	 * @param startEndString
	 * @return  start time in HH:mm format, military time
	 */
	public static parseOHEnd(startEndString) {
		try {
			return parseOHEnd(startEndString, null, null)
		}catch(e){
			logger.error("parseOHEnd - "+e);
			return null
		}
	}

	/**
	 * If start and end times are in different strings, pass both strings as separate arguments (start time string first),
	 * both start and end times will be parsed out of their strings, and end time will be returned if both start and end times are between 7am-9pm
	 *
	 * @param startTime
	 * @param endTime
	 * @return end time, in HH:mm format, military time
	 */
	public static parseOHEnd(startString,endString) {
		try {
			return parseOHEnd(startString,endString, null)
		}catch(e){
			logger.error("parseOHEnd - " +e);
			return null
		}
	}

	
	public static parseOHEnd(startString,endString, Enum timeZone) {
		try {
			return parseTimesAndReturnStartEndArray(startString,endString,timeZone)[1]
		} catch(e){
			logger.error("problem with parseOHEnd - " +e);
			println("problem with parseOHEnd - ")
			return null
		}
	}
	
	/**
	 * If you have a start time and a duration, pass start time string and duration(in minutes) as separate arguments(start time string first),
	 * function will parses out  start time from its string, calculate the end time,
	 * and  return the end time of both start and end times are between 7am-9pm
	 *
	 * @param startTime
	 * @param durationInMin
	 * @return end time, in HH:mm format, military time
	 */
	public static parseOHEndWithDuration(startString, durationInMin) {
		try {
			def startTime = applyrules(startString)
			def endTime = getEndWithStartandDuration(startString, durationInMin)
			
			return (isValidOpenHomeTime(startTime, endTime))[1]
		} catch(e){
			logger.error.("parseOHEndWithDuration - " + e);
			return null
		}
	}

	
	// ---------------Private functions -------------------
	
	
	/**
	 * always returns an array
	 *
	 * @param 
	 * @param 
	 * @return 
	 */
	private static parseTimesAndReturnStartEndArray(startString,endString, timeZone){
		try {
			if (timeZone){
				def startTime = convertFromUTC(startString,timeZone.getTimeZone())
				def endTime = convertFromUTC(endString,timeZone.getTimeZone())

				return (isValidOpenHomeTime(startTime, endTime))
			} else if (endString){
			
				startString = preParsingCleanUp(startString)
				endString = preParsingCleanUp(endString)
		
				def startTime = applyrules(startString)
				def endTime = applyrules(endString)

				return (isValidOpenHomeTime(startTime, endTime))
			} else {

				// if in format H-H(am/pm), add zeros to normalize times (5-6pm turns into 05:00 and 06:00)
				if(startString != null && startString != ""){
					startString = preParsingCleanUp(startString)
					def parsedTime = applyrules(startString)
					
					def startTime = convertStringWithTwoTimesIntoArray(parsedTime)[0]
					def endTime = convertStringWithTwoTimesIntoArray(parsedTime)[1]
					
					return (isValidOpenHomeTime(startTime, endTime))
				}
			}
		}
		catch(e){
			logger.error("Problem with dontKnowWhatToCallThis(startString,endString, timeZone) using values startString:" + startString + ", endString: " + endString + ", and timestamp: " + timeZone);
			println("Problem with dontKnowWhatToCallThis(startString,endString, timeZone) using values startString: " + startString + ", endString: " + endString + ", and timestamp: " + timeZone);
			return null
		}
		
	}
	
	/**
	 * takes input in form of HH:MM:SS
	 * strips the seconds
	 *
	 * @param input, pattern
	 * @return parsedOutTimePattern
	 */
	private static removeSecondsFromTwoTimestamps(input){
		try {
			def arrayOfBothTimes = convertStringWithTwoTimesIntoArray(input)
			
			def startTime = addColonAndTwoZerosIfSansMinutes(arrayOfBothTimes[0])
			def endTime = addColonAndTwoZerosIfSansMinutes(arrayOfBothTimes[1])
			
			startTime = removeSecondsFromTimestamp(startTime)
			endTime = removeSecondsFromTimestamp(endTime)
			
			return startTime + "-" + endTime
		}
		catch(e){
			logger.error("Problem with removeSecondsFromTwoTimestamps(input) using values input:" + input);
			println("Problem with removeSecondsFromTwoTimestamps(input) using values input:" + input)
			return null
		}
		
	}
	
	/**
	 * takes input in form of HH:MM:SS
	 * strips the seconds
	 *
	 * @param input, pattern
	 * @return parsedOutTimePattern
	 */
	private static removeSecondsFromTimestamp(input){
		try {
			def matcher = input =~ /(^\d{2}:\d{2}):\d{2}$/
			return matcher[0][1]
		}
		catch(e){
			logger.error("Problem with removeSecondsFromTimestamp(input) using values input:" + input);
			println("Problem with removeSecondsFromTimestamp(input) using values input:" + input)
			return null
		}
		
	}
	
	
	
	/**
	 * parses out a given time pattern from a string. Used when a time is embedded 
	 * somewhere in a longer string
	 *
	 * @param input, pattern
	 * @return parsedOutTimePattern
	 */
	private static parseOutTimePattern(input, pattern){
		try {
			def matcher = input =~ pattern
			def parsedOutTimePattern = matcher[0]
			return parsedOutTimePattern
		}
		catch(e){
			logger.error("Problem with parseOutTimePattern(input, pattern) using values input:" + input + " and pattern:" + pattern);
			println("Problem with parseOutTimePattern(input, pattern) using values input:" + input + " and pattern:" + pattern)
			return null
		}
		
		
		
	}
	
	/**
	 * takes a string with two times and creates an array
	 * with each time as an element in the array
	 *
	 * @param input
	 * @return arrayWithBothTimes
	 */
	private static convertStringWithTwoTimesIntoArray(input){
		
		try {
			def firstTime = input.substring(0,input.indexOf("-"))
			def secondTime = input.substring(input.indexOf("-") + 1,)
			
			def arrayWithBothTimes = [firstTime,secondTime]
			return arrayWithBothTimes
		}
		catch(e){
			logger.error("Problem with convertStringWithTwoTimesIntoArray(input) using value input:" + input);
			println("Problem with convertStringWithTwoTimesIntoArray(input) using value input:" + input);
			return null
		}

	}
	
	
	/**
	 * if time contains only hours, without minutes, add ":00"
	 * This must be done to convert to military time later
	 *
	 * @param input
	 * @return full time with minutes
	 */
	private static addColonAndTwoZerosIfSansMinutes(input){
		
		try {
			if (!input.contains(":")){
				if (input.contains("am")){
					return input.replaceAll("am","") + ":00am"
				}
				if (input.contains("pm")){
					return input.replaceAll("pm","") + ":00pm"
				}
				else {
					return input + ":00"
				}
			}
			else {
				return input
			}
		}
		catch(e){
			logger.error("Problem with addColonAndTwoZerosIfSansMinutes(input) using value input:" + input);
			println("Problem with addColonAndTwoZerosIfSansMinutes(input) using value input:" + input)
			return null
		}
	}
	

	/**
	 * logic if both strings are in same string
	 * a string
	 *
	 * @param arrayInput
	 * @return timeString
	 */
	private static logicIfBothTimesInSameString(input){
		
		try {
			def arrayOfBothTimes = convertStringWithTwoTimesIntoArray(input)
			
			def startTime = addColonAndTwoZerosIfSansMinutes(arrayOfBothTimes[0])
			def endTime = addColonAndTwoZerosIfSansMinutes(arrayOfBothTimes[1])
			
			startTime = convertToMilitaryTime(addAmOrPmIfSans(startTime,endTime)[0])
			endTime = convertToMilitaryTime(addAmOrPmIfSans(startTime,endTime)[1])
			
			return startTime + "-" + endTime
		}
		catch(e){
			logger.error("Problem with logicIfBothTimesInSameString(input) using value input:" + input);
			println("Problem with logicIfBothTimesInSameString(input) using value input:" + input)
			return null
		}
	}

	/**
	 * adds am or pm to both start and end times
	 * this is needed to convert to military time
	 * you can only send to this function of there are seconds in the time: ":00"
	 * it's okay to pass values that already have am/pm...this funciton will just pass them along
	 *
	 * @param arrayInput
	 * @return array with start and end in different elements
	 */
	private static addAmOrPmIfSans(time){
		try {
			
			def timeHour = time.substring(0,time.indexOf(":"))
			
			if (!time.contains("am") && !time.contains("pm") && timeHour.toInteger() >= 0 && timeHour.toInteger() <= 7){
				time = time + "pm"
			}
			if (!time.contains("am") && !time.contains("pm") && timeHour.toInteger() > 7){
				if (timeHour.toInteger() == 12){
					time = time + "pm"
				}
				else {
					time = time + "am"
				}
			}
			return time
		}
		catch(e){
			logger.error("Problem with addAmOrPmIfSans(time) using value: " + time);
			println("Problem with addAmOrPmIfSans(time) using value: " + time)
			return null
		}
	}
	
	/**
	 * adds am or pm to both start and end times
	 * this is needed to convert to military time
	 * you can only send to this function of there are seconds in the time: ":00"
	 * it's okay to pass values that already have am/pm...this funciton will just pass them along
	 *
	 * @param arrayInput
	 * @return array with start and end in different elements
	 */
	private static addAmOrPmIfSans(start,end){	
		try {
			def arrayOfTimes = [start,end]
			
			def startHour = start.substring(0,start.indexOf(":"))
			def endHour = end.substring(0,end.indexOf(":"))
			
			if (!start.contains("am") && !start.contains("pm") && startHour.toInteger() >= 0 && startHour.toInteger() <= 7){
				arrayOfTimes[0] = start + "pm"
			}
			if (!start.contains("am") && !start.contains("pm") && startHour.toInteger() > 7){
				if (startHour.toInteger() == 12){
					arrayOfTimes[0] = start + "pm"
				}
				else {
					arrayOfTimes[0] = start + "am"
				}
			}
			if (!end.contains("am") && !end.contains("pm") && endHour.toInteger() >= 0 && endHour.toInteger() <= 9){
				arrayOfTimes[1] = end + "pm"
			}
			if (!end.contains("am") && !end.contains("pm") && endHour.toInteger() > 9){
				if (endHour.toInteger() == 12){
					arrayOfTimes[1] = end + "pm"
				}
				else {
					arrayOfTimes[1] = end + "am"
				}
			}
			
			return arrayOfTimes

		}
		catch(e){
			logger.error("Problem with addAmOrPmIfSans(start,end) using value start: " + start + " end: " + end);
			println("Problem with addAmOrPmIfSans(start,end) using value start: " + start + " end: " + end)
			return null
		}
	}

	private static preParsingCleanUp(input){
		try{
			//remove some possible dates in the string
			input = input.replaceAll("[0-9]{1,4}[/,-][0-9]{1,2}[/,-][0-9]{1,4}","")
			input = input.replaceAll("[0-9]{1,2}/[0-9]{1,2}","")
			
			//remove all spaces and lowercase everything
			input = input.toLowerCase().replaceAll(" ","")
			
			//Change any "to" strings to "-" (in case 'to' us used to say between times)
			input = input.replaceAll("to", "-")
			//Change all "noon" words to "12:00"
			input = input.replaceAll("12:noon","12pm").replaceAll("12(noon)", "12pm").replaceAll("noon","12pm")
			
			//remove common words containing "a", "m" or "p", so that I can filter out 
			// all characters except "a" "p" and "m"
			input = input.replaceAll("at","").replaceAll("open","").replaceAll("time","").replaceAll("meet","")
			input = input.replaceAll("place","").replaceAll("around","").replaceAll("please","").replaceAll("appointment","")
			input = input.replaceAll("appt","").replaceAll("call","").replaceAll("up","").replaceAll("people","")
			
			//remove all characters except "a" "p" and "m"
			input = input.replaceAll("[b-lnoq-z]","")
			
			//Change all "a.m." and "p.m." to "am" and "pm"
			input = input.replaceAll("a.m.","am").replaceAll("p.m.","pm").replaceAll("a.m","am").replaceAll("p.m","pm")
			
			//Change all "." to ":" 
			input = input.replaceAll("[.]",":")
			
			return input

		}catch(e){
			println ("problem in preParsingCleanUp() function")
			return null
		}
	}
	
	/**
	 * Translates time from 12 hour to military time
	 *
	 * @param inputDate
	 * @return time (HH:MM)
	 */
	private static convertToMilitaryTime(time) {

		if(time != null && time !="") {
			time = new Date().parse("hh:mma",time)
			def fixedTime= time.format("HH:mm")

			return fixedTime
		}
	}
	
	/**
	 * Calculates end time from a start time and a duration (in minutes)
	 *
	 * @param startTime, durationInMin
	 * @return array with start and end times
	 */
	private static getEndWithStartandDuration(startTime, durationInMin) {
		def endTime=null

		if(startTime != null && durationInMin != null) {
			def start = applyrules(startTime)
			def durHours = ((durationInMin.toInteger())/60).toBigDecimal()

			//converting duration minutes to hours and splitting the value at the "." (if present) to create an HOUR value (durArray[0]) & a fraction (durArray[1])
			if(durHours.toString().contains(".")) {
				endTime = addDurToStartToFindEnd(start, durHours)
			}else {
				endTime = (start.substring(0,2).toInteger()+durHours.toInteger())+start.substring(2)
			}
		}

		//if the end time is earlier than 10AM (though unlikely), this will format the endTime so it returns as 09:00 and not 9:00.
		if (endTime != "" && endTime != null){
			if(endTime.toString().substring(0,endTime.indexOf(":")).length()== 1) {
				return "0"+endTime
			}else {
				return endTime
			}
		} else {
			return endTime
		}
	}

	/**
	 * this is a method used only by getEndWithStartandDuration()
	 *
	 * @param startTime, durationInMin
	 * @return array with start and end times
	 */
	private static addDurToStartToFindEnd(start, durHours){
		try {
			def minutes=null
			def endTime=null
	
			def durArray=durHours.toString().split("\\.")
			//durArray now has hours in [0] and minutesutes in [1]
	
			//determinuteses if durArray[1] is a half hour or a quarter hour, which requires slightly different steps to make it convertible into minutesutes.
			if(durArray[1].toString().length() == 1) {
				minutes = (start.substring(3).toInteger()+((durArray[1].toInteger()*6)))
			}else{
				minutes = (start.substring(3).toInteger()+((durArray[1].toInteger()*6)/10))
			}
			if(minutes.toString().length() == 1) {
				minutes = minutes + "0" //returns 12:00 and not 12:0
			}
			//this determinuteses the duration is properly added together with the original time. ie. adding 90 minutesutes to 12:30 will return 14:00, not 13:60
			if(minutes >= 60) {
				endTime =  ((start.substring(0,2).toInteger()+durHours.toInteger())+1 + ":" + (minutes-60)).replaceAll(":0",":00")
			}else{
				endTime = (start.substring(0,2).toInteger()+durHours.toInteger()) + ":" + minutes
			}
			return endTime
		}catch(e){
			println ("problem in addDurToStartToFindEnd(start, durHours) with start: " + start + " and  duration:" + durHours)
			return null
		}

	}

	/**
	 * Converts the time from the UTC standard timezone to the timezone of your choice.
	 *
	 * @param time as a DateTime value, ie. '2012-01-01T12:00:00'
	 * @param zone - MUST be set as one of the following values EXACTLY (case sensitive):
	 *             US/Samoa
	 US/Aleutian
	 US/Hawaii
	 US/Alaska
	 US/Pacific
	 US/Pacific-New
	 US/Arizona
	 US/Mountain
	 US/Central
	 US/Indiana-Starke
	 US/East-Indiana
	 US/Eastern
	 US/Michigan
	 * @return
	 */
	private static convertFromUTC(time,zone) {
		
		try {
			//import java.text.SimpleDateFormat
			
					def today = new Date().format("yyyy-MM-dd")
					def _time = null
					if(time!=null && time != "")
					{
						time=time.replaceAll(" ","T")
						if(time.length()>10)
						{
							_time= time
						}
						else if(time.length()==8)
						{
							_time= today+"T"+time
						}
						else if(time.length()==5)
						{
							_time=today+"T"+time+":00"
						}
						def gmtdf = new SimpleDateFormat("y-M-d'T'h:m:s")
						gmtdf.setCalendar(Calendar.getInstance(TimeZone.getTimeZone('UTC')))
						def cstcal = Calendar.getInstance(TimeZone.getTimeZone(zone))
						cstcal.setTimeInMillis(gmtdf.parse(_time).getTime())
						String.format('%02d:%02d', cstcal.get(Calendar.HOUR_OF_DAY), cstcal.get(Calendar.MINUTE))
					}
		}catch(e){
			println ("problem in addDurToStartToFindEnd(start, durHours) with time: " + time + " and  zone:" + zone)
			return null
		}
		
	}

	private static isValidOpenHomeTime(startTime, endTime)
	{
		def arrayWithBothItems = [null,null]
		
		//if both are good
		if (validateOpenHomeTime(startTime) && validateOpenHomeTime(endTime)){
			arrayWithBothItems[0] = startTime
			arrayWithBothItems[1] = endTime
			return arrayWithBothItems
		}

		//Log instances where time is not between 7am-9pm
		if (!validateOpenHomeTime(startTime)){
			logger.error("Start time (" + startTime + ") is not between 6am-10pm")
		}
		if (!validateOpenHomeTime(endTime)){
			logger.error("End time (" + endTime + ") is not between 6am-10pm")
		}

		//if either bad
		return arrayWithBothItems
	}


	/**
	 * This function checks if both start and end times are between 7am-9am, if so it ruturns end time
	 * Start and end times must, by the time they get to this function, be in format "HH:mm"
	 *
	 * @param time
	 * @return time boolean
	 */
	private static validateOpenHomeTime(time) {
		if (time != "" && time != null){
			def hour = time.substring(0,time.indexOf(":"))
			
			if (hour.toInteger() > 5 && hour.toInteger() < 23) {
				return true
			}
			else {
				//Here would be a good place to log the error, once we create logging
				return false
			}
		}
		else {
			return false
		}
	}
}


