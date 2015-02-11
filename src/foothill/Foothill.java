package foothill;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*

public class Foothill 
{
    //Phase1 code is at the bottom, commented out
    //Phase2 code at the top, uncommented


    //Phase 2 Code:

    static int NUM_CARDS_PER_HAND = 7;
    static int  NUM_PLAYERS = 2;
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
    static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
    static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 

    public static void main(String[] args)
    {
        int k;
        Icon tempIcon;

        // establish main frame in which program will run
        CardTable myCardTable 
        = new CardTable("CS 1B CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
        myCardTable.setSize(800, 600);
        myCardTable.setLocationRelativeTo(null);
        myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // show everything to the user
        myCardTable.setVisible(true);

        // CREATE LABELS ----------------------------------------------------
        code goes here ...

        // ADD LABELS TO PANELS -----------------------------------------
        code goes here ...

        // and two random cards in the play region (simulating a computer/hum ply)
        code goes here ...

        // show everything to the user
        myCardTable.setVisible(true);

        private static Card generateRandomCard()
        {
            char theValue = 
                    GUICard.turnIntIntoCardValueChar((int)Math.random());
            char theIntSuit = GUICard.turnIntIntoCardSuitChar((int)Math.random());
            Card.Suit theCardSuit;
            Card returnCard;

            if (theIntSuit == 'C')
                theCardSuit = Card.Suit.clubs;
            if (theIntSuit == 'D')
                theCardSuit = Card.Suit.diamonds;
            if (theIntSuit == 'H')
                theCardSuit = Card.Suit.hearts;
            if (theIntSuit == 'S')
                theCardSuit = Card.Suit.spades;

            returnCard = new Card(theValue,theCardSuit);
            return returnCard;


        }
    }














    //Phase 1 Code:

    // static for the 57 icons and their corresponding labels
    // normally we would not have a separate label for each card, but
    // if we want to display all at once using labels, we need to.


    /*
   static int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];
   static JLabel[] labels = new JLabel[NUM_CARD_IMAGES];

   // for assisting with conversions:
   static String cardlValsConvertAssist = "23456789TJQKAX";
   static String suitValsConvertAssist = "CDHS";

   static void loadCardIcons()
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
           icon[intSuit*14 + intVal] = new ImageIcon(imageFileName);
         }
      imageFileName = "images/BK.gif";
      icon[icon.length - 1] = new ImageIcon(imageFileName);
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

   public static void main(String[] args)
   {

      int k;

      // prepare the image icon array
      loadCardIcons();

      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Card Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);   
      frmMyWindow.setLayout(layout);

      // prepare the image label array
      JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         labels[k] = new JLabel(icon[k]);

      // place your 3 controls into frame
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         frmMyWindow.add(labels[k]);

      // show everything to the user
      frmMyWindow.setVisible(true);
   }

     */
}