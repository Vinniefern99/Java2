package Module_8;

import java.util.*;
import java.text.*;

public class TestFloatNode
{
   public static void main (String[] args)
   {
      FloatNode fp;
      List myList = new List();
      int k;

      // build a data-less list by inserting 10 nodes at front
      for (k = 0; k < 10; k++)
      {
         fp = new FloatNode(k);
         myList.insertAfterHead(fp);
      }
      myList.showList();

      // remove 5 nodes
      for (k = 0; k < 5; k++)
         myList.removeAfterHead();   
      myList.showList();
   }
 }
