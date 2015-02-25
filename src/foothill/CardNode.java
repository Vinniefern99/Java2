package foothill;

//import java.text.NumberFormat;
//import java.util.Locale;

public class CardNode extends Node
{
    // additional data for subclass
    private Card myCard;

    // constructor
    public CardNode(Card theCard)
    {
        super();  // constructor call to base class
        myCard = theCard;
    }

    // accessor
    public Card getData()
    {
        return myCard;
    }

    // overriding toString()
    public String toString()
    {
        return myCard.toString();
    }
}
