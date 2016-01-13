package Module_8;

class FloatList extends List
{
   // insert in increasing numerical order
   public void insert(double x)
   {
      Node np;
      FloatNode fp;
      // create a new FloatNode
      FloatNode newfp = new FloatNode(x);
      
      // always stay one node behind the node you are testing
      for (resetCurrent(); (np = getCurrent()) != null ; iterate() )
      {
         if (np.getNext() == null)
            break; // pointing to last node in list
      
         // if x > current then we continue to search
         fp = (FloatNode)(np.getNext());
         if ( x <= fp.getData() )
            break;   // found the exact place for this float
      }
      np.insertAfter(newfp);
   }
   
   // remove the first occurance from list
   boolean remove(double x)
   {
      Node np;
      FloatNode fp;

      // always stay one node behind the node you are testing
      for (resetCurrent(); (np = getCurrent()) != null ; iterate() )
      {
         if (np.getNext() == null)
            return false; // end of list and not found

         // if f == current then we found match
         fp = (FloatNode)(np.getNext());
         if ( x == fp.getData() )
         {
            np.removeAfter();
            return true;   // we found, we removed, we return
         }
      }
      return false;
   }
}