package ui;
import javax.swing.*;
import java.awt.event.*;

/*
 * Author njreiss
 */

public class Landing implements ActionListener{
  JFrame frame1;
  JLabel l;
  JButton button1;
  JButton button2;

  Landing(){
    // creates a JFrame
    frame1 = new JFrame();
    l = new JLabel();
    button1 = new JButton("Sign Up");
    button2 = new JButton("Log In");

    l.setText("Welcome to our Learning Management System!");

    // x axis, y axis, width, height
    l.setBounds(120, 90, 300, 15);
    button1.setBounds(210, 115, 80, 30);
    button2.setBounds(210, 145, 80, 30);
  
    frame1.add(l);
    frame1.add(button1);
    frame1.add(button2);

    button1.addActionListener(this);
    button2.addActionListener(this);
  
    frame1.setSize(500, 300) ;
    frame1.setLayout(null);
    frame1.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      frame1.setVisible(false);
      new SignUp();
      
    } else if (e.getSource() == button2) { 
      frame1.setVisible(false);
      new LogIn();
    }
  }
}
