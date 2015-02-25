package foothill;

class CardQueue extends Queue
{
    public void addCard(Card theCard)
    {
        CardNode theCardNode = new CardNode(theCard);

        // push the StackNode onto the stack (base class call)
        super.add(theCardNode);
    }

    public Card removeCard() throws QueueEmptyException
    {
        CardNode theCardNode = (CardNode)remove();
        return theCardNode.getData();
    }
}
