package foothill;

//import java.util.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

//Class Node  ----------------------------------
public class Node
{
    
  // data (we allow Queue class public access)
  protected Node next;

  // constructor
  public Node()
  {
      next = null;
  }

  // console display
  public String toString()
  {
      //Date date = new Date();
      //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      /*
      try {
          Thread.sleep(1000);                 //1000 milliseconds is one second.
      } catch(InterruptedException ex) {
          Thread.currentThread().interrupt();
      }
      */
      String returnString =  "(generic node)"/* + dateFormat.format(date)*/;

      return returnString;
  }
}
