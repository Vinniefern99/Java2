package foothill;

public class Message
{
    // class constants
    public final static int MIN_MSG_LENGTH = 40;
    public final static int MAX_MSG_LENGTH = 1000000;
    public final static String DEFAULT_MESSAGE = "no message";
    public final static int MIN_AUTH_LENGTH = 4;
    public final static int MAX_AUTH_LENGTH = 100;
    public final static String DEFAULT_AUTHOR = "Vince Fernald";

    // private members
    private String author; 
    private String message;  


    public Message()
    {
        author = DEFAULT_AUTHOR;  
        message = DEFAULT_MESSAGE; 
    }

    public Message(String theAuth, String theMsg)
    {
        if (!setAuthor(theAuth))
        {
            author = DEFAULT_AUTHOR; 
        }

        if (!setMessage(theMsg))
        {
            message = DEFAULT_MESSAGE;
        }    
    } 

    public boolean setAuthor(String author)
    {
        if (!isValidLength(author, MIN_AUTH_LENGTH, MAX_AUTH_LENGTH)) 
            return false;

        this.author = author;
        return true;
    }

    public boolean setMessage(String message)
    {
        if (!isValidLength(message, MIN_MSG_LENGTH, MAX_MSG_LENGTH))
            return false;

        this.message = message;
        return true;
    }

    public String getAuthor()
    {
        return author;
    }    

    public String getMessage()
    {
        return message;
    }

    //Validation helper
    private boolean isValidLength(String testString, int min, int max)
    {
        if (testString == null || testString == "" || 
                testString.length() < min || testString.length() > max)
            return false;
        return true;
    }


    public String toString()
    {
        final String returnString = "Author: " + author + 
                "\n Message: ---------\n" + message + "\n";

        return returnString;
    }
}
