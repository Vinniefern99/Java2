package foothill;
import javax.swing.*;

public class GUICard
{
    private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
    private static Icon iconBack;
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
        String imageFileName;
        int intSuit, intVal;

        for (intSuit = 0; intSuit < 4; intSuit++)
           for (intVal = 0; intVal < 14; intVal++ )
           {
              // card image files stored in Foothill/images folder with names like
              // "AC.gif", "3H.gif","XD.gif", etc.
              imageFileName = "images/"
                    + turnIntIntoCardValueChar(intVal) 
                    + turnIntIntoCardSuitChar(intSuit)
                    + ".gif";
              iconCards[intVal][intSuit] = new ImageIcon(imageFileName);
           }
        //imageFileName = "images/BK.gif";
        //icon[icon.length - 1] = new ImageIcon(imageFileName);
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
}
