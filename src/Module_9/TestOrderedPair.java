package Module_9;

import java.util.*;

public class TestOrderedPair
{
    public static void main (String[] args)
    {
        OrderedPair<Integer, Integer> intPr;
        OrderedPair<String, Double> mixedPr;

        intPr = new OrderedPair<Integer, Integer>(3, -59);
        mixedPr = new OrderedPair<String, Double>("teach", 21.96);

        System.out.println("Individual pairs: " + intPr + mixedPr);

        // we can't create arrays of generics, but we can create collections
        ArrayList<OrderedPair<String, Double>> mixedPrArray 
        = new ArrayList<OrderedPair<String, Double>>();

        // build the mixed pair array and show
        for (int k = 0; k < 10; k++)
        {
            mixedPrArray.add( 
                    new OrderedPair<String, Double>("CIS " + k , k/10.)
                    );
        }
        System.out.println("Mixed Pair Array: "); 
        for (int k = 0; k < 10; k++)
            System.out.println( mixedPrArray.get(k));
    }
}

/* ----------------------- Run ------------------------

Individual pairs: (3, -59) (teach, 21.96) 
Mixed Pair Array: 
(CIS 0, 0.0) 
(CIS 1, 0.1) 
(CIS 2, 0.2) 
(CIS 3, 0.3) 
(CIS 4, 0.4) 
(CIS 5, 0.5) 
(CIS 6, 0.6) 
(CIS 7, 0.7) 
(CIS 8, 0.8) 
(CIS 9, 0.9) 

------------------------------------------------------- */