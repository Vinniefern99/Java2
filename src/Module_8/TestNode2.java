package Module_8;


public class TestNode2
{
   public static void main (String[] args)
   {
      Node n1, n2, n3, np;
      n1 = new Node();
      n2 = new Node();
      n3 = new Node();

      n1.insertAfter(n2);  // insert n2 after n1
      n2.insertAfter(n3);  // insert n3 after n2

      for (np = n1; np != null; np = np.getNext())
         np.show();
   }
 }