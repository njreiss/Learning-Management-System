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

public class ViewLesson implements ActionListener{
  private final LMSFacade facade;
  JFrame frame1;
  Lesson workingLesson;
  JTabbedPane tabbedPane;
  JScrollPane scroll;
  JButton printButton;
  JButton viewComments;
  JButton addComment;

  ViewLesson(LMSFacade facade, Lesson workingLesson){
    this.facade = facade;
    this.workingLesson = workingLesson;
    
    frame1 = new JFrame();
    tabbedPane = new JTabbedPane();

    frame1.setLayout(null);
    // for statement should populate string J with the title of the Lesson for
    // each Lesson in the topi. Should iterate once for each lesson in lessons
    // array. 
    for (int i = 0; i < 1; i++) {
      JPanel p1 = new JPanel();
      p1.setLayout(null);
      String j = i + " ";
      tabbedPane.add(j, p1);
      JLabel lessonName = new JLabel();
      JTextArea lessonContent = new JTextArea();
      lessonContent.setLineWrap(true);
      scroll = new JScrollPane(lessonContent);
      lessonName.setText(workingLesson.getTitle()); //Lesson title

      lessonContent.setEditable(false);
      lessonContent.setText(workingLesson.getContent()); // Lesson content

      lessonName.setFont(new Font(lessonName.getFont().getName(), Font.BOLD, lessonName.getFont().getSize()));
  
      lessonName.setBounds(50, 50, 400, 20);
      scroll.setBounds(50, 80, 375, 300);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      p1.add(lessonName);
      p1.add(scroll);
      tabbedPane.add(workingLesson.getTitle() , p1);

      tabbedPane.setBounds(0,0,500,500);
    }
    printButton = new JButton();
    printButton.setText("Print Content");
    printButton.setBounds(50, 500, 150, 30);
    printButton.addActionListener(this);
    frame1.add(printButton);

    viewComments = new JButton();
    viewComments.setText("View Comments");
    viewComments.setBounds(350, 500, 150, 30);
    viewComments.addActionListener(this);
    frame1.add(viewComments);

    addComment = new JButton();
    addComment.setText("Add Comment");
    addComment.setBounds(200, 500, 150, 30);
    addComment.addActionListener(this);
    frame1.add(addComment);

    frame1.setVisible(true);
    frame1.setSize(500, 600);
    frame1.add(tabbedPane);
  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == viewComments) {
      new ViewComments(facade, workingLesson.getComments());
    } else if (e.getSource() == addComment) {
      new AddComment(facade, workingLesson.getComments());
    } else if (e.getSource() == printButton) {
      String fileName = "src/" + workingLesson.getTitle() + ".txt";
      facade.printLesson(fileName, workingLesson.getContent());
    }
  }
}
