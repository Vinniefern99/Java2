package foothill;
import javax.swing.*;
import java.awt.*;

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
    
    private JPanel panel1, panel2, panel3;
    
    public CardTable(String title, int numCardsPerHand, int numPlayers)
    {
        super(title);
        
        if (!isValidNumCards(numCardsPerHand))
            this.numCardsPerHand = DEFAULT_NUM_CARDS;
        if (!isValidNumPlayers(numPlayers))
            this.numPlayers = DEFAULT_NUM_PLAYERS;
        
        this.numCardsPerHand = numCardsPerHand;
        this.numPlayers = numPlayers;

        
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
