package Module_8;

public class MultiSegmentLogic  implements Cloneable
{
    protected BooleanFunc[] segs;

    public MultiSegmentLogic() 
    {
        this(0);
    }

    public MultiSegmentLogic(int numSegs) 
    {
        setNumSegs(numSegs);
    }

    //mutators
    public boolean setNumSegs(int numSegs) 
    {
        if (numSegs < 0)
            return false;

        segs = new BooleanFunc[numSegs];
        for (int k = 0; k < segs.length; k++)
            segs[k] = new BooleanFunc();

        return true;
    }

    public boolean setSegment(int segNum, BooleanFunc funcForThisSeg) 
    {
        if (!validSeg( segNum))
            return false;

        // cloning object so we can pass in anon/temporary BooleanFunc
        try {
            segs[segNum] = (BooleanFunc)funcForThisSeg.clone();
        } 
        catch ( CloneNotSupportedException e) 
        {
            return false;
        }
        return true;
    }

    public void eval(int input) 
    {
        for (int k = 0; k < segs.length; k++)
            segs[k].eval(input);
    }

    public Object clone() throws CloneNotSupportedException 
    {
        // array will temporarily point to original object
        MultiSegmentLogic newMsl = (MultiSegmentLogic) super.clone();

        newMsl.segs = new BooleanFunc[segs.length];
        for (int k = 0; k < segs.length; k++)
            newMsl.segs[k] = (BooleanFunc)this.segs[k].clone();
        return newMsl;
    }

    // helpers
    protected boolean validSeg(int seg) 
    {
        return seg >= 0 && seg < segs.length;
    }
}
