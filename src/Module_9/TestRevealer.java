package Module_9;

public class TestRevealer
{
   public static void main(String[] args)
   {
      String string1 = "death of a salesman";
      String string2 = "queen's gambit";
      Card card1, card2, card3;
 
 
      card1 = new Card();
      card2 = new Card('5');
      card3 = new Card('9', Card.Suit.hearts);

      Revealer.displayGUI(card1);
      Revealer.displayGUI(string1);
      Revealer.displayGUI( new Card('t',Card.Suit.clubs) );
      
      Revealer.displayConsole(card3);   
      Revealer.displayConsole(card2);
      Revealer.displayConsole(string2); 
   }
}