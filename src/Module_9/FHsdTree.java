package Module_9;

public class FHsdTree<E> implements Cloneable
{
    private int mSize;
    private int pSize;
    FHsdTreeNode<E> mRoot;

    //used by collectGarbage()
    boolean returnVal;

    public FHsdTree() 
    { 
        clear(); 
    }

    public boolean empty() 
    { 
        return (mSize == 0); 
    }

    public int size() 
    { 
        return mSize; 
    }

    public int sizePhysical()
    {
        return pSize;
    }

    public void clear() 
    { 
        mSize = 0; 
        mRoot = null; 
    }

    public FHsdTreeNode<E> find(E x) 
    { 
        return find(mRoot, x, 0); 
    }

    public boolean remove(E x) 
    { 
        return remove(mRoot, x); 
    }

    //I have some boolean logic here in order to have collectGarbage()
    //return the correct value. I tried doing this in the overloaded
    //method and it got very hairy.
    public boolean collectGarbage()
    {
        returnVal = false;
        final boolean temp = collectGarbage(mRoot,0);
        returnVal = false;
        pSize = mSize;
        return temp;
    }

    public void  display()  
    { 
        display(mRoot, 0); 
    }

    public void  displayPhysical()  
    { 
        displayPhysical(mRoot, 0); 
    }

    public <F extends Traverser<? super E>> 
    void traverse(F func)  
    { 
        traverse(func, mRoot, 0); 
    }

    public FHsdTreeNode<E> addChild( FHsdTreeNode<E> treeNode,  E x )
    {
        // empty tree? - create a root node if user passes in null
        if (mSize == 0)
        {
            if (treeNode != null)
                return null; // error something's fishy.  treeNode can't right
            mRoot = new FHsdTreeNode<E>(x, null, null, null, false);
            mRoot.myRoot = mRoot;
            mSize = 1;
            pSize = 1;
            return mRoot;
        }

        if (treeNode == null || treeNode.deleted)
            return null; // error inserting into non_null tree w/ null parent
        if (treeNode.myRoot != mRoot)
            return null;  // silent error, node does not belong to this tree

        //push node into the head of the sibling list; adjust prev pointers
        FHsdTreeNode<E> newNode = new FHsdTreeNode<E>(x, 
                treeNode.firstChild, null, treeNode, mRoot, false);  
        treeNode.firstChild = newNode;
        if (newNode.sib != null)
            newNode.sib.prev = newNode;
        ++mSize;
        ++pSize;
        return newNode;  
    }

    public FHsdTreeNode<E> find(FHsdTreeNode<E> root, E x, int level)
    {
        FHsdTreeNode<E> retval;

        if (mSize == 0 || root == null)
            return null;

        if (root.data.equals(x))
            return root;

        // otherwise, recurse. don't process sibs if this was the original call
        if ( level > 0 && (retval = find(root.sib, x, level)) != null )
            return retval;

        if (root.deleted)
            return null;

        return find(root.firstChild, x, ++level);
    }

    public boolean remove(FHsdTreeNode<E> root, E x)
    {
        FHsdTreeNode<E> tn = null;

        if (mSize == 0 || root == null)
            return false;

        if ( (tn = find(root, x, 0)) != null )
        {
            tn.deleted = true;
            //removeNode(tn);
            //mSize--;
            mSize = 0;
            calculateMSize(mRoot, 0);
            return true;
        }
        return false;
    }

    public void calculateMSize(FHsdTreeNode<E> root, int level)
    {
        if (root == null)
            return;

        if (level == 0 && root.deleted)
            return;

        mSize++;

        if (level > 0)
            calculateMSize(root.sib, level);

        if (root.deleted)
        {
            mSize--;
            return;
        }
        else
            calculateMSize(root.firstChild, ++level);
    }

    private void removeNode(FHsdTreeNode<E> nodeToDelete )
    {
        if (nodeToDelete == null || mRoot == null)
            return;
        if (nodeToDelete.myRoot != mRoot)
            return;  // silent error, node does not belong to this tree

        // remove all the children of this node
        while (nodeToDelete.firstChild != null)
            removeNode(nodeToDelete.firstChild);

        if (nodeToDelete.prev == null)
            mRoot = null;  // last node in tree
        else if (nodeToDelete.prev.sib == nodeToDelete)
            nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
        else
            nodeToDelete.prev.firstChild = nodeToDelete.sib;  // adjust parent

        // adjust the successor sib's prev pointer
        if (nodeToDelete.sib != null)
            nodeToDelete.sib.prev = nodeToDelete.prev;
    }


    boolean collectGarbage(FHsdTreeNode<E> root, int level)
    {        
        if (root == null)
            return false;

        if (level == 0 && root.deleted)
        {
            removeNode(root);
            return true;
        }

        if (level > 0)
            collectGarbage(root.sib, level);

        if (root.deleted)
        {
            returnVal = true;
            removeNode(root); 
        }

        collectGarbage(root.firstChild, ++level);

        return returnVal;
    }

    public Object clone() throws CloneNotSupportedException
    {
        FHsdTree<E> newObject = (FHsdTree<E>)super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;
        newObject.setMyRoots(newObject.mRoot);

        return newObject;
    }

    private FHsdTreeNode<E> cloneSubtree(FHsdTreeNode<E> root)
    {
        FHsdTreeNode<E> newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new FHsdTreeNode<E>
        (root.data, cloneSubtree(root.sib), cloneSubtree(root.firstChild), 
                null, false);

        // the prev pointer is set by parent recursive call, this is the code:
        if (newNode.sib != null)
            newNode.sib.prev = newNode;
        if (newNode.firstChild != null)
            newNode.firstChild.prev = newNode;
        return newNode;
    }

    // recursively sets all myRoots to mRoot
    private void setMyRoots(FHsdTreeNode<E> treeNode)
    {
        if (treeNode == null)
            return;

        treeNode.myRoot = mRoot;
        setMyRoots(treeNode.sib);
        setMyRoots(treeNode.firstChild);
    }

    // define this as a static member so recursive display() does not need
    // a local version
    final static String blankString = "                                    ";

    // let be public so client can call on subtree
    public void  display(FHsdTreeNode<E> treeNode, int level) 
    {
        String indent;

        // stop runaway indentation/recursion
        if  (level > (int)blankString.length() - 1)
        {
            System.out.println( blankString + " ... " );
            return;
        }

        if (treeNode == null)
            return;

        indent = blankString.substring(0, level);

        // pre-order processing done here ("visit")
        if (!treeNode.deleted)
            System.out.println( indent + treeNode.data ) ;

        // recursive step done here
        if (!treeNode.deleted)
            display( treeNode.firstChild, level + 1 );

        if (level > 0 )
            display( treeNode.sib, level );
    }

    // let be public so client can call on subtree
    public void  displayPhysical(FHsdTreeNode<E> treeNode, int level) 
    {
        String indent;

        // stop runaway indentation/recursion
        if  (level > (int)blankString.length() - 1)
        {
            System.out.println( blankString + " ... " );
            return;
        }

        if (treeNode == null)
            return;

        indent = blankString.substring(0, level);

        // pre-order processing done here ("visit")
        if (treeNode.deleted)
            System.out.println( indent + treeNode.data + " (D)") ;
        else
            System.out.println( indent + treeNode.data ) ;

        // recursive step done here
        displayPhysical( treeNode.firstChild, level + 1 );
        if (level > 0 )
            displayPhysical( treeNode.sib, level );
    }

    // often helper of typical public version, but also callable by on subtree
    public <F extends Traverser<? super E>> 
    void traverse(F func, FHsdTreeNode<E> treeNode, int level)
    {
        if (treeNode == null)
            return;

        func.visit(treeNode.data);

        // recursive step done here
        traverse( func, treeNode.firstChild, level + 1);
        if (level > 0 )
            traverse( func,  treeNode.sib, level);
    }
}
