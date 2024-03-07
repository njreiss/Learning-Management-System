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
public class AddQuestion implements ActionListener {
  private final LMSFacade facade;
  Quiz quizToEdit;
  Question questionToEdit;
  boolean edit;
  boolean add;

  JFrame frame1;
  JLabel l1;
  JButton button1;

  JLabel questionLabel;
  JLabel ans1Label;
  JLabel ans2Label;
  JLabel ans3Label;
  JLabel ans4Label;
  JLabel ansIndex;

  JTextField questionField;
  JTextField ans1Field;
  JTextField ans2Field;
  JTextField ans3Field;
  JTextField ans4Field;
  JComboBox ansComboBox;
  
  String answers[] = {"1", "2", "3", "4"};

  AddQuestion(LMSFacade facade, Quiz quizToEdit, Question questionToEdit, boolean edit, boolean add){
    this.facade = facade;
    this.quizToEdit = quizToEdit;
    this.questionToEdit = questionToEdit;
    this.edit = edit;
    this.add = add;
    // create new displayed objects
    frame1 = new JFrame();
    l1 = new JLabel();
    button1 = new JButton();
    button1.addActionListener(this);

    questionLabel = new JLabel();
    ans1Label = new JLabel();
    ans2Label = new JLabel();
    ans3Label = new JLabel();
    ans4Label = new JLabel();
    ansIndex = new JLabel();
    
    questionField = new JTextField();
    ans1Field = new JTextField();
    ans2Field = new JTextField();
    ans3Field = new JTextField();
    ans4Field = new JTextField();
    ansComboBox = new JComboBox(answers);

    l1.setFont(new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()));
    
    // set text for all prompts
    if (edit) {
      l1.setText("Edit Question");
    } else {
      l1.setText("Add Question");
    }
    
    button1.setText("Submit");

    questionLabel.setText("Question:");
    ans1Label.setText("Answer 1: ");
    ans2Label.setText("Answer 2: ");
    ans3Label.setText("Answer 3: ");
    ans4Label.setText("Answer 4: ");
    ansIndex.setText("Correct Answer: ");
  
    // sets bounds for all objects
    l1.setBounds(100, 50, 300, 15);
    questionLabel.setBounds(100, 80, 150, 15);
    ans1Label.setBounds(100, 110, 150, 15);
    ans2Label.setBounds(100, 140, 150, 15);
    ans3Label.setBounds(100, 170, 150, 15);
    ans4Label.setBounds(100, 200, 150, 15);
    ansIndex.setBounds(100, 230, 150, 15);

    questionField.setBounds(250, 80, 150, 20);
    ans1Field.setBounds(250, 110, 150, 20);
    ans2Field.setBounds(250, 140, 150, 20);
    ans3Field.setBounds(250, 170, 150, 20);
    ans4Field.setBounds(250, 200, 150, 20);
    ansComboBox.setBounds(250, 230, 150, 20);

    button1.setBounds(275, 260, 125,30);

    // adds all ojects
    frame1.add(l1);
    frame1.add(button1);

    frame1.add(questionLabel);
    frame1.add(ans1Label);
    frame1.add(ans2Label);
    frame1.add(ans3Label);
    frame1.add(ans4Label);
    frame1.add(ansIndex);

    frame1.add(questionField);
    frame1.add(ans1Field);
    frame1.add(ans2Field);
    frame1.add(ans3Field);
    frame1.add(ans4Field);
    frame1.add(ansComboBox);
    // frame1.add(button1);

    // creates frame
    frame1.setSize(500, 500);
    frame1.setLayout(null);
    frame1.setVisible(true);

  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      String question = questionField.getText();
      String ans1 = ans1Field.getText();
      String ans2 = ans2Field.getText();
      String ans3 = ans3Field.getText();
      String ans4 = ans4Field.getText();
      int ansIndex = ansComboBox.getSelectedIndex();

      System.out.println(question);
      System.out.println(ans1);
      System.out.println(ans2);
      System.out.println(ans3);
      System.out.println(ans4);
      System.out.println(ansIndex);

      if (question.isBlank() || ans1.isBlank() || ans2.isBlank() || ans3.isBlank() || ans4.isBlank()) {
        JOptionPane.showMessageDialog(frame1,"Questions or the answers are complete.","Alert",JOptionPane.WARNING_MESSAGE);
      } else {
        ArrayList<String> choices = new ArrayList<String>();
        choices.add(ans1);
        choices.add(ans2);
        choices.add(ans3);
        choices.add(ans4);

        if (edit) {
          // edit the question
          facade.editQuestion(questionToEdit, question, choices, ansIndex);
          frame1.setVisible(false);
        } else if (add) {
          facade.addQuestionToQuiz(quizToEdit, question, choices, ansIndex);
          frame1.setVisible(false);
        } else {
          facade.createQuestion(question, choices, ansIndex);
        }
      }

      frame1.setVisible(false);

    }
  }
}
