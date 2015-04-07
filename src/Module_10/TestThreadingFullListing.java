package Module_10;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//since there are Date classes in both java.util and
//java.sql, we have to use complete class names to
//make sure we avoid ambiguity.

import java.util.Date;
import java.sql.Time;

public class TestThreadingFullListing
{
 
   public static void main(String[] args)
   {
      // establish main frame in which program will run
      TypingFrame myTypingFrame 
            = new TypingFrame("Copy The String You See ...");
      myTypingFrame.setSize(400, 200);
      myTypingFrame.setLocationRelativeTo(null);
      myTypingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // show everything to the user
      myTypingFrame.setVisible(true);
   }
}

// TypingFrame class is derived from JFrame class
class TypingFrame  extends JFrame
{
   private JButton btnMyButton;
   private JTextField txtMyTextArea;
   private JLabel lblMyLabel;
   private String currentString;
   private int currentIndex;
   private String[] strings = {"i thought about you",
         "spring can really hang you up the most",
         "night in tunesia",
         "freedom jazz dance",
         "haitian divorce"};
   private JLabel lblTimer;
   TimeKeeperTask timekeeper;
   
   // TypingFrame constructor
   public TypingFrame(String title)
   {
      // pass the title up to the JFrame constructor
      super(title);
      
      currentIndex = 0;
      currentString = strings[currentIndex];

      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);  
      setLayout(layout);
      
      // 4 controls: two labels, a text field and a button
      lblMyLabel = new JLabel(currentString);
      lblTimer = new JLabel("       ");
      setTimeLabel();
      txtMyTextArea = new JTextField(30);
      btnMyButton = new JButton("Press For New String"); 
      add(lblMyLabel);
      add(txtMyTextArea);
      add(btnMyButton);
      add(lblTimer);
      btnMyButton.addActionListener( new ButtonListener() );
      txtMyTextArea.addActionListener( new TextFieldListener() );
      timekeeper = new TimeKeeperTask(this);
      startClock();
   }
   
   // inner class for JButton Listener - select new string
   class ButtonListener implements ActionListener
   {
      // event handler for JButton
      public void actionPerformed(ActionEvent e)
      {
         int newIndex;

         do
           newIndex = (int)(Math.random() * 1000) % strings.length ;
         while (newIndex == currentIndex);
         currentIndex = newIndex;
         currentString = strings[newIndex];
         lblMyLabel.setText(currentString);
         txtMyTextArea.setText("");
         txtMyTextArea.requestFocus();
         
         // start a thread to make clock tick
         startClock();         
      }
   }// end inner class ButtonListener 
   
   // inner class for JTextField Listener
   class TextFieldListener implements ActionListener
   {
      // event handler for JButton
      public void actionPerformed(ActionEvent e)
      {         
         String strUser = txtMyTextArea.getText();
         if (currentString.equals(strUser))
            lblMyLabel.setText("Correct!");
         else
            lblMyLabel.setText("Sorry.");  
         
         // stop the thread 
         stopClock();         
       }
   } // end inner class TextFieldListener
   
   void setTimeLabel()
   {
      Date date;
      Time time;
      String theTime;
      // get the time
      date = new Date();
      time = new Time(date.getTime());
      theTime = time.toString();
      lblTimer.setText( theTime );
   }
   void startClock()
   {
      // if an existing thread is running, stop it
      stopClock();
      Thread thread = new Thread(timekeeper);
      timekeeper.turnMeOn();
      thread.start();     
   }
   void stopClock()
   {
      timekeeper.turnMeOff();
   }

} // end class TypingFrame

class TimeKeeperTask implements Runnable
{
   TypingFrame parent;
   boolean active;
   final int SLEEPTIME = 200; // .2 second
   
   TimeKeeperTask(TypingFrame pnt)
   {
      parent = pnt;
      active = true;
   }
   
   public void turnMeOff()
   {
      active = false;
      try
      {
         Thread.sleep(SLEEPTIME+1); // give it time to get the message
      }
      catch (InterruptedException ex)
      {          
      }
   }
   public void turnMeOn()
   {
      active = true;
   }
  
   public void run()
   {
      while (active)
      {
         try
         {
            Thread.sleep(SLEEPTIME);
         }
         catch (InterruptedException ex)
         {          
         }
         parent.setTimeLabel(); 
      }
      // System.out.println("Stopped!");  // debug to confirm thread is stopping
   }
}