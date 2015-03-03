package foothill;

public class Card implements Comparable
{
    public enum Suit { clubs, diamonds, hearts, spades };

    static char DEFAULT_VAL = 'A';
    static Suit DEFAULT_SUIT = Suit.spades;

    private String illegalReturnString = "** illegal **";

    private char value;
    private Suit suit;
    private boolean errorFlag;

    // for ordering
    public static char[] valueRanks = { '2', '3', '4', '5', '6', '7', '8', '9', 
        'T', 'J', 'Q', 'K', 'A'};
    static Suit[] suitRanks = {Suit.clubs, Suit.diamonds, Suit.hearts, 
        Suit.spades};
    static int numValsInOrderingArray = 13;


    //constructor
    public Card(char value, Suit suit) 
    {
        set(value,suit);
    }

    public Card(char value)
    {
        this(value, DEFAULT_SUIT);
    }

    //default constructor without parameters
    public Card() 
    {
        this(DEFAULT_VAL, DEFAULT_SUIT);

    }

    public Card(Card card)
    {
        this(card.value, card.suit);
    }

    //one mutator method that sets suit and value
    public boolean set(char value, Suit suit)
    {
        final char upperValue = Character.toUpperCase(value);

        if (isValid(value,suit))
        {
            errorFlag = false;
            this.suit = suit;
            this.value = upperValue;
            return true;
        }
        else
        {
            errorFlag = true;
            return false;
        }
    }

    public char getValue()
    {
        return value;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public boolean getErrorFlag()
    {
        return errorFlag;
    }

    public boolean equals(Card card)
    {
        if (this.value != card.value)
            return false;
        if (this.suit != card.suit)
            return false;
        if (this.errorFlag != card.errorFlag)
            return false;
        return true;
    }

    public String toString()
    {
        String stringValue, stringSuit, returnValue;

        stringValue = String.valueOf(value);
        stringSuit = String.valueOf(suit);

        returnValue = "(" + stringValue + " of " + stringSuit + ")";

        if (!this.errorFlag)
        {
            return returnValue;
        }
        else
            return illegalReturnString;
    }

    private static boolean isValid(char value, Suit suit)
    {
        final char upperValue;
        final String legalVals = "23456789TJQKAX";

        upperValue  = Character.toUpperCase(value);

        // check for validity
        return legalVals.indexOf(upperValue) >= 0;
    }





    // sort member methods
    public int compareTo(Object other)
    {
        if (! (other instanceof Card) )
            return -1;

        Card otherCard = (Card)other;

        if (this.value == otherCard.value)
            return ( getSuitRank(this.suit) - getSuitRank(otherCard.suit) );

        return ( 
                getValueRank(this.value) 
                - getValueRank(otherCard.value) 
                );
    }

    // helpers for compareTo()
    public static int getSuitRank(Suit st)
    {
        int k;

        for (k = 0; k < 4; k++) 
            if (suitRanks[k] == st)
                return k;

        // should not happen
        return 0;
    }

    public  static int getValueRank(char val)
    {
        int k;

        for (k = 0; k < numValsInOrderingArray; k++) 
            if (valueRanks[k] == val)
                return k;

        // should not happen
        return 0;
    }

}



