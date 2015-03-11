package Module8;

public class TestSevenSegmentDisplay
{

    public static void main(String[] args)
    {
        SevenSegmentDisplay my7SegForCon = new SevenSegmentDisplay(15, 13);
        int j;
        
        System.out.println(
           "Testing SevenSegmentDisplay ===================================");

        my7SegForCon.setSize( 7, 9 );
        for ( j = 0; j < 16; j++ )
        {
           my7SegForCon.eval( j );
           my7SegForCon.loadConsoleImage();
           my7SegForCon.consoleDisplay();
           System.out.println();
        }

        for ( j = 5; j < 21; j += 4)
        {
           my7SegForCon.setSize( j, 2*j + 1 );
           my7SegForCon.eval( 5 );
           my7SegForCon.loadConsoleImage();
           my7SegForCon.consoleDisplay();
        }

    }

}
