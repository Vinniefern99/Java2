package foothill;

//import javax.swing.*;
//import java.awt.*;
//import javax.swing.border.*;
//import java.util.Random;


public class Foothill
{
   public static void main(String[] args)
   {
      Card[][] deck = new Card[4][13];
      int row, col;
      Card.Suit st;
      char val;
      
      // instantiate the array elements
      for (row = 0; row < deck.length; row++)
         for (col = 0; col < deck[row].length; col++)
            deck[row][col] = new Card();
    
      // assign values to array elements
      for (row = 0; row < deck.length; row++)
      { 
         // set the suit for this loop pass
         st = Card.Suit.values()[row];
 
         // now set all the values for this suit
         deck[row][0].set('A', st);
         for (val='2', col = 1; val <= '9'; val++, col++)
            deck[row][col].set(val, st);
         deck[row][9].set('T', st);
         deck[row][10].set('J', st);
         deck[row][11].set('Q', st);
         deck[row][12].set('K', st);
      }
      
      // test compareTo with queen of spades
      Card queenOfSpades = new Card('q', Card.Suit.spades);
      String phrase;
      for ( row = 0; row < deck.length; row++)
      {
         for ( col = 0; col < deck[row].length; col ++)
         {
            if ( queenOfSpades.compareTo(deck[row][col]) < 0 )
                  phrase = " is less than ";
            else if ( queenOfSpades.compareTo(deck[row][col]) > 0 )
                  phrase = " is greater than ";
            else
                  phrase = " is equal to ";
                 
            System.out.println(queenOfSpades + phrase
                  + deck[row][col] );
         }   
      }
   }
}


/* Assignment 6
public class TestTree
{
    public static void main (String[] args)
    {
        
        
        
        
        Card card1 = new Card('3',Card.Suit.diamonds);
        Card card2 = new Card('4',Card.Suit.hearts);
        Card card3 = new Card('5',Card.Suit.clubs);

        CardQueue queue1 = new CardQueue();

        queue1.addCard(card1);
        System.out.println(queue1.toString());
        queue1.addCard(card2);
        System.out.println(queue1.toString());
        queue1.addCard(card3);
        System.out.println(queue1.toString());


        System.out.println(queue1.toString());

        queue1.removeCard();
        System.out.println(queue1.toString());
        queue1.removeCard();
        System.out.println(queue1.toString());
        queue1.removeCard();
        System.out.println(queue1.toString());
        queue1.removeCard();

        /*


        Queue q = new Queue();
        Node p;

        // build the stack
        for (int k = 0; k < 3; k++)
        {
            p = new Node();
            q.add(p);
        }

        // show the stack, deleting as you pop
        //while ( (p = q.remove()) != null)
        //    System.out.println(p.toString());
        System.out.println(q.toString());

        q.remove();
        System.out.println();
        System.out.println(q.toString());

        q.remove();
        System.out.println();
        System.out.println(q.toString());

        q.remove();
        System.out.println();
        System.out.println(q.toString());

        q.remove();
        System.out.println();
        System.out.println(q.toString());
         
    }
    
}
*/

