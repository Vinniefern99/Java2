package Module_9;

public class TestGenericMethods
{
   public static void main (String[] args)
   {
      Float[] fltArray = new Float[10];
      String[] strArray = new String[10];
      
      loadArray(fltArray, new Float(-3.5));
      printArray(fltArray);
      
      loadArray(strArray, new String("Can anyone hear me?"));
      printArray(strArray);
   }
   
   static <E> void printArray(E[] array)
   {
      System.out.println("The First International List of Loceff");
      for (int k = 0; k < array.length; k++)
         System.out.println("<---|" + array[k] + "|--->");
      System.out.println("************ END LIST ***********");
  }

   static <E> void loadArray(E[] array, E value)
   {
      for (int k = 0; k < array.length; k++)
         array[k] = value;
   }
 }

/* --------------------- Run ------------------------

The First International List of Loceff
<---|-3.5|--->
<---|-3.5|--->
<---|-3.5|--->
<---|-3.5|--->
<---|-3.5|--->
<---|-3.5|--->
<---|-3.5|--->
<---|-3.5|--->
<---|-3.5|--->
<---|-3.5|--->
************ END LIST ***********
The First International List of Loceff
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
<---|Can anyone hear me?|--->
************ END LIST ***********

---------------------------------------------- */