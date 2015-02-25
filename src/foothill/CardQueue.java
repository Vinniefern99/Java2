package foothill;

import java.lang.Exception;
import javax.swing.JOptionPane;

class CardQueue extends Queue
{
    public void addCard(Card theCard)
    {
        // don't allow pushing of Float.MIN_VALUE 
        //if (x == Float.MIN_VALUE)
        //   return;    // could throw an exception when we learn how
        // create a new FloatNode
        CardNode theCardNode = new CardNode(theCard);

        // push the StackNode onto the stack (base class call)
        super.add(theCardNode);
    }

    public Card removeCard() throws QueueEmptyException
    {
        try
        {     
            // remove a node
            CardNode theCardNode = (CardNode)remove();
            return theCardNode.getData();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, 
                    "Queue is Empty", "Exception Window",
                    JOptionPane.ERROR_MESSAGE); 
        }
        return new Card();
    }
}

class QueueEmptyException extends NullPointerException
{
}