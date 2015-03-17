package Module_9;

public class TestStack2
{
    public static void main (String[] args)
    {
        String x;
        Stack<String> sStk = new Stack<String>();

        // build the stack
        for (x = "HI"; x.length() < 15; x += " !")
            sStk.push(x);

        System.out.println("\nPop and show:");
        while ( !sStk.isEmpty() )
            System.out.println(sStk.pop());

        System.out.println();
    }
}

/* ------------------------ RUN ----------------------

Pop and show:
HI ! ! ! ! ! !
HI ! ! ! ! !
HI ! ! ! !
HI ! ! !
HI ! !
HI !
HI

----------------------------------------------------- */