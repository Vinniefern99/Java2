package Module8;

public class SevenSegmentLogic extends MultiSegmentLogic
{
    public static final int NUM_SEGS = 7;
    
    public SevenSegmentLogic() 
    {
        setNumSegs(NUM_SEGS);
        loadAllFuncs();
    }

    public boolean getValOfSeg(int seg) 
    {
        if (!validSeg(seg))
            return false;
        return segs[seg].getState();
    }

    private void loadAllFuncs() 
    {
        // define all in terms of on/true
        final int aIntArray[] = { 0, 2, 3, 5, 6, 7, 8, 9, 10, 12, 14, 15 };
        final int bIntArray[] = { 0, 1, 2, 3, 4, 7, 8, 9, 10, 13 };
        final int cIntArray[] = { 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13 };
        final int dIntArray[] = { 0, 2, 3, 5, 6, 8, 9, 11, 12, 13, 14 };
        final int eIntArray[] = { 0, 2, 6, 8, 10, 11, 12, 13, 14, 15 };
        final int fIntArray[] = { 0, 4, 5, 6, 8, 9, 10, 11, 12, 14, 15 };
        final int gIntArray[] = { 2, 3, 4, 5, 6, 8, 9, 10, 11, 13, 14, 15 };

        // set error pattern to "E" through second parameter
        final BooleanFunc a = new BooleanFunc(16, true);
        a.setTruthTableUsingTrue(aIntArray);
        final BooleanFunc b = new BooleanFunc(16, false);
        b.setTruthTableUsingTrue(bIntArray);
        final BooleanFunc c = new BooleanFunc(16, false);
        c.setTruthTableUsingTrue(cIntArray);
        final BooleanFunc d = new BooleanFunc(16, true);
        d.setTruthTableUsingTrue(dIntArray);
        final BooleanFunc e = new BooleanFunc(16, true);
        e.setTruthTableUsingTrue(eIntArray);
        final BooleanFunc f = new BooleanFunc(16, true);
        f.setTruthTableUsingTrue(fIntArray);
        final BooleanFunc g = new BooleanFunc(16, true);
        g.setTruthTableUsingTrue(gIntArray);

        // this block could be combined with above; done separately for clarity
        setSegment(0, a);
        setSegment(1, b);
        setSegment(2, c);
        setSegment(3, d);
        setSegment(4, e);
        setSegment(5, f);
        setSegment(6, g);
    }
}