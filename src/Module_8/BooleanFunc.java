package Module_8;

public class BooleanFunc implements Cloneable
{
    public static final int MAX_TABLE_FOR_CLASS = 65536; 
    public static final int DEFAULT_TABLE_SIZE = 16;

    //truthtable is a 1-D array
    private boolean[] truthTable;
    private boolean evalReturnIfError;
    private boolean state;

    // constructors
    public BooleanFunc() 
    {
        this(DEFAULT_TABLE_SIZE, false);
    }

    public BooleanFunc(int tableSize) 
    {
        this(tableSize, false);
    }

    public BooleanFunc(int tableSize, boolean evalReturnIfError) 
    {
        // deal with construction errors in a crude but simple fashion
        if (tableSize > MAX_TABLE_FOR_CLASS || tableSize < 1)
            tableSize = DEFAULT_TABLE_SIZE;
        truthTable = new boolean[tableSize];
        this.evalReturnIfError = evalReturnIfError;
        this.state = evalReturnIfError;
    }

    // mutators, accessors
    public boolean setTruthTableUsingTrue(int[] inputsThatProduceTrue) 
    {
        setTableToConstant(false);
        return setTruthValuesAt(inputsThatProduceTrue, true);
    }
    public boolean setTruthTableUsingFalse(int[] inputsThatProduceFalse) 
    {
        setTableToConstant(true);
        return setTruthValuesAt(inputsThatProduceFalse, false);
    }

    public boolean setTruthValuesAt(int[] tableLocs, boolean truthValue) 
    {
        if (tableLocs == null || tableLocs.length > truthTable.length )
            return false;

        for (int i = 0; i < tableLocs.length; i++) 
        {
            final int j = tableLocs[i];
            if (j >= 0 && j < truthTable.length)
                truthTable[j] = truthValue;
        }
        return true;
    }

    public boolean eval(int input) 
    {
        if (!inputInRange(input))
            return (state = evalReturnIfError);
        return (state = truthTable[input]);
    }

    public boolean getState() 
    {
        return state;
    }

    // deep copy required
    public Object clone() throws CloneNotSupportedException 
    {
        // always do this first 
        // array will temporarily point to original object
        BooleanFunc newBf = (BooleanFunc) super.clone();

        //now do the immediate class member objects
        newBf.truthTable = new boolean[truthTable.length];
        for (int k = 0; k < truthTable.length; k++)
            newBf.truthTable[k] = this.truthTable[k];
        return newBf;
    }

    // helpers
    private void setTableToConstant(boolean value) 
    {
        for (int k = 0; k < truthTable.length; k++)
            truthTable[k] = value;
    }

    private boolean inputInRange(int input) 
    {
        return input >= 0 && input < truthTable.length;
    }
}
