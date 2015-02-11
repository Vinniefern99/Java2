package foothill;
import javax.swing.*;

public class GUICard
{
    private static Icon[][] iconCards = new ImageIcon[14][4];
    private static Icon iconBack = new ImageIcon("images/BK.gif");
    private static boolean iconsLoaded = false;

    private static String cardlValsConvertAssist = "23456789TJQKAX";
    private static String suitValsConvertAssist  = "CDHS";
    private static Card.Suit suitConvertAssist[] =
    {
        Card.Suit.clubs,
        Card.Suit.diamonds,
        Card.Suit.hearts,
        Card.Suit.spades
    };

    public static void loadCardIcons()
    {
        if (iconsLoaded)
            return;

        String imageFileName;
        int intSuit, intVal;

        for (intSuit = 0; intSuit < 4; intSuit++)
            for (intVal = 0; intVal < 14; intVal++ )
            {
                // card image files stored in Foothill
                //images folder with names like
                // "AC.gif", "3H.gif","XD.gif", etc.
                imageFileName = "images/"
                        + turnIntIntoCardValueChar(intVal) 
                        + turnIntIntoCardSuitChar(intSuit)
                        + ".gif";
                iconCards[intVal][intSuit] = new ImageIcon(imageFileName);
            }

        iconsLoaded = true;
    }

    // turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
    static char turnIntIntoCardValueChar(int k)
    {

        if ( k < 0 || k > 13)
            return '?'; 
        return cardlValsConvertAssist.charAt(k);
    }

    // turns 0 - 3 into 'C', 'D', 'H', 'S'
    static char turnIntIntoCardSuitChar(int k)
    {
        if ( k < 0 || k > 3)
            return '?'; 
        return suitValsConvertAssist.charAt(k);
    }

    static public Icon getIcon(Card card)
    {
        loadCardIcons(); // will not load twice, so no worries.
        return iconCards[valueAsInt(card)][suitAsInt(card)];
    }

    private static int valueAsInt(Card card)
    {
        final char value = card.getValue();
        int k;

        for (k = 0 ; k < cardlValsConvertAssist.length() ; k++)
        {
            if ( value == cardlValsConvertAssist.charAt(k))
                return k;         
        }

        return 'X'; 
    }

    private static int suitAsInt(Card card)
    {
        final Card.Suit theSuit = card.getSuit();
        int k;

        for (k = 0 ; k < suitConvertAssist.length ; k++ )
        {
            if (theSuit == suitConvertAssist[k])
                return k;   
        }

        return 0;
    }

    public static Icon getIconBack() 
    {
        return iconBack;
    }

}
