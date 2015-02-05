package foothill;

//imports go at the top of whatever .java file this appears.
import java.util.*;
import java.text.*;

class FloatNode extends StackNode
{
// additional data for subclass
private float data;

// constructor
public FloatNode(float x)
{
   super();  // constructor call to base class
   data = x;
}

// accessor
public float getData()
{
   return data;
}

// overriding show()
public void show()
{
   NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   tidy.setMaximumFractionDigits(4);
   
   System.out.print("[" + tidy.format(data) + "] ");
}
}