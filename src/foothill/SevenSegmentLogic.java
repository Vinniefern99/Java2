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

    //There's no way to create for loops to make this shorter, 
    //I tried a number of ways and it didn't work.
    void loadBooleanFuncs() throws CloneNotSupportedException
    {
        // ---------------------
        final int[] aIntArray = new int[]{1, 4, 11, 13};
        final int[] bIntArray = new int[]{5, 6, 11, 12, 14, 15};
        final int[] cIntArray = new int[]{2, 12, 14, 15};
        final int[] dIntArray = new int[]{1, 4, 7, 10, 15};
        final int[] eIntArray = new int[]{1, 3, 4, 5, 7, 9};
        final int[] fIntArray = new int[]{1, 2, 3, 13};
        final int[] gIntArray = new int[]{0, 1, 7, 12};

        final BooleanFunc bFunc0 = new BooleanFunc();
        final BooleanFunc bFunc1 = new BooleanFunc();
        final BooleanFunc bFunc2 = new BooleanFunc();
        final BooleanFunc bFunc3 = new BooleanFunc();
        final BooleanFunc bFunc4 = new BooleanFunc();
        final BooleanFunc bFunc5 = new BooleanFunc();
        final BooleanFunc bFunc6 = new BooleanFunc();

        bFunc0.setTruthTableUsingFalse(aIntArray);
        setSegment(0, bFunc0);

        bFunc1.setTruthTableUsingFalse(bIntArray);
        setSegment(1, bFunc1);

        bFunc2.setTruthTableUsingFalse(cIntArray);
        setSegment(2, bFunc2);

        bFunc3.setTruthTableUsingFalse(dIntArray);
        setSegment(3, bFunc3);

        bFunc4.setTruthTableUsingFalse(eIntArray);
        setSegment(4, bFunc4);

        bFunc5.setTruthTableUsingFalse(fIntArray);
        setSegment(5, bFunc5);

        bFunc6.setTruthTableUsingFalse(gIntArray);
        setSegment(6, bFunc6);
    }
}
