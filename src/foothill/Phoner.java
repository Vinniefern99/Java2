package foothill;

public class Phoner
{
   public static void main (String[] args)
   {
      Phone me = new Phone("800", "1234567"), you = new Phone();
      
      you.setPhone("415", "5551234");
      me.showPhone();
      you.showPhone();
   }
 }

// Base Class Phone  -----------------------------
class Phone
{
   private  String areaCode;
   private  String number;
    
   public Phone()
   {
      setPhone();
   }
   public Phone(String ac, String num)
   {
      if ( !setPhone(ac, num) )
         setPhone();  // use defaults if bad values passed
   }
   public void setPhone()
   {
      setPhone("000", "0000000");
   }
   public boolean setPhone(String ac, String num)
   {
      if (ac.length() == 3 && isNumber(ac) 
            && num.length() == 7 && isNumber(num) )
         {
            number = num;
            areaCode = ac;
            return true;
         }
      return false;
   }
   public String getAreaCode()
   {
      return areaCode;
   }
   public String getNumber()
   {
      return number;
   }
   public String toString()
   {
      String result;
      result = "(" + areaCode + ")"
         + number.substring(0,3) + "-"
         + number.substring(3,7);
      return result;
  }
   public void showPhone()
   {
      System.out.println(toString());
   }      
   protected static boolean isNumber(String s)
   {
      for(int k = 0; k < s.length(); k++)
         if (!Character.isDigit(s.charAt(k)))
            return false;
      return true;
  }
}