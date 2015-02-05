package foothill;

import java.util.Random;

public class Deck
{
    private static final int MAX_PACKS = 6;
    private static final int NUM_CARDS_PER_PACK = 52;
    private static final int MAX_CARDS_PER_DECK = 
            MAX_PACKS * NUM_CARDS_PER_PACK;
    private static final int DEFAULT_PACKS = 1;

    private static Card[] masterPack;

    private Card[] cards;
    private int topCard;
    private int numPacks;
    private Random random;

    public Deck(int numPacks)
    {
        allocateMasterPack();

        //initializing a card array with the max possible card references
        cards = new Card[MAX_CARDS_PER_DECK];

        init(numPacks);
    }

    public Deck()
    {
        this(DEFAULT_PACKS);
    }

    public boolean init(int numPacks)
    {
        if (numPacks < 0 || numPacks > MAX_PACKS)
            return false;

        int j, k;
        j = 0;

        this.numPacks = numPacks;
        topCard = numPacks * NUM_CARDS_PER_PACK;

        while (j < topCard)
        {
            for (k=0 ; k < masterPack.length ; k++)
            {
                cards[j] = masterPack[k];
                j++;
            }
        }

        this.random = new Random(1L);
        return true;
    }

    public Card dealCard()
    {
        //providing an error response card in case there is
        //any chance that the aren't any cards
        final Card errorReturn = new Card('E', Card.DEFAULT_SUIT); 

        //I'm not saying if(topCard < 0) here because the instructions say
        //that topCard holds the number of cards in the deck, not the 
        //reference of the largest card.
        if (topCard == 0)
            return errorReturn;
        else
            return new Card (cards[--topCard]);
    }

    public void shuffle()
    {
        int i, randInt;

        for (i = topCard-1; i > 0; i--) 
        {
            randInt = random.nextInt(i+1); // Note nextInt(n) < n
            final Card swap = cards[randInt];
            cards[randInt] = cards[i];
            cards[i] = swap;       
        }      
    }

    //accessor for topCard
    public int getNumCards()
    {
        return topCard;
    }

    //accessor for numPacks
    public int getNumPacks()
    {
        return numPacks;
    }

    public Card inspectCard(int k) 
    {
        final Card errorReturn = new Card('E', Card.DEFAULT_SUIT);

        if (k < 0 || k >= topCard)
            return errorReturn;
        else
            return new Card (cards[k]);
    }

    public String toString()
    {
        int k;
        String allCards = "{ ";

        for (k = 0; k < topCard; k++) 
        {
            allCards += cards[k].toString();
            if (k < topCard - 1)
                allCards += ", ";
        }

        allCards += " }";
        return allCards;
    }

    public static void allocateMasterPack()
    {
        if (masterPack != null)
            return;

        masterPack = new Card[NUM_CARDS_PER_PACK];

        int j, k, l;
        l = 0;
        final String legalVals = "23456789TJQKA";

        for (j=0; j < Card.Suit.values().length ; j++)
        {
            for (k=0 ; k < legalVals.length() ; k++)
            {
                masterPack[l++] = 
                        new Card(legalVals.charAt(k),Card.Suit.values()[j]);
            }

        }
    }

}
