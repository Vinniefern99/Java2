package foothill;

public class Email extends Message
{
    // class constants
    public final static int MIN_EMAIL_ADDRESS_LENGTH = 7;
    public final static int MAX_EMAIL_ADDRESS_LENGTH = 100;
    public final static String DEFAULT_EMAIL_ADDRESS = "vince@gmail.com";

    // private members
    private String fromAddress; 
    private String toAddress;  

    public Email(String theAuth, String theMsg, String fromAddr, String toAddr)
    {
        set(theAuth, theMsg, fromAddr, toAddr);
    }

    public Email()
    {
        this(DEFAULT_AUTHOR, DEFAULT_MESSAGE, 
                DEFAULT_EMAIL_ADDRESS, DEFAULT_EMAIL_ADDRESS);
    }

    private boolean set(String theAuth, String theMsg, 
            String fromAddr, String toAddr)
    {

        final boolean authorValid = super.setAuthor(theAuth);
        final boolean messageValid = super.setMessage(theMsg);
        final boolean toAddressValid = setFromAddress(fromAddr);
        final boolean fromAddressValid = setToAddress(toAddr);

        if (!authorValid || !messageValid ||
                !toAddressValid || !fromAddressValid)
            return false;

        return true;
    }

    public boolean setFromAddress(String fromAddress)
    {
        final boolean lengthValid = isValidLength(fromAddress,
                MIN_EMAIL_ADDRESS_LENGTH, MAX_EMAIL_ADDRESS_LENGTH);
        final boolean emailValid = isValidEAddr(fromAddress);

        if (!lengthValid || !emailValid)
            return false;

        this.fromAddress = fromAddress;
        return true;
    }

    public boolean setToAddress(String toAddress)
    {
        final boolean lengthValid = isValidLength(toAddress,
                MIN_EMAIL_ADDRESS_LENGTH, MAX_EMAIL_ADDRESS_LENGTH);
        final boolean emailValid = isValidEAddr(toAddress);

        if (!lengthValid || !emailValid)
            return false;

        this.toAddress = toAddress;
        return true;
    }

    public String getFromAddress()
    {
        return fromAddress;
    }

    public String getToAddress()
    {
        return toAddress;   
    }

    //Validation helpers
    private boolean isValidLength(String testString, int min, int max)
    {
        if (testString == null || testString == "" || 
                testString.length() < min || testString.length() > max)
            return false;
        return true;
    }

    private boolean isValidEAddr(String emailAddress)
    {
        if (emailAddress == null || emailAddress == "")
            return false;

        final boolean hasAtSign = emailAddress.contains("@");
        final boolean hasPeriod = emailAddress.contains(".");

        if (!hasAtSign || !hasPeriod)
            return false;

        return true;
    }

    public String toString()
    {
        String returnString;

        returnString = "From: " + fromAddress + "\nTo: " + 
                toAddress + "\n" + super.toString();

        return returnString;
    }
}
