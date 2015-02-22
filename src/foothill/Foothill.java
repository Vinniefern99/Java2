package foothill;

//import javax.swing.*;
//import java.awt.*;
//import javax.swing.border.*;
//import java.util.Random;


public class Foothill
{
    public static void main (String[] args)
    {
        Queue q = new Queue();
        Node p;

        // build the stack
        for (int k = 0; k < 5; k++)
        {
            p = new Node();
            q.add(p);
        }

        // show the stack, deleting as you pop
        //while ( (p = q.remove()) != null)
        //    System.out.println(p.toString());
        System.out.println(q.toString());
        
        
    }
}