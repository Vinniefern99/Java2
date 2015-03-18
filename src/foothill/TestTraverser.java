package foothill;

class PrintString implements TraverserG<String>
{
   public void visit(String s)
   {
      System.out.print( s + " ");
   }
};

//------------------------------------------------------
public class TestTraverser
{
   
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      FHsdTree<String> sceneTree = new FHsdTree<String>();
      FHsdTreeNode<String> tn;
      PrintString g = new PrintString();
      
      // code that builds the tree identical to previous program - omitted
      
      sceneTree.traverse(g);
   }
}

/* ------------------ RUN -----------------
room table south west leg south east leg north west leg north east leg Miguel th
e human torso right arm right hand pinky ring finger middle finger index finger 
thumb left arm left hand pinky ring finger middle finger index finger thumb Lily
 the canine torso wagging tail right left paw right rear paw left front paw righ
t front paw 
---------------------------------------- */