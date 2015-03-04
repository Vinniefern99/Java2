package foothill;

public class SevenSegmentLogic extends MultiSegmentLogic
{
    public static int NUM_SEGS = 7;
    
    public SevenSegmentLogic() throws CloneNotSupportedException
    {
        super(NUM_SEGS);
        loadBooleanFuncs();
        
    }

    public boolean getValOfSeg(int seg)
    {
        if (seg < 0 || seg > NUM_SEGS)
            return false;
        
        return segs[seg].getState();
        
    }
    
    
    void loadBooleanFuncs() throws CloneNotSupportedException
    {
        BooleanFunc bFunc = new BooleanFunc();
        
        int[] aIntArray = new int[]{1, 4, 11, 13};
        int[] bIntArray = new int[]{5, 6, 11, 12, 14, 15};
        int[] cIntArray = new int[]{2, 12, 14, 15};
        int[] dIntArray = new int[]{1, 4, 7, 10, 15};
        int[] eIntArray = new int[]{1, 3, 4, 5, 7, 9};
        int[] fIntArray = new int[]{1, 2, 3, 13};
        int[] gIntArray = new int[]{0, 1, 7, 12};
        
        
        bFunc.setTruthTableUsingFalse(aIntArray);
        setSegment(0, bFunc);
        
        bFunc.setTruthTableUsingFalse(bIntArray);
        setSegment(1, bFunc);
        
        bFunc.setTruthTableUsingFalse(cIntArray);
        setSegment(2, bFunc);
        
        bFunc.setTruthTableUsingFalse(dIntArray);
        setSegment(3, bFunc);
        
        bFunc.setTruthTableUsingFalse(eIntArray);
        setSegment(4, bFunc);
        
        bFunc.setTruthTableUsingFalse(fIntArray);
        setSegment(5, bFunc);
        
        bFunc.setTruthTableUsingFalse(gIntArray);
        setSegment(6, bFunc);
          
    }
}
