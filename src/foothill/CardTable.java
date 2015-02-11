package foothill;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class CardTable extends JFrame
{
    static int MIN_CARDS_PER_HAND = 0;
    static int MAX_CARDS_PER_HAND = 57;
    static int MIN_PLAYERS = 2;  // for now, we only allow 2 person games
    static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
    static int DEFAULT_NUM_CARDS = 7;
    static int DEFAULT_NUM_PLAYERS = 2;

    private int numCardsPerHand;
    private int numPlayers;

    public static JPanel pnlComputerTop, pnlMiddleTable, pnlPlayerBottom;

    public CardTable(String title, int numCardsPerHand, int numPlayers)
    {
        super(title);

        if (!isValidNumCards(numCardsPerHand))
            this.numCardsPerHand = DEFAULT_NUM_CARDS;
        if (!isValidNumPlayers(numPlayers))
            this.numPlayers = DEFAULT_NUM_PLAYERS;
        this.numCardsPerHand = numCardsPerHand;
        this.numPlayers = numPlayers;

        pnlComputerTop = 
                new JPanel(new GridLayout(1,this.numCardsPerHand,20,10));
        pnlMiddleTable = 
                new JPanel(new GridLayout(2,2,20,10));
        pnlPlayerBottom = 
                new JPanel(new GridLayout(1,this.numCardsPerHand,20,10));

        setLayout(new BorderLayout(20,10));
        add(pnlComputerTop, BorderLayout.NORTH);
        add(pnlMiddleTable, BorderLayout.CENTER);
        add(pnlPlayerBottom, BorderLayout.SOUTH);

        // place borders around three sub-panels
        pnlComputerTop.setBorder(new TitledBorder("Computer Hand"));
        pnlMiddleTable.setBorder(new TitledBorder("Playing Area"));
        pnlPlayerBottom.setBorder(new TitledBorder("Your Hand"));

    }

    private boolean isValidNumCards(int numCardsPerHand)
    {
        if (numCardsPerHand < MIN_CARDS_PER_HAND || 
                numCardsPerHand > MAX_CARDS_PER_HAND)
            return false;
        return true;
    }

    private boolean isValidNumPlayers(int numPlayers)
    {
        if (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS)
            return false;
        return true;
    }

    public int getNumCardsPerHand()
    {
        return numCardsPerHand;
    }

    public int getNumPlayers()
    {
        return numPlayers;
    }

}
