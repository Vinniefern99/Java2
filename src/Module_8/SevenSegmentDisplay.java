package Module_8;

class SevenSegmentDisplay  implements Cloneable {

    private SevenSegmentImage theImage;
    private SevenSegmentLogic theDisplay;

    public SevenSegmentDisplay() 
    {
        this(SevenSegmentImage.MIN_WIDTH,SevenSegmentImage.MIN_HEIGHT);
    }

    public SevenSegmentDisplay(int width, int height) 
    {
        theImage = new SevenSegmentImage();
        theDisplay = new SevenSegmentLogic();
        setSize(width, height);
    }

    public boolean setSize(int width, int height) 
    {
        if (theImage.setSize(width, height))
            return true;
        return false;
    }

    public void loadConsoleImage() 
    {
        theImage.clearImage();

        if (theDisplay.getValOfSeg(0))
            theImage.turnOnCellsForSegment('a');
        if (theDisplay.getValOfSeg(1))
            theImage.turnOnCellsForSegment('b');
        if (theDisplay.getValOfSeg(2))
            theImage.turnOnCellsForSegment('c');
        if (theDisplay.getValOfSeg(3))
            theImage.turnOnCellsForSegment('d');
        if (theDisplay.getValOfSeg(4))
            theImage.turnOnCellsForSegment('e');
        if (theDisplay.getValOfSeg(5))
            theImage.turnOnCellsForSegment('f');
        if (theDisplay.getValOfSeg(6))
            theImage.turnOnCellsForSegment('g');
    }

    public void consoleDisplay() 
    {
        theImage.display();
    }

    public void eval(int input) 
    {
        theDisplay.eval(input);  
    }
}