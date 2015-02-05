package foothill;

public class Shweet extends Message
{
    // class constants
    public final static int MIN_SHWITTER_ID_LENGTH = 3;
    public final static int MAX_SHWITTER_ID_LENGTH = 15;
    public final static int MIN_SHWEET_LENGTH = 5;
    public final static int MAX_SHWEET_LENGTH = 140;
    public final static String DEFAULT_USER_ID = "default_user";

    // private members
    private String fromID; 

    public Shweet(String theAuth, String theMsg, String theID)
    {
        set(theAuth, theMsg, theID);
    }

    public Shweet()
    {
        this(DEFAULT_AUTHOR, DEFAULT_MESSAGE, DEFAULT_USER_ID);
    }

    public boolean set(String theAuth, String theMsg, String theID)
    {
        final boolean authorValid = super.setAuthor(theAuth);
        final boolean messageValid = setMessage(theMsg);
        final boolean iDValid = setFromID(theID);

        if (!authorValid || !messageValid || !iDValid)
            return false;

        return true;
    }

    public boolean setFromID(String fromID)
    {
        final boolean shwittertIDValid = isValidShwitterID(fromID);

        if (!shwittertIDValid)
            return false;

        this.fromID = fromID;
        return true; 
    }

    public String getFromID()
    {
        return fromID;
    }

    //I'm overridding setMessage because we have to test
    //the schweet length in this class too
    public boolean setMessage(String shweetMessage)
    {
        final boolean shweetValid = isValidShweet(shweetMessage);

        if (!shweetValid)
            return false;

        if (!super.setMessage(shweetMessage))
            return false;

        return true;
    }

    //validation helpers
    private boolean isValidShweet(String shweetMessage)
    {
        final boolean shweetLengthValid = isValidLength(shweetMessage, 
                MIN_SHWEET_LENGTH, MAX_SHWEET_LENGTH);

        if (!shweetLengthValid)
            return false;

        return true;
    }

    private boolean isValidShwitterID(String theID)
    {
        final boolean lengthValid = isValidLength(theID, 
                MIN_SHWITTER_ID_LENGTH, MAX_SHWITTER_ID_LENGTH);
        final boolean styleValid = stringHasOnlyAlphaOrNumOrUnderscore(theID);

        if (!lengthValid || !styleValid)
            return false;

        return true;
    }

    private boolean stringHasOnlyAlphaOrNumOrUnderscore(String theID)
    {
        if (theID == null || theID == "")
            return false;

        int k;

        for (k = 0 ; k < theID.length() ; k++)
        { 
            if (!Character.isLetterOrDigit(theID.charAt(k)) &&
                    !(theID.charAt(k) == '_'))
                return false;
        }

        return true;
    }

    private boolean isValidLength(String testString, int min, int max)
    {
        if (testString == null || testString == "" || 
                testString.length() < min || testString.length() > max)
            return false;
        return true;
    }

    public String toString()
    {
        String returnString;

        returnString = "Shweet: " + super.getAuthor() + "@" + fromID + "\n"
                + super.getMessage() + "\n";

        return returnString;
    }
}
