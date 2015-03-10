package Module8;


//Class List ---------------------------------------
class List
{
 // pointer to first node in stack
 private Node head, current;
 
 // constructor
 public List()
 {
    // an empty list will have one "header" node at the front
    head = new Node();
    current = null;
 }
 
 protected void insertAfterHead(Node newNode)
 {   
    head.insertAfter(newNode);
 }  
 
 protected Node removeAfterHead()
 {
   return head.removeAfter();     
 }

 // console display
 public void showList()
 {
    System.out.println( "\n\n_____Here's the List_______\n" );
    for (current = head; (current = current.getNext()) != null; )
    {
       current.show();
       System.out.print(" ");
    }
    System.out.println( "\n\n_____That's all!_______\n" );
 }

 protected Node getCurrent()
 {
    return current;
 }

 protected void resetCurrent()
 {
    current = head;
 }

 protected Node iterate()
 {
    if (current != null)
       current = current.getNext();
    return current;
 }
}