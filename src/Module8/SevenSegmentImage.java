package Module8;

public class SevenSegmentImage implements Cloneable {
    public static final int MIN_HEIGHT = 3;
    public static final int MIN_WIDTH = 5;
    public static final int MAX_HEIGHT = 40;
    public static final int MAX_WIDTH = 65;
    public static final String DRAW_CHAR = "*";
    public static final String BLANK_CHAR = " ";

    private boolean[][] data;
    private int topRow, midRow, bottomRow, leftCol, rightCol;

    public SevenSegmentImage() {
       ...
    }

    public SevenSegmentImage(int width, int height) {
       ...
    }

    public void clearImage() {
       ...
    }

    public boolean turnOnCellsForSegment(char segment) {
        ...
    }

    public boolean setSize(int width, int height) {
       ...
    }

    public void display() {
       ...
    }

    // deep copy required
    public Object clone() throws CloneNotSupportedException {
       ...
    }

    private boolean validateSize(int width, int height) {
       ...
    }

    private void allocateCleanArray() {
       ...
    }

    // helpers - not required, but used by instructor
    void drawHorizontal(int row) {
       ...
    }

    void drawVertical(int col, int startRow, int stopRow) {
       ...
    }
 }