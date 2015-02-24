package foothill;

class CardQueue extends Queue
{
    public static final Card QUEUE_EMPTY = new Card();

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

    public Card removeCard()
    {
        // pop a node
        CardNode theCardNode = (CardNode)remove();
        if (theCardNode == null)
            return QUEUE_EMPTY;
        else
            return theCardNode.getData();
    }
    
    public String toString()
    {
        
    }
}