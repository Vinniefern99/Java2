package Module_9;

public class TestComparable
{
   public static void main(String[] args)
   {
      SpecialInt a = new SpecialInt(5);
      SpecialInt b = new SpecialInt(-29);
      SpecialInt c = new SpecialInt(77);
      SpecialInt x;
      
      a.compareTo(b);

      x = findLargestOfThree(a, b, c);
 
      System.out.println(x.theInt + " " );
      
    }
   
   static <E extends Comparable<? super E>>
   E findLargestOfThree(E x, E y, E z)
   {
      if (x.compareTo(y) > 0)
         return (x.compareTo(z) > 0) ? x : z;
      else
         return (y.compareTo(z) > 0) ? y : z; 
   } 
}

class MyInt implements Comparable<MyInt>
{
   public int theInt;
   public MyInt(int n) { theInt = n; }
   public int compareTo(MyInt other)
   {
      return (theInt - other.theInt);
   }
}

class SpecialInt extends MyInt
{
   public SpecialInt(int n) { super(n); }
   // etc.
}