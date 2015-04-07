package Module_10;
import java.io.*;
import java.text.*;
import java.util.*;

//------------------------------------------------------
// FileLeaner class -----------------------------------
public class TestDataStreamsPrimitiveData
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      DataInputStream myFileIn;
      DataOutputStream myFileOut;
      final int SIZE = 80;
      int k;
      float[] myFloats;
      NumberFormat tidy = NumberFormat.getNumberInstance(Locale.US);
      tidy.setMaximumFractionDigits(2);
      tidy.setMinimumFractionDigits(2);
      tidy.setMinimumIntegerDigits(1);
   
      // ------- build a test array of floats 
      myFloats = new float[SIZE];
      for (k = 0; k < myFloats.length; k++)
         myFloats[k] = (float) (Math.random() / (Math.random() + 1) );
   
      try
      { 
         // ------- create and write the file
         myFileOut = new DataOutputStream( 
            new FileOutputStream("TestStream.dat") );
         for (k = 0; k < myFloats.length; k++)
            myFileOut.writeFloat( myFloats[k] );
         myFileOut.close();
    
         // ------- open and read the file
         myFileIn = new DataInputStream( 
            new FileInputStream("TestStream.dat") );
         for ( k = 0; myFileIn.available() > 0; k++ )
            myFloats[k] = myFileIn.readFloat();
         myFileIn.close();
    
         // ------- show the user
         for (k = 0; k < myFloats.length; k++)
         {
            System.out.print(tidy.format(myFloats[k]) + " ");
            if (k % 10 == 9)
               System.out.println();
         }
      }
      catch( FileNotFoundException e)
      {
         System.out.println("\nOops. Problem opening the file!");
      }  
   }
} 