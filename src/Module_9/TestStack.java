package Module_9;

public class TestStack
{
   public static void main (String[] args)
   {
      float x;
      Stack<Float> fStk = new Stack<Float>();

      // build the stack
      for (x = 0; x < 5; x++)
         fStk.push(x);

      System.out.println("Entire stack without popping:");
      System.out.println(fStk);
      
      System.out.println("\nNow pop and show:");
      while ( !fStk.isEmpty() )
         System.out.println(fStk.pop());
         
      System.out.println();
   }
 }
 
 /* -------------------- RUN -------------------------
Entire stack without popping:
4.0
3.0
2.0
1.0
0.0


Now pop and show:
4.0
3.0
2.0
1.0
0.0
 --------------------------------------------- */