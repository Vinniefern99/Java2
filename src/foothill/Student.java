package foothill;

public class Student
{
    private String lastName;
    private String firstName;
    long totalPoints;
    
    // constructor requires parameters - no default supplied
    public Student( String lst, String fst, long pts)
    {
       lastName = firstName = "zz-error";
       totalPoints = 0;
       // at least require that it starts with a letter
       if (lst != null && Character.isLetter(lst.charAt(0)))
          lastName = lst;
       if (fst != null && Character.isLetter(fst.charAt(0)))
          firstName = fst;
       if (pts >= 0 && pts <= 1000)
          totalPoints = pts;    
    }
    
    public String toString()
    {
       String strStudent = lastName + ", " + firstName 
          + " Points: " + totalPoints;
       return strStudent;
    }
  
    // print out array with string as a title for the message box
    public static void printArrayConsole(String title, Student[] data)
    {  
       System.out.println("\n********** " + title + " **********\n");
       for (int k =0; k < data.length; k++)
           System.out.println( data[k] );
       System.out.println();
   }
 }