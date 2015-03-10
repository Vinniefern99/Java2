package Module8;

public class TestList
{
   public static void main(String[] args)
   {
      Node np;
      List myList = new List();
      int k;

      // build a data-less list by inserting 10 nodes at front
      for (k = 0; k < 10; k++)
      {
         np = new Node();
         myList.insertAfterHead(np);
      }
      myList.showList();

      // remove 5 nodes
      for (k = 0; k < 5; k++)
         myList.removeAfterHead();
      myList.showList();
   }
}