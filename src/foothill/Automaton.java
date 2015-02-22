package foothill;
/*
class Automaton
{
    // class constants
    public final static int MAX_DISPLAY_WIDTH = 121;

    // private members 
    private boolean rules[];  // allocate rules[8] in constructor!
    private String thisGen;   // same here
    private String extremeBit; // bit, "*" or " ", implied everywhere "outside"
    private int displayWidth;  // an odd number so it can be perfectly centered

    // public constructors, mutators, etc. (need to be written)
    public Automaton(int newRule)
    {
        this.thisGen = "*";
        this.extremeBit = " ";

        setDisplayWidth(MAX_DISPLAY_WIDTH);

        rules = new boolean[8];

        setRule(newRule);
    }

    public void resetThisGen()
    {
        this.thisGen = "*";
        this.extremeBit = " ";
    }

    public boolean setRule(int newRule)
    {
        if (newRule < 0 || newRule > 255)
            return false;        

        int j, k;
        String binaryForm;

        binaryForm = String.format("%8s", Integer.toBinaryString(newRule))
                .replace(' ', '0');

        for (j = 7, k = 0 ; j >= 0 && k <= 7; j--, k++)
        {
            if (binaryForm.charAt(k) == '1')
                rules[j] = true;
            else 
                rules[j] = false;    
        }

        return true;
    }

    public boolean setDisplayWidth(int width)
    {
        if (width % 2 == 0)
            return false;

        displayWidth = width;
        return true;
    }

    public String toStringCurrentGen()
    {
        String returnString = "";
        String tempThisGen = new String(thisGen);
        int j, k, centerIndexOfMax, lengthOfThisGen, centerOfThisGen;

        centerIndexOfMax = displayWidth / 2;
        centerOfThisGen = tempThisGen.length() / 2;

        //if thisGen is too large, trim it
        if (thisGen.length() > displayWidth)
        {
            tempThisGen = new String(tempThisGen.substring
                    (centerOfThisGen - centerIndexOfMax,
                            centerOfThisGen + (centerIndexOfMax+1)));
        }

        lengthOfThisGen = tempThisGen.length();

        for (j = 0 ; j < displayWidth ; j++)
        {
            if (j == (centerIndexOfMax - (lengthOfThisGen/2)) )
            {
                for (k = 0 ; k < tempThisGen.length() ; k++){
                    returnString = returnString + tempThisGen.charAt(k);
                    j++;
                }   
            }
            if (tempThisGen.length() != displayWidth)
                returnString = returnString + extremeBit;
        }

        return returnString;
    }

    public void propagateNewGeneration()
    {
        int k;
        String nextGen = "";

        thisGen = extremeBit + extremeBit + thisGen
                + extremeBit + extremeBit;

        //I do not care about the outer two bits
        for (k = 1 ; k < thisGen.length()-1 ; k++)
        {
            if (thisGen.charAt(k-1) == '*' && thisGen.charAt(k) == '*' 
                    && thisGen.charAt(k+1) == '*')
            {
                if (rules[7] == true)
                    nextGen = nextGen + "*";
                else
                    nextGen = nextGen + " ";
            }   
            if (thisGen.charAt(k-1) == '*' && thisGen.charAt(k) == '*' 
                    && thisGen.charAt(k+1) == ' ')
            {
                if (rules[6] == true)
                    nextGen = nextGen + "*";
                else
                    nextGen = nextGen + " ";   
            }
            if (thisGen.charAt(k-1) == '*' && thisGen.charAt(k) == ' ' 
                    && thisGen.charAt(k+1) == '*')
            {
                if (rules[5] == true)
                    nextGen = nextGen + "*";
                else
                    nextGen = nextGen + " ";
            }
            if (thisGen.charAt(k-1) == '*' && thisGen.charAt(k) == ' ' 
                    && thisGen.charAt(k+1) == ' ')
            {
                if (rules[4] == true)
                    nextGen = nextGen + "*";
                else
                    nextGen = nextGen + " ";
            }
            if (thisGen.charAt(k-1) == ' ' && thisGen.charAt(k) == '*' 
                    && thisGen.charAt(k+1) == '*')
            {
                if (rules[3] == true)
                    nextGen = nextGen + "*";
                else
                    nextGen = nextGen + " ";
            }
            if (thisGen.charAt(k-1) == ' ' && thisGen.charAt(k) == '*' 
                    && thisGen.charAt(k+1) == ' ')
            {
                if (rules[2] == true)
                    nextGen = nextGen + "*";
                else
                    nextGen = nextGen + " ";
            }
            if (thisGen.charAt(k-1) == ' ' && thisGen.charAt(k) == ' ' 
                    && thisGen.charAt(k+1) == '*')
            {
                if (rules[1] == true)
                    nextGen = nextGen + "*";
                else
                    nextGen = nextGen + " ";
            }
            if (thisGen.charAt(k-1) == ' ' && thisGen.charAt(k) == ' ' 
                    && thisGen.charAt(k+1) == ' ')
            {
                if (rules[0] == true)
                    nextGen = nextGen + "*";
                else
                    nextGen = nextGen + " ";
            }
        }

        //replace thisGen with newGen
        thisGen = new String(nextGen);

        if (extremeBit == "*")
        {
            if (rules[7] == true)
                extremeBit = "*";
            else
                extremeBit = " ";
        }
        if (extremeBit == " ")
        {
            if (rules[0] == true)
                extremeBit = "*";
            else
                extremeBit = " ";
        }
    }

    //accessors not described in spec. I used them for testing
    public boolean[] getRules()
    {
        return rules;
    }

    public String getThisGen()
    {
        return thisGen;
    }

    public String getExtremeBit()
    {
        return extremeBit;
    }

    public int getDisplayWidth()
    {
        return displayWidth;
    }
}

 */

