package ui;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import backEnd.Quiz;
import backEnd.Topic;
import backEnd.*;

/*
 * Author njreiss
 */
public class AddQuiz implements ActionListener {
  private final LMSFacade facade;
  Quiz quizToEdit;
  boolean edit;

  JFrame frame1;
  JLabel l1;
  JLabel l2;
  JButton button1;

  JLabel titleLabel;
  JLabel descriptionLabel;

  JTextField titleField;
  JTextField descriptionField;

  ArrayList<Question> questionList;

  JButton addQuestion;

  AddQuiz(LMSFacade facade, Quiz quizToEdit, boolean edit){
    this.facade = facade;
    this.quizToEdit = quizToEdit;
    this.edit = edit;
    // create new displayed objects
    frame1 = new JFrame();
    l1 = new JLabel();
    l2 = new JLabel();
    button1 = new JButton();
    button1.addActionListener(this);

    titleLabel = new JLabel();
    descriptionLabel = new JLabel();
    
    titleField = new JTextField();
    descriptionField = new JTextField();

    l1.setFont(new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()));
    l2.setFont(new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()));

    titleLabel = new JLabel(); 
    descriptionLabel = new JLabel();

    titleField = new JTextField();
    descriptionField = new JTextField();

    questionList= new ArrayList<Question>();
    if (!edit) {
      addQuestion = new JButton();
      addQuestion.addActionListener(this);
      addQuestion.setText("Add Question");
      l2.setText("Questions");
    }
    
    // set text for all prompts
    if (edit) {
      l1.setText("Edit Quiz");
    } else {
      l1.setText("Create Quiz");
    }
    
    titleLabel.setText("Quiz Title:");
    descriptionLabel.setText("Quiz Description:");
    button1.setText("Submit");
  
    // sets bounds for all objects
    l1.setBounds(100, 50, 300, 15);
    titleLabel.setBounds(100, 80, 150, 20);
    descriptionLabel.setBounds(100, 110, 150, 20);

    titleField.setBounds(250, 80, 150, 20);
    descriptionField.setBounds(250, 110, 150, 20);

    l2.setBounds(100, 140, 300, 15);

    int questionCounter = 0;
    while(questionCounter < questionList.size()){
      JLabel questionName = new JLabel();
      JButton editButton = new JButton();

      questionName.setText(questionList.get(questionCounter).getQuestion());
      editButton.setText("Edit Question");
      questionName.setBounds(100, 170 + (30 * questionCounter), 150, 30);
      editButton.setBounds(300, 170 + (30 * questionCounter), 100, 30);

      frame1.add(questionName);
      frame1.add(editButton);
      questionCounter++;
    }

    if (!edit) {
      addQuestion.setBounds(275, 170 + (30 * questionCounter), 125, 30);
    }
    
    button1.setBounds(275, 200 + (30 * questionCounter), 125,30);

    // adds all ojects
    frame1.add(l1);

    frame1.add(titleLabel);
    frame1.add(descriptionLabel);

    frame1.add(titleField);
    frame1.add(descriptionField);

    frame1.add(l2);
    frame1.add(l1);

    if (!edit) {
      frame1.add(addQuestion);
    }
   
    frame1.add(button1);

    // creates frame
    frame1.setSize(500, 350 + (30 * questionCounter));
    frame1.setLayout(null);
    frame1.setVisible(true);

  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      String title = titleField.getText();
      String description = descriptionField.getText();

      System.out.println(title);
      System.out.println(description);

      if (title.isBlank() || description.isBlank()) {
        JOptionPane.showMessageDialog(frame1,"Title or description are incomplete.","Alert",JOptionPane.WARNING_MESSAGE);
      } else {
        if (edit) {
          facade.editQuiz(quizToEdit, title, description);
        } else {
          facade.createQuiz(title, description);
        }
      }

      frame1.setVisible(false);
    } else if (e.getSource() == addQuestion) {
      if (facade.reachedQuestionLimit()) {
        JOptionPane.showMessageDialog(frame1,"Only " + LMSFacade.QUESTION_LIMIT + " questions are allowed.","Alert",JOptionPane.WARNING_MESSAGE);
      } else {
        new AddQuestion(facade, null, null, false, false);
      }
    }
  }
}
