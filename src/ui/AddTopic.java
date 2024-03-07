package ui;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import backEnd.Quiz;
import backEnd.Topic;
import backEnd.*;

/*
 * Author njreiss
 */
public class AddTopic implements ActionListener{
  private final LMSFacade facade;
  Topic topicToEdit;
  boolean edit;

  JFrame frame1;
  JLabel l1;
  JLabel l2;
  JLabel l3;
  JButton button1;

  JLabel topicTitleLabel;
  JLabel descriptionLabel;
  JTextField topicTitleField;
  JTextField descriptionField;

  ArrayList<Topic> topicList;

  JButton addLesson;
  JButton addQuiz;

  String languages[] = {"Python", "JavaScript", "Java", "C#", "PHP", "C++", "C", "R", "Swift", "Objective C", "Kotlin"};

  AddTopic(LMSFacade facade, Topic topicToEdit, boolean edit){
    this.facade = facade;
    this.topicToEdit = topicToEdit;
    this.edit = edit;
    
    // create new displayed objects
    
    frame1 = new JFrame();
    l1 = new JLabel();
    l2 = new JLabel();
    l3 = new JLabel();
    l1.setFont(new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()));
    l2.setFont(new Font(l2.getFont().getName(), Font.BOLD, l2.getFont().getSize()));
    l3.setFont(new Font(l3.getFont().getName(), Font.BOLD, l3.getFont().getSize()));

    topicTitleLabel = new JLabel(); 
    descriptionLabel = new JLabel();

    topicTitleField = new JTextField();
    descriptionField = new JTextField();

    topicList= new ArrayList<Topic>();

    if (!edit) {
      addLesson = new JButton();
      addLesson.addActionListener(this);
      addQuiz = new JButton();
      addQuiz.addActionListener(this);
    }
    

    button1 = new JButton();
    button1.addActionListener(this);

    // set text for all prompts
    if (edit) {
      l1.setText("Edit Topic");
    } else {
      l1.setText("Create Topic");
    }
    topicTitleLabel.setText("Topic Title:");
    descriptionLabel.setText("Topic Description:");

    if (!edit) {
      l2.setText("Lessons");
      addLesson.setText("Add Lesson");

      l3.setText("Quizes");
      addQuiz.setText("Add Quiz");
    }

    button1.setText("Submit");
  
    // sets bounds for all objects
    l1.setBounds(100, 50, 300, 15);
    topicTitleLabel.setBounds(100, 80, 150, 20);
    descriptionLabel.setBounds(100, 110, 150, 20);

    topicTitleField.setBounds(250, 80, 150, 20);
    descriptionField.setBounds(250, 110, 150, 20);

    l2.setBounds(100, 140, 300, 15);

    int lessonCounter = 0;
    while(lessonCounter < topicList.size()){
      JLabel lessonName = new JLabel();
      JButton editButton = new JButton();

      // lessonName.setText(lessonList.get(topicCounter));
      lessonName.setText(topicList.get(lessonCounter).getTitle());
      editButton.setText("Edit Lesson");
      lessonName.setBounds(100, 170 + (30 * lessonCounter), 150, 30);
      editButton.setBounds(300, 170 + (30 * lessonCounter), 100, 30);

      frame1.add(lessonName);
      frame1.add(editButton);
      lessonCounter++;
    }

    if (!edit) {
      addLesson.setBounds(300, 170 + (30 * lessonCounter), 100, 30);
    }
    l3.setBounds(100, 200 + (30 * lessonCounter), 300, 15);

    int quizCounter = 0;
    while(quizCounter < topicList.size()){
      JLabel lessonName = new JLabel();
      JButton editButton = new JButton();

      // lessonName.setText(lessonList.get(topicCounter));
      lessonName.setText(topicList.get(quizCounter).getTitle());
      editButton.setText("Edit Lesson");
      lessonName.setBounds(100, 170 + (30 * quizCounter), 150, 30);
      editButton.setBounds(300, 170 + (30 * quizCounter), 100, 30);

      frame1.add(lessonName);
      frame1.add(editButton);
      quizCounter++;
    }

    if (!edit) {
      addQuiz.setBounds(300, 230 + (30 * lessonCounter) + (30 * quizCounter), 100, 30);
    }
    
    button1.setBounds(300, 260 + (30 * lessonCounter) + (30 * quizCounter), 100, 30);


    // adds all ojects
    frame1.add(l1);

    frame1.add(topicTitleLabel);
    frame1.add(descriptionLabel);

    frame1.add(topicTitleField);
    frame1.add(descriptionField);

    frame1.add(l2);
    frame1.add(l3);

    if (!edit) {
      frame1.add(addLesson);
      frame1.add(addQuiz);
    }
    frame1.add(button1);

    // frame1.add(l3);

    // frame1.add(addQuiz);
    // creates frame
    frame1.setSize(500, 350 + (30 * lessonCounter) + (30 * quizCounter));
    frame1.setLayout(null);
    frame1.setVisible(true);

  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      String title = topicTitleField.getText();
      String description = descriptionField.getText();

      System.out.println(title);
      System.out.println(description);

      if (title.isBlank() || description.isBlank()) {
        JOptionPane.showMessageDialog(frame1,"Title or Description are incomplete.","Alert",JOptionPane.WARNING_MESSAGE);
      } else {
        if (edit) {
          facade.editTopic(topicToEdit, title, description);
          frame1.setVisible(false);
        } else {
          facade.createTopic(title, description);
        }
      }

      frame1.setVisible(false);

    } else if (e.getSource() == addLesson) {
      if (facade.reachedLessonLimit()) {
        JOptionPane.showMessageDialog(frame1,"Only " + LMSFacade.LESSON_LIMIT + " lessons are allowed.","Alert",JOptionPane.WARNING_MESSAGE);
      } else {
        new AddLesson(facade, null, false);
      }
    } else if (e.getSource() == addQuiz) {
      if (facade.reachedQuizLimit()) {
        JOptionPane.showMessageDialog(frame1,"Only " + LMSFacade.QUIZ_LIMIT + " quiz is allowed.","Alert",JOptionPane.WARNING_MESSAGE);
      } else {
        new AddQuiz(facade, null, false);
      }
    }
  }
}
