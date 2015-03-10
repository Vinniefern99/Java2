package Module8;

public class SevenSegmentLogic extends MultiSegmentLogic
{
   public SevenSegmentLogic() {
      setNumSegs(7);
      loadAllFuncs();
   }

   public boolean getValOfSeg(int seg) {
      if (!validSeg(seg))
         return false;
      return segs[seg].getState();
   }

   private void loadAllFuncs() {
      // we use letters, rather than arrays, to help connect with traditional
      // a, b, ... g segements

      // define all in terms of on/true
      int segA[] = { 0, 2, 3, 5, 6, 7, 8, 9, 10, 12, 14, 15 };
      int segB[] = { 0, 1, 2, 3, 4, 7, 8, 9, 10, 13 };
      int segC[] = { 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13 };
      int segD[] = { 0, 2, 3, 5, 6, 8, 9, 11, 12, 13, 14 };
      int segE[] = { 0, 2, 6, 8, 10, 11, 12, 13, 14, 15 };
      int segF[] = { 0, 4, 5, 6, 8, 9, 10, 11, 12, 14, 15 };
      int segG[] = { 2, 3, 4, 5, 6, 8, 9, 10, 11, 13, 14, 15 };

      // set error pattern to "E" through second parameter
      BooleanFunc a = new BooleanFunc(16, true);
      a.setTruthTableUsingTrue(segA);
      BooleanFunc b = new BooleanFunc(16, false);
      b.setTruthTableUsingTrue(segB);
      BooleanFunc c = new BooleanFunc(16, false);
      c.setTruthTableUsingTrue(segC);
      BooleanFunc d = new BooleanFunc(16, true);
      d.setTruthTableUsingTrue(segD);
      BooleanFunc e = new BooleanFunc(16, true);
      e.setTruthTableUsingTrue(segE);
      BooleanFunc f = new BooleanFunc(16, true);
      f.setTruthTableUsingTrue(segF);
      BooleanFunc g = new BooleanFunc(16, true);
      g.setTruthTableUsingTrue(segG);

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