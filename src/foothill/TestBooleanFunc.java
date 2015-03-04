package foothill;

public class TestBooleanFunc
{

    public static void main(String[] args)
    {
        BooleanFunc segA, segB, segC;

        int  inputX, k;

        int evenFunc[] = { 0, 2, 4, 6, 8, 10, 12, 14 };
        int greater9Func[] = { 10, 11, 12, 13, 14, 15 };
        int greater3Func[] = { 0, 1, 2, 3 };
        
        segA = new BooleanFunc();
        segB = new BooleanFunc( 13 );
        segC = new BooleanFunc( 100, true );

        segA.setTruthTableUsingTrue( evenFunc );
        segB.setTruthTableUsingTrue( greater9Func );
        segC.setTruthTableUsingFalse( greater3Func );

        // testing class BooleanFunc
        System.out.println( "before eval()" );
        System.out.println(
           "\n  A(x) = "
           + segA.getState()
           + "\n  B(x) = "
           + segB.getState()
           + "\n  C(x) = "
           + segC.getState()
           + "\n" );
        System.out.println( "looping with eval()" );

        for ( inputX = 0; inputX < 10; inputX++ )
        {
           segA.eval( inputX );
           segB.eval( inputX );
           segC.eval( inputX );
           System.out.println(
              "Input: " + inputX
              + "\n  A(x) = "
              + segA.getState()
              + "\n  B(x) = "
              + segB.getState()
              + "\n  C(x) = "
              + segC.getState()
              + "\n" );
        }
     }


}
