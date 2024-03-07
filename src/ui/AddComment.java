package ui;
import java.util.ArrayList;
import backEnd.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/*
 * Author njreiss
 */

public class AddComment implements ActionListener{
  private final LMSFacade facade;
  Course courseToAdd;
  Lesson lessonToAdd;
  ArrayList<Comment> comments;
  JFrame frame1;
  JButton submitButton;
  JTextArea commentField;

  AddComment(LMSFacade facade, ArrayList<Comment> comments){
    this.facade = facade;
    this.comments = comments;
    frame1 = new JFrame();
    submitButton = new JButton();
    commentField = new JTextArea();
    frame1.setLayout(null);
    commentField.setLineWrap(true);
    

    submitButton.setText("Submit");
    submitButton.addActionListener(this);

    submitButton.setBounds(350, 200, 100, 30);
    commentField.setBounds(50, 50, 400, 150);

    frame1.add(submitButton);
    frame1.add(commentField);
    
    frame1.setVisible(true);
    frame1.setSize(500, 300);
    frame1.setLayout(null);
  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
      System.out.println("add comment");
      String content = commentField.getText();
      facade.addComment(comments, content);
      frame1.setVisible(false);
    }
  }
}