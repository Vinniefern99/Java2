package test

import java.util.Properties;
import com.dozer.groovy.common.OpenHouseUtils
import com.dozer.groovy.common.TimeZoneTypes;

import groovy.util.GroovyTestCase;
//import org.apache.log4j.PropertyConfigurator;


/**
 *
 * TestOpenHomeTime.groovy
 * @author jason
 * @since 2012-05-29
 *
 * Test open home time parsing
 * Adds function to convert from UTC
 */

class TestOpenHouseUtils extends GroovyTestCase {
  /*
   void loadLog4JConfig(){
   try {
   Properties props = new Properties();
   props.load(new FileInputStream("C:\\Users\\vfernald\\Documents\\workspace\\di-groovy\\src\\com\\dozer\\groovy\\logger\\Log4j.properties"));
   PropertyConfigurator.configure(props);
   }
   catch(Exception e){
   print(e.stackTrace)
   }
   }
   */

  void testparseOHStart() {
   // loadLog4JConfig()

    assert "14:00" == OpenHouseUtils.parseOHStart("2:00pm to 5:00pm")
    assert "13:00" == OpenHouseUtils.parseOHStart("1:00 - 5:00PM")
    assert "13:00" == OpenHouseUtils.parseOHStart("1PM to 3PM")
    assert "12:00" == OpenHouseUtils.parseOHStart("12 PM - 3 PM")
    assert "12:00" == OpenHouseUtils.parseOHStart("12 p.m.-3 P.M.")
    assert "12:00" == OpenHouseUtils.parseOHStart("12:00 pm - 3:00 pm")
    assert "12:00" == OpenHouseUtils.parseOHStart("noon - 3 PM")
    assert "06:00" == OpenHouseUtils.parseOHStart("6:00am - 8:00am")
    assert "13:00" == OpenHouseUtils.parseOHStart("1 PM to 3 PM")
    assert "14:00" == OpenHouseUtils.parseOHStart("2:00-3:00")
    assert "12:00" == OpenHouseUtils.parseOHStart("12-2:30pm")
    assert "16:00" == OpenHouseUtils.parseOHStart("4:00-6:00pm")
    assert "13:00" == OpenHouseUtils.parseOHStart("13:00:00-15:00:00")
	assert "14:00" == OpenHouseUtils.parseOHStart("2:00PM - 4:00PM")
	assert "13:00" == OpenHouseUtils.parseOHStart("1:00PM - 4:00PM")

    assert "13:00" == OpenHouseUtils.parseOHStart("13:00 - 15:00")
    assert "15:00" == OpenHouseUtils.parseOHStart("15:00 - 18:00")
    assert "13:00" == OpenHouseUtils.parseOHStart("1:00 - 3:00")
	assert null == OpenHouseUtils.parseOHStart("0")

	assert "12:30" == OpenHouseUtils.parseOHStart("12:30Pm-2:30Pm")
	assert "13:00" == OpenHouseUtils.parseOHStart("1Pm-3Pm")
	
	//These are assertions that are failing. Fix OpenHouseUtils to 
	assert "15:30" == OpenHouseUtils.parseOHStart("3:30Pm-5Pm")
	assert "12:30" == OpenHouseUtils.parseOHStart("12:30Pm-2Pm")
	

    assert "14:30" == OpenHouseUtils.parseOHStart("Open house at 2:30 pm","Ends at 4:30 pm")
    assert "16:00" == OpenHouseUtils.parseOHStart("4.00 P.M. is time", "6.15 P.M.")
    assert "16:00" == OpenHouseUtils.parseOHStart("4.00P.M. is time", "6.15P.M.")
    assert "10:00" == OpenHouseUtils.parseOHStart("10:00am is the time", "1:15pm")
    assert "09:00" == OpenHouseUtils.parseOHStart("9:00a.m.", "10:30a.m.")
    assert "13:30" == OpenHouseUtils.parseOHStart("13:30:00", "14:15:00")
	assert "11:00" == OpenHouseUtils.parseOHStart("2012-01-01T11:00:00", "2012-01-01T13:45:00")
    assert "11:00" == OpenHouseUtils.parseOHStart("11am", "1pm")
    assert "10:00" == OpenHouseUtils.parseOHStart("10 am", "11 am")
    assert "12:00" == OpenHouseUtils.parseOHStart("12:00 P.M", "1:00 P.M.")
    assert "13:30" == OpenHouseUtils.parseOHStart("01:30 pm", "3:30pm")
    assert "11:00" == OpenHouseUtils.parseOHStart("11:00", "01:00pm")
    assert null == OpenHouseUtils.parseOHStart("10:00pm", "11:15pm")
    assert "06:00" == OpenHouseUtils.parseOHStart("6:00 a.m.", "8:30 a.m.")
    assert null == OpenHouseUtils.parseOHStart("2:00 a.m.", "4:30 a.m.")
    assert "14:00" == OpenHouseUtils.parseOHStart("2004-08-22T14:00:00", "2004-08-22T16:00:00")
    assert "13:00" == OpenHouseUtils.parseOHStart("13:00", "14:00")
    assert "14:00" == OpenHouseUtils.parseOHStart("14:00", "16:00")
	assert null == OpenHouseUtils.parseOHStart(null, "0")
	assert "10:00" == OpenHouseUtils.parseOHStart("10:00", "13:00")
	assert "14:00" == OpenHouseUtils.parseOHStart("14:00:00", "17:00:00")

    assert "11:00" == OpenHouseUtils.parseOHStart("2012-08-20T18:00:00", "2012-08-20T18:00:00", TimeZoneTypes.PACIFIC)
    assert "11:00" == OpenHouseUtils.parseOHStart("2012-08-20T18:00:00", "2012-08-20T18:00:00", TimeZoneTypes.PACIFIC)
    assert "10:00" == OpenHouseUtils.parseOHStart("2012-01-20T18:00:00", "2012-08-20T18:00:00", TimeZoneTypes.PACIFIC)
  }

  void testparseOHStartWithDuration() {
   // loadLog4JConfig()

    assert "12:00" == OpenHouseUtils.parseOHStartWithDuration("2012-01-01T12:00:00", "120")
    assert "10:30" == OpenHouseUtils.parseOHStartWithDuration("2012-01-01T10:30:00", "330")
    assert "12:00" == OpenHouseUtils.parseOHStartWithDuration("2012-01-01T12:00:00", "45")
    assert "10:00" == OpenHouseUtils.parseOHStartWithDuration("2012-01-01T10:00:00", "135")
    assert "14:00" == OpenHouseUtils.parseOHStartWithDuration("2012-01-01T14:00:00", "210")
    assert null == OpenHouseUtils.parseOHStartWithDuration("2012-01-01T00:00:00", "120")
  }


  void testparseOHEnd() {
   // loadLog4JConfig()

    assert "17:00" == OpenHouseUtils.parseOHEnd("2:00 to 5:00pm")
    assert "17:00" == OpenHouseUtils.parseOHEnd("1:00 PM - 5:00 PM")
    assert "15:00" == OpenHouseUtils.parseOHEnd("noon - 3 PM")
    assert "12:00" == OpenHouseUtils.parseOHEnd("10AM - Noon")

    assert "16:30" == OpenHouseUtils.parseOHEnd("Open house at 2:30 pm","Ends at 4:30 pm")
    assert "18:15" == OpenHouseUtils.parseOHEnd("4.00 P.M.", "6.15 P.M.")
    assert "13:15" == OpenHouseUtils.parseOHEnd("10:00am", "1:15pm")
    assert "10:30" == OpenHouseUtils.parseOHEnd("9.00 a.m.", "10.30 a.m.")
    assert "14:15" == OpenHouseUtils.parseOHEnd("13:30:00", "14:15:00")
    assert "13:45" == OpenHouseUtils.parseOHEnd("2012-01-01T11:00:00", "2012-01-01T13:45:00")
    assert null == OpenHouseUtils.parseOHEnd("10:00pm", "11:15pm")
    assert "08:30" == OpenHouseUtils.parseOHEnd("6:00 a.m.", "8:30 a.m.")
    assert null == OpenHouseUtils.parseOHEnd("2:00 a.m.", "4:30 a.m.")
    assert "16:00" == OpenHouseUtils.parseOHEnd("2004-08-22T14:00:00", "2004-08-22T16:00:00")
    assert "14:00" == OpenHouseUtils.parseOHEnd("13:00", "14:00")
    assert "16:00" == OpenHouseUtils.parseOHEnd("14:00", "16:00")

    assert "11:00" == OpenHouseUtils.parseOHEnd("2012-08-20T18:00:00", "2012-08-20T18:00:00", TimeZoneTypes.PACIFIC)
    assert "11:00" == OpenHouseUtils.parseOHEnd("2012-08-20T18:00:00", "2012-08-20T18:00:00", TimeZoneTypes.PACIFIC)
    assert "11:00" == OpenHouseUtils.parseOHEnd("2012-01-20T18:00:00", "2012-08-20T18:00:00", TimeZoneTypes.PACIFIC)
    assert "14:00" == OpenHouseUtils.parseOHEnd("18:00", "2012-08-20T18:00:00", TimeZoneTypes.EASTERN)
 
  
  }



  void testparseOHEndWithDuration() {
    //loadLog4JConfig()

    assert "14:00" == OpenHouseUtils.parseOHEndWithDuration("2012-01-01T12:00:00", "120")
    assert "16:00" == OpenHouseUtils.parseOHEndWithDuration("2012-01-01T10:30:00", "330")
    assert "12:45" == OpenHouseUtils.parseOHEndWithDuration("2012-01-01T12:00:00", "45")
    assert "14:15" == OpenHouseUtils.parseOHEndWithDuration("2012-01-01T12:00:00", "135")
    assert "15:30" == OpenHouseUtils.parseOHEndWithDuration("2012-01-01T12:00:00", "210")
    assert null == OpenHouseUtils.parseOHEndWithDuration("2012-01-01T00:00:00", "120")
  }
}


