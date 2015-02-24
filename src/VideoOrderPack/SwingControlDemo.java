package VideoOrderPack;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class SwingControlDemo {
    
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel headerLabel2;
   private JLabel headerLabel3;
   private JPanel controlPanel;
   private JPanel logPanel;
   
   private JPanel headerPanel;
   
   public JTextArea commentTextArea;
   public JLabel lbcntvideos;
   public boolean initwalk=false; 
   public JTextField txtpathToSearch;
   

   public SwingControlDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      SwingControlDemo  swingControlDemo = new SwingControlDemo();      
      swingControlDemo.showTextAreaDemo();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Reordenar Videos");
      mainFrame.setSize(600,700);
      //mainFrame.setLayout(new GridLayout(2,2,10,10));
      mainFrame.setLayout(new FlowLayout());
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("",JLabel.LEADING);
      headerLabel.setFont(new Font(headerLabel.getName(), Font.PLAIN, 20));;
      headerLabel2 = new JLabel("",JLabel.LEADING); 
      headerLabel3 = new JLabel("",JLabel.LEADING);
      txtpathToSearch = new JTextField("D:\\FTPVideos");
      txtpathToSearch.setEnabled(true);
      
      JButton showButton = new JButton("Ordenar NOW");

      showButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {     
        	 initwalk=true;
        	 
         }
      }); 
      logPanel = new JPanel();
      controlPanel = new JPanel();
      headerPanel = new JPanel();
      //controlPanel.setLayout(new FlowLayout());
      //headerPanel.setLayout(new FlowLayout(3,5,10));
      headerPanel.setLayout(new GridLayout(0,1));
      controlPanel.setLayout(new GridLayout(0,1));
      logPanel.setLayout(new GridLayout(2,2));
      headerPanel.add(headerLabel);
      headerPanel.add(headerLabel2);
      headerPanel.add(headerLabel3);
      
      controlPanel.add(txtpathToSearch);
      mainFrame.add(headerPanel);
      mainFrame.add(controlPanel);      
      controlPanel.add(showButton);
      
      //mainFrame.pack();
      mainFrame.setVisible(true);  
   }

   void showTextAreaDemo(){
      headerLabel.setText("REORDENAR VIDEOS"); 
      headerLabel2.setText("Launcher diari a las 4:00 AM"); 
      headerLabel3.setText("Status: "); 

      commentTextArea = new JTextArea("empty for the moment",20,50);

      JScrollPane scrollPane = new JScrollPane(commentTextArea);   
      lbcntvideos = new JLabel("Progress: 0/00000",JLabel.CENTER);



      
      logPanel.add(scrollPane); 
      logPanel.add(scrollPane); 
      
      mainFrame.add(logPanel);
      controlPanel.add(lbcntvideos);
      
      //mainFrame.pack();
      mainFrame.setVisible(true);  
   }
}