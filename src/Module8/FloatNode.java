package Module8;

//imports go at the top of whatever .java file this appears.
import java.util.*;
import java.text.*;

class FloatNode extends Node
{
// additional data for subclass
private double data;

// constructor
public FloatNode(double x)
{
   super();  // constructor call to base class
   data = x;
}

// overriding show()
public void show()
{
   NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   tidy.setMaximumFractionDigits(4);
   
   System.out.print("[" + tidy.format(data) + "] ");
}

// accessor and mutator
public double getData()
{
   return data;
}

public boolean setData(double usrData)
{
   // if there were a viable limitation we would test and return false if bad
   data = usrData;
   return true;
}
}