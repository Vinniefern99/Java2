package foothill;

//import java.util.*;

//Class Queue ---------------------------------------
public class Queue
{
    // pointers to first and last elements in Queue
    private Node head, tail;

    // constructor
    public Queue()
    {
        head = null;
        tail = null;
    }

    public void add(Node newNode)
    {   
        if (newNode == null) 
            return;   // emergency return

        if (head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.next = newNode;
            tail = newNode;
        }
    }  

    public Node remove() throws QueueEmptyException
    {
        Node temp;
        temp = head;

        if (head != null)
        {
            head = head.next; 
            temp.next = null; // don't give client access to queue!
            return temp; 
        }
        else
            throw new QueueEmptyException();    
    }

    public String toString()
    {
        Node p;
        String returnString = "";

        // Display all the nodes in the stack
        for( p = head; p != null; p = p.next )
            if (returnString == "")
                returnString = p.toString();
            else
                returnString = returnString + " -> " + p.toString();

        return returnString;
    }
}

class QueueEmptyException extends NullPointerException
{
}