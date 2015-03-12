package Module8;

public class SevenSegmentImage implements Cloneable 
{
    public static final int MIN_HEIGHT = 5;
    public static final int MIN_WIDTH = 3;
    public static final int MAX_HEIGHT = 65;
    public static final int MAX_WIDTH = 40;
    public static final String DRAW_CHAR = "*";
    public static final String BLANK_CHAR = " ";

    private boolean[][] data;
    private int topRow, midRow, bottomRow, leftCol, rightCol;

    public SevenSegmentImage() 
    {
        this(MIN_WIDTH, MIN_WIDTH);
    }

    public SevenSegmentImage(int width, int height) 
    {
        //topRow and leftCol are always 0
        topRow = 0;
        leftCol = 0;

        if (!(setSize(width, height)))
        {
            bottomRow = MIN_HEIGHT - 1;
            rightCol = MIN_WIDTH - 1;
            midRow = (MIN_HEIGHT - 1) / 2;
        }
    }

    public void clearImage() 
    {
        int j, k;

        //I'm not sure if this is right...check it!
        for (j = 0 ; j <= bottomRow; j++)
            for (k = 0 ; k <= rightCol; k++)
                data[j][k] = false;
    }

    public boolean turnOnCellsForSegment(char segment) 
    {
        //String charOptions = "0123456789AaBbCcDdEeFfGg";
        String charOptions = "AaBbCcDdEeFfGg";

        if (segment == ' ' || charOptions.indexOf(segment) < 0)
            return false;

        switch (segment)
        {
            case 'A':
            case 'a':   
                drawHorizontal(topRow);
                break;
            case 'B':
            case 'b':   
                drawVertical(rightCol, topRow, midRow);
                break;
            case 'C':
            case 'c':   
                drawVertical(rightCol, midRow, bottomRow);
                break;
            case 'D':
            case 'd':   
                drawHorizontal(bottomRow);
                break;
            case 'E':
            case 'e':   
                drawVertical(leftCol, midRow, bottomRow);
                break;
            case 'F':
            case 'f':   
                drawVertical(leftCol, topRow, midRow);
                break;
            case 'G':
            case 'g':   
                drawHorizontal(midRow);
                break;
            default: return false; //if no match. shouldn't ever happen
        }

        return true;
    }

    public boolean setSize(int width, int height) 
    {
        //if width and/or height are invalid, send back to constructor
        //I'm requiring that both be valid, not just one
        if (!(validateSize(width, height)))
            return false;

        bottomRow = height - 1;
        rightCol = width - 1;
        midRow = (height - 1) / 2;

        allocateCleanArray(); 

        return true;
    }

    public void display() 
    {
        for (int k = 0 ; k <= bottomRow; k++)
        {
            for (int j = 0 ; j <=  rightCol; j++)
            {
                if (data[k][j] == false)
                    System.out.print(" ");
                else
                    System.out.print("*");

            }
            System.out.println();
        }           
    }

    // deep copy required
    public Object clone() throws CloneNotSupportedException 
    {
        // array will temporarily point to original object
        final SevenSegmentImage newSsi = (SevenSegmentImage) super.clone();

        newSsi.data = new boolean[rightCol+1][bottomRow+1];
        for (int j = 0 ; j <= bottomRow; j++)
            for (int k = 0 ; k <= rightCol; k++)
                newSsi.data[j][k] = this.data[j][k];
        return newSsi;
    }

    private boolean validateSize(int width, int height) 
    {
        boolean isValid = true;

        //test width
        if (width < MIN_WIDTH || width > MAX_WIDTH)
            isValid = false;

        //test height
        if (height % 2 == 0 || height < MIN_HEIGHT || height > MAX_HEIGHT)
            isValid = false;

        return isValid;
    }

    private void allocateCleanArray() 
    {
        data = new boolean[bottomRow+1][rightCol+1];
        clearImage();
    }

    // helpers - not required, but used by instructor
    void drawHorizontal(int row) {
        int k;

        for (k = 0 ; k <= rightCol ; k++)
            data[row][k] = true;
    }

    void drawVertical(int col, int startRow, int stopRow) 
    {
        for (int k = startRow ; k <= stopRow ; k++)
            data[k][col] = true;
    }
}