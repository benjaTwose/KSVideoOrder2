package VideoOrderPack;


import java.awt.*;
import java.awt.event.*;
//import java.io.File;

import javax.swing.*;
 
public class SwingControlDemo {
    
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JPanel controlPanel;
   public JTextArea commentTextArea;
   public JLabel lbcntvideos;

   public SwingControlDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      SwingControlDemo  swingControlDemo = new SwingControlDemo();      
      swingControlDemo.showTextAreaDemo();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Reordenar Videos");
      mainFrame.setSize(800,900);
      // mainFrame.setLayout(new GridLayout(3,3));
      mainFrame.setLayout(new FlowLayout());
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("",JLabel.CENTER);        
      //statusLabel = new JLabel("",JLabel.CENTER);  


      //statusLabel.setSize(350,100);
      
      
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      //controlPanel.setLayout(new GridLayout(4,1,10,10));

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);  
   }

   void showTextAreaDemo(){
      headerLabel.setText("Reordena Videos"); 

      JLabel  commentlabel= new JLabel("Log:", JLabel.CENTER);

      /*final JTextArea commentTextArea = 
      *   new JTextArea("This is a Swing tutorial "
      *   +"to make GUI application in Java.",5,20);
      */
      commentTextArea = new JTextArea("This is a Swing tutorial to make GUI application in Java.",20,50);

      JScrollPane scrollPane = new JScrollPane(commentTextArea);   
      lbcntvideos = new JLabel("Progress: 0",JLabel.CENTER);

      JButton showButton = new JButton("Show");

      showButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {     
            //statusLabel.setText( commentTextArea.getText());   
        	 filesToOrder.mine2();
         }
      }); 

      controlPanel.add(commentlabel);
      controlPanel.add(scrollPane);        
      //controlPanel.add(showButton);
      controlPanel.add(lbcntvideos);
      mainFrame.pack();
      mainFrame.setVisible(true);  
   }
}