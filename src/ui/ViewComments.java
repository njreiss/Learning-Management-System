package ui;
import java.util.ArrayList;
import backEnd.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/*
 * Author njreiss
 */
public class ViewComments implements ActionListener{
  private final LMSFacade facade;
  JFrame frame1;
  JButton viewTopic;
  ArrayList<Comment> comments;
  int commentIndex = 0;
  JButton addComment;
  JButton viewReplies;

  ViewComments(LMSFacade facade, ArrayList<Comment> comments) {
    this.facade = facade;    
    this.comments = comments;
    frame1 = new JFrame();
    frame1.setLayout(null);

    for (int i = 0; i < comments.size(); i++) {
      Comment workingComment = comments.get(i);
      JLabel authorLabel = new JLabel();
      JLabel contentLabel = new JLabel();
      JLabel dateLabel = new JLabel();
      viewReplies = new JButton();
      JSeparator separator = new JSeparator();

      authorLabel.setText("UserName: " + workingComment.getUser().getFullName()); // Course name
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
      dateLabel.setText("Date: " + formatter.format(workingComment.getDate())); // Teacher name
      contentLabel.setText("Content: " + workingComment.getContent()); // Course description
      viewReplies.setText("View Replies");
      viewReplies.setName("view " + Integer.toString(i));
      viewReplies.addActionListener(this);

      authorLabel.setFont(new Font(authorLabel.getFont().getName(), Font.BOLD, authorLabel.getFont().getSize()));

      authorLabel.setBounds(50, 50 + (i * 150), 300, 20);
      dateLabel.setBounds(300, 50 + (i * 150), 100, 20);
      contentLabel.setBounds(50, 75 + (i * 150), 300, 100);
      viewReplies.setBounds(350, 70 + (i * 150), 100, 30);
      separator.setBounds(50,175 + (i * 150), 400, 1);

      frame1.add(authorLabel);
      frame1.add(dateLabel);
      frame1.add(contentLabel);
      frame1.add(viewReplies);
      frame1.add(separator);
      
    }

    addComment = new JButton();
    addComment.setText("Add Comment");
    addComment.setName("add 0");
    addComment.setBounds(200, 800, 150, 30);
    addComment.addActionListener(this);
    frame1.add(addComment);
    
    frame1.setVisible(true);
    frame1.setSize(500, 1000);
    frame1.setLayout(null);
  }
  public void actionPerformed(ActionEvent e) {
    JButton btn = (JButton)e.getSource();
    String[] splitArray = btn.getName().split("\\s+");
    String action = splitArray[0];
    int commentIndex;

    if (action.equals("add")) {
      new AddComment(facade, comments);
      frame1.setVisible(false);
    } else if (action.equals("view")) {
      commentIndex = Integer.parseInt(splitArray[1]);
      System.out.println(commentIndex); // test
      new ViewComments(facade, comments.get(commentIndex).getReplys());
    }
  }
}