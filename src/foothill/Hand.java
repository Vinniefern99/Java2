package foothill;

public class Hand
{
    public static final int MAX_CARDS = 50;

    private Card[] myCards;
    private int numCards;

    public Hand()
    {
        myCards = new Card[MAX_CARDS];;
        resetHand();
    }

    public void resetHand()
    {
        numCards = 0;
    }

    public boolean takeCard(Card card) 
    {
        if (numCards >= MAX_CARDS)
            return false;

        // be frugal - only allocate when needed
        if (myCards[numCards] == null)
            myCards[numCards] = new Card();

        // don't assign - that would be a grave error (could clone(), instead)
        myCards[numCards++].set(card.getValue(), card.getSuit());
        return true;
    }

    public Card playCard()
    {
        // always play highest card in array. client will prepare this 
        // position. in rare case that client tries 
        // to play from a spent hand, return error

        final Card errorReturn = new Card('E', Card.DEFAULT_SUIT); 

        if (numCards == 0)
            return errorReturn;
        else
            return myCards[--numCards];
    }

    public Card inspectCard(int k)
    {
        final Card errorReturn = new Card('E', Card.DEFAULT_SUIT);

        if (k < 0 || k >= numCards)
            return errorReturn;
        else
            // don't let the client modify the card in our private array
            return new Card (myCards[k]);
    }

    public String toString()
    {
        int k;
        String allCards = "( ";

        for (k = 0; k < numCards; k++) 
        {
            allCards += myCards[k].toString();
            if (k < numCards - 1)
                allCards += ", ";
        }

        allCards += " )";
        return allCards;
    }

    public int getNumCards()
    {
        return numCards;
    }
}