public class Automaton
{
    // class constants
    public final static int MAX_DISPLAY_WIDTH = 121;

    // private members
    private boolean rules[];  // allocate rules[8] in constructor!
    private String thisGen;   // same here
    String extremeBit; // bit, "*" or " ", implied everywhere "outside"
    int displayWidth;  // an odd number so it can be perfectly centered

    // constructor
    public Automaton(int new_rule)
    {
        rules = new boolean[8];
        if ( !SetRule(new_rule) )
            SetRule(0);
        resetFirstGen();
        setDisplayWidth(79);
    }

    public void resetFirstGen()
    {
        // create a virtual 1 in a sea of 0s:   "         *         "
        thisGen = "*";
        extremeBit = " ";
    }

    // mutators
    public boolean SetRule(int new_rule)
    {
        int bit_to_examine, k, bit_result;

        // optional filtering
        if (new_rule < 0 || new_rule > 255)
            return false;

        // nothing to filter
        for (k = 0; k < 8; k++)
        {
            bit_to_examine = 1 << k;
            bit_result = bit_to_examine & new_rule;
            if ( bit_result != 0 )
                rules[k] = true;
            else
                rules[k] = false;
        }
        return true;
    }

    public boolean setDisplayWidth(int width)
    {
        // check range
        if (width < 1 || width > MAX_DISPLAY_WIDTH)
            return false;

        // require odd parity
        if (width % 2 == 0)
            return false;

        displayWidth = width;
        return true;
    }

    public String toStringCurrentGen()
    {
        int padAmount;  // amount that we have to add to thisGen to give it full length
        // of displayLength
        int k, amountToTruncEachSide;
        String displayString = "";

        padAmount = displayWidth - thisGen.length();

        if (padAmount >= 0)
        {
            // we need to add padAmount/2 of extremeBit on each side of thisGen
            // to satisfy display string
            for (k = 0; k < padAmount/2; k++)
                displayString += extremeBit;
            displayString += thisGen;
            for (k = 0; k < padAmount/2; k++)
                displayString += extremeBit;
        }
        else
        {
            // truncate thisGen on both ends by padAmount/2 to fit display string
            amountToTruncEachSide = -(padAmount/2);  // or absolute value pad/2

            for (k = 0; k < displayWidth; k++)
                displayString += thisGen.charAt(amountToTruncEachSide + k);
        }

        return displayString;
    }

    public void propagateNewGeneration()
    {
        int tripletVal;
        String nextGen = "";
        boolean extremeBitIsOne;

        // prepare by padding left and right with two extreme bits each
        thisGen = extremeBit + extremeBit + thisGen + extremeBit + extremeBit ;

        // we have added 4 chars to the existing generation.  Now apply rule,
        // which will shorten by 1 char on each side, with a net increase of
        // 2 chars.

        // all the positions of nextGen[k] are determined by
        // thisGen[k-1], thisGen[k] and thisGen[k+1]
        // according to the rule set

        // according to the rule set
        for (int k = 1; k < thisGen.length()-1; k++)
        {
            // turn the three positions into an int, e.g., "** " -> 6
            tripletVal = 0;
            if (thisGen.charAt(k-1) == '*')
                tripletVal+=4;
            if (thisGen.charAt(k) == '*')
                tripletVal+=2;
            if (thisGen.charAt(k+1) == '*')
                tripletVal+=1;

            // now use the rule set to get the "child" of these three
            nextGen += rules[tripletVal] ? "*" : " ";
        }

        // extremeBit must be recomputed.  
        // first the boolean that answers the question
        if (extremeBit == " ")
            extremeBitIsOne = rules[0];
        else
            extremeBitIsOne = rules[7];

        // and convert the boolean to the new character, blank or *:
        extremeBit = extremeBitIsOne ? "*" : " ";

        // and finally pass the torch to the new generation
        thisGen = nextGen;
    }
}
