package foothill;

public class BooleanFunc implements Cloneable
{
    public static int MAX_TABLE_FOR_CLASS = 65536;
    public static int DEFAULT_TABLE_SIZE = 16;

    //truthtable is a 1-D array
    private boolean[] truthTable;
    private boolean evalReturnIfError;
    private boolean state;

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
        if (evalReturnIfError)
            this.evalReturnIfError = true;
        else
            this.evalReturnIfError = false;

        if (tableSize < 0 || tableSize > 65536)
            truthTable = new boolean[DEFAULT_TABLE_SIZE];

        truthTable = new boolean[tableSize];
    }

    //mutators
    public boolean setTruthValuesAt(int[] inputs, boolean truthValue)
    {
        if (inputs == null)
            return false;
        if (inputs.length > truthTable.length)
            return false;

        int k, currentInt;

        for (k = 0 ; k < inputs.length ; k++)
        {
            currentInt = inputs[k];
            truthTable[currentInt] = truthValue;
        }

        return true;
    }

    public boolean setTruthTableUsingTrue(int[] inputsThatProduceTrue) 
    {
        setTableToConstant(false);

        if (inputsThatProduceTrue == null)
            return false;
        if (inputsThatProduceTrue.length > truthTable.length)
            return false;

        //System.out.println(inputsThatProduceTrue.length);
        //System.out.println(truthTable.length);

        int k, currentInt;

        for (k = 0 ; k < inputsThatProduceTrue.length ; k++)
        {
            currentInt = inputsThatProduceTrue[k];

            if (currentInt >= 0 && currentInt < truthTable.length)
                truthTable[currentInt] = true;
        }

        return true;
    }

    public boolean setTruthTableUsingFalse(int[] inputsThatProduceFalse)
    {
        setTableToConstant(true);

        if (inputsThatProduceFalse == null)
            return false;
        if (inputsThatProduceFalse.length > truthTable.length)
            return false;

        int k, currentInt;

        for (k = 0 ; k < inputsThatProduceFalse.length ; k++)
        {
            currentInt = inputsThatProduceFalse[k];

            if (currentInt >= 0 && currentInt < truthTable.length)
                truthTable[currentInt] = false;
        }

        return true;
    }

    public boolean eval(int input)
    {
        if (input < 0 || input >= truthTable.length)
        {
            state = evalReturnIfError;
            return state;
        }

        state = truthTable[input];
        return state;

    }

    public boolean getState()
    {
        return state;
    }


    //private helper methods
    private void setTableToConstant(boolean value)
    {
        int k;

        for (k = 0 ; k < truthTable.length ; k++)
            truthTable[k] = value;
    }

    public Object clone() throws CloneNotSupportedException
    {
        int row;

        // always do this first -
        BooleanFunc newObject = (BooleanFunc)super.clone();

        //now do the immediate class member objects
        newObject.truthTable = new boolean[truthTable.length];
        for ( row = 0; row < truthTable.length; row++ )
            newObject.truthTable[row] = this.truthTable[row];


        return newObject;
    }

}
