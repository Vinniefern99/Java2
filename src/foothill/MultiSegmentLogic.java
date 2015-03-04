package foothill;

public class MultiSegmentLogic implements Cloneable 
{
    public static int MAX_NUM_SEGS = 1000;

    protected int numSegs;
    protected BooleanFunc[] segs;


    public MultiSegmentLogic()
    {
        this(0);
    }

    public MultiSegmentLogic(int numSegs)
    {
        this.numSegs = numSegs;
        segs = new BooleanFunc[numSegs];

    }

    //mutators
    public boolean setNumSegs(int numSegs)
    {
        if (numSegs < 0 || numSegs > MAX_NUM_SEGS)
            return false;

        int k;

        segs = null;
        segs = new BooleanFunc[numSegs];

        for (k = 0 ; k < numSegs ; k++)
            segs[k] = new BooleanFunc();

        return true;
    }

    public boolean setSegment(int segNum, BooleanFunc funcForThisSeg) 
            throws CloneNotSupportedException
    {
        if (segNum < 0 || segNum > segs.length)
            return false;

        segs[segNum] = (BooleanFunc)funcForThisSeg.clone();

        return true;
    }

    public void eval(int input)
    {
        int k;

        for (k = 0 ; k < segs.length ; k++)
            segs[k].eval(input);
    }

    public Object clone() throws CloneNotSupportedException
    {
        int row;

        // always do this first -
        MultiSegmentLogic newObject = (MultiSegmentLogic)super.clone();

        //now do the immediate class member objects
        newObject.segs = new BooleanFunc[numSegs];
        for ( row = 0; row < numSegs; row++ )
            newObject.segs[row] = this.segs[row];


        return newObject;
    }

}
