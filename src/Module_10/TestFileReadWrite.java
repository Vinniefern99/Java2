package Module_10;
import java.io.*;

public class TestFileReadWrite
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      FileInputStream myFileIn;
      FileOutputStream myFileOut;
      final int SIZE = 100;
      int k, val; 
      byte[] myBytes;
    
      // build a test array of bytes  
      myBytes = new byte[SIZE];
      for (k = 0; k < SIZE; k++)
         myBytes[k] = (byte) (k);
    
      try 
      {
         // create and write the file
         myFileOut = new FileOutputStream("TestStream.dat");
         for (k = 0; k < myBytes.length; k++)
            myFileOut.write( myBytes[k] );
         myFileOut.close();
      }
      catch  (FileNotFoundException e)
      { 
         System.out.println("Ooops: We can't write the file"); 
      }
    
      try 
      {  
         // open and read the file
         myFileIn = new FileInputStream("TestStream.dat");
         for ( k=0; (val = myFileIn.read()) != -1; k++ )
            myBytes[k] = (byte)val;
         myFileIn.close();
       
         for (k=0; k < SIZE; k++)
         {
            System.out.print(myBytes[k] + " ");
            if (k % 10 == 0)
               System.out.println();
         }
      }
      catch( FileNotFoundException e)
      { 
         System.out.println("Oops. Where's the file??"); 
      }
      catch( ArrayIndexOutOfBoundsException e)
      { 
         System.out.println(
            "\nOops. We went beyond the end of the array!"); 
      }
   }
} 