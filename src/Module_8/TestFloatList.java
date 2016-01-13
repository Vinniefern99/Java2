package Module_8;

import java.util.*;
import java.text.*;

public class TestFloatList
{
   public static void main (String[] args)
   {
      FloatList myList = new FloatList();
      int k;
      double x;

      // build list of 10 floats, 2 at a time (1 random and 1 k*100)
      for (k = 0; k < 5; k++)
      {
         x = 1000 * (double)Math.random(); // float bet 0 and 1000
         myList.insert(x);
         myList.insert(k*100);
      }

      // should be sorted
      myList.showList();

      // remove 5 nodes (and delete them!)
      for (k = 0; k < 5; k++)
      {
         x = k*100;
         if (myList.remove(x))
            System.out.println(x + " removed");
         else 
            System.out.println(x + " not found");
     }
      myList.showList();

      if (!myList.remove (-10))  // should have no effect
         System.out.println("-10 not in list as expected.");;
      myList.showList();
   }
 }
