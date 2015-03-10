package Module8;

//Class Node ----------------------------------
class Node
{
// data
private Node next;

// constructor
public Node()
{
   next = null;
}

// console display
public void show()
{
   System.out.print("(generic node) ");
}

// insert after
public void insertAfter(Node newNode)
{
   if (newNode == null)
      return;
   newNode.next = next;
   next = newNode;
}

// remove after
public Node removeAfter()
{
   Node temp = next;

   if (temp != null)
      next = temp.next;
   return temp;
}

// returning the next member
public Node getNext()
{
   return next;
}
}