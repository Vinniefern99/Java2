package foothill;

//import javax.swing.*;
//import java.awt.*;
//import javax.swing.border.*;
//import java.util.Random;


public class Foothill
{
    public static void main (String[] args)
    {
        Card card1 = new Card('3',Card.Suit.diamonds);
        Card card2 = new Card('4',Card.Suit.hearts);
        Card card3 = new Card('5',Card.Suit.clubs);

        CardQueue queue1 = new CardQueue();

        queue1.addCard(card1);
        queue1.addCard(card2);
        queue1.addCard(card3);


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
         */
    }
}