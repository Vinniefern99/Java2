package foothill;

public class Foothill
{
   public static void main (String[] args)
   {
       
      Message message1 = new Message();
      Message message2 = new Message("Vince","This is my notice of resignation. I guess it has to be longer.");
      Message message3 = new Message(null, null);
      Message message4 = new Message("", "");
      
      System.out.println(message1.toString());
      System.out.println(message2.toString());
      System.out.println(message3.toString());
      System.out.println(message4.toString());
      
      System.out.println();
      System.out.println();
      
      Email email1 = new Email();
      Email email2 = new Email("Jeffers", "Hello this is my long ass message that i want appear in my window.",
              "jing@trulia.com", "virgil@trulia.com");
      Email email3 = new Email(null, null, null, null);
      Email email4 = new Email("", "", "", "");
      
      System.out.println(email1.toString());
      System.out.println(email2.toString());
      System.out.println(email3.toString());
      System.out.println(email4.toString());
      
      System.out.println();
      System.out.println();
      
      Shweet shweet1 = new Shweet();
      Shweet shweet2 = new Shweet("Jeffers", "Hello this is my long ass message that i want appear in my window.",
              "chris_h12a@ver");
      Shweet shweet3 = new Shweet(null, null, null);
      Shweet shweet4 = new Shweet("", "", "");
      
      System.out.println(shweet1.toString());
      System.out.println(shweet2.toString());
      System.out.println(shweet3.toString());
      System.out.println(shweet4.toString());
      
   }
 }