package Module8;

public class TestSevenSegmentImage
{
    public static void main(String[] args) throws CloneNotSupportedException
    {
        System.out.println("--- Testing SevenSegmentImage ---");    
        
        SevenSegmentImage ssi = new SevenSegmentImage();
        ssi.setSize( 7, 9 );
        ssi.turnOnCellsForSegment( 'a' ); ssi.display();
        ssi.turnOnCellsForSegment( 'b' ); ssi.display();
        ssi.turnOnCellsForSegment( 'c' ); ssi.display();
        ssi.turnOnCellsForSegment( 'd' ); ssi.display();

        ssi.clearImage();
        ssi.turnOnCellsForSegment( 'e' ); ssi.display();
        ssi.turnOnCellsForSegment( 'f' ); ssi.display();
        ssi.turnOnCellsForSegment( 'g' ); ssi.display();

        ssi.clearImage();
        ssi.turnOnCellsForSegment( 'x' ); ssi.display();
        ssi.turnOnCellsForSegment( '3' ); ssi.display();
        
        /*
        SevenSegmentImage ssi = new SevenSegmentImage();
        ssi.setSize( 7, 9 );
        //ssi.turnOnCellsForSegment( 'A' ); ssi.display();
        System.out.println();
        ssi.turnOnCellsForSegment( 'a' ); ssi.display();
        System.out.println();
        //ssi.turnOnCellsForSegment( 'B' ); ssi.display();
        System.out.println();
        ssi.turnOnCellsForSegment( 'b' ); ssi.display();
        System.out.println();
        //ssi.turnOnCellsForSegment( 'C' ); ssi.display();
        System.out.println();
        ssi.turnOnCellsForSegment( 'c' ); ssi.display();
        System.out.println();
        //ssi.turnOnCellsForSegment( 'D' ); ssi.display();
        System.out.println();
        ssi.turnOnCellsForSegment( 'd' ); ssi.display();
        System.out.println();
        //ssi.turnOnCellsForSegment( 'E' ); ssi.display();
        System.out.println();
        ssi.turnOnCellsForSegment( 'e' ); ssi.display();
        System.out.println();
        //ssi.turnOnCellsForSegment( 'F' ); ssi.display();
        System.out.println();
        ssi.turnOnCellsForSegment( 'f' ); ssi.display();
        System.out.println();
        //ssi.turnOnCellsForSegment( 'G' ); ssi.display();
        System.out.println();
        ssi.turnOnCellsForSegment( 'g' ); ssi.display();
        System.out.println();
        
        */
    }

}


