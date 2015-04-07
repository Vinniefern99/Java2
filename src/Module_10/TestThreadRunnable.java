package Module_10;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestThreadRunnable
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
class TypingFrame extends JFrame
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
      
      // 3 controls: a label, a text field and a button
      lblMyLabel = new JLabel(currentString);
      txtMyTextArea = new JTextField(30);
      btnMyButton = new JButton("Press For New String"); 
      add(lblMyLabel);
      add(txtMyTextArea);
      add(btnMyButton);
      btnMyButton.addActionListener( new ButtonListener() );
      txtMyTextArea.addActionListener( new TextFieldListener() );
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
       }
   } // end inner class TextFieldListener
   
} // end class TypingFrame