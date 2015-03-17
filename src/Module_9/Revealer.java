package Module_9;
import javax.swing.*;

class Revealer
{
   static public <E> void displayGUI(E myThing)
   {
      String report = myThing.toString();
      JOptionPane.showMessageDialog(null, report, 
            "Loceff's Data Revealer", JOptionPane.PLAIN_MESSAGE);
   }
   
   static public <E> void displayConsole(E myThing)
   {
      String report = myThing.toString();
      System.out.println(" --- Loceff's Data Revealer ---");
      System.out.println(report);
      System.out.println(" ------------------------------\n\n");
   }
}