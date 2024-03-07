package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;

import backEnd.*;

/*
 * Author njreiss
 */

public class ViewQuestion implements ActionListener {
    private final LMSFacade facade;
    Course workingCourse;
    Quiz workingQuiz;
    ArrayList<Question> questions;
    int numCorrectChoices = 0; // keep track of number of correct choices user makes
    int numChoices = 0;
    ArrayList<Integer> correctAnswerIndices;
    ArrayList<ButtonGroup> buttonGroups;
    JFrame frame1;
    JLabel l1;
    JRadioButton a0;
    JRadioButton a1;
    JRadioButton a2;
    JRadioButton a3;
    ArrayList<JPanel> pages;
    JTabbedPane tabbedPane;
    JButton submitButton;
    

    ViewQuestion(LMSFacade facade, Course workingCourse, Quiz workingQuiz) {
        this.facade = facade;
        this.workingCourse = workingCourse;
        this.workingQuiz = workingQuiz;
        this.questions = workingQuiz.getQuestions();
        this.correctAnswerIndices = new ArrayList<Integer>();
        this.buttonGroups = new ArrayList<ButtonGroup>();

        frame1 = new JFrame();
        l1 = new JLabel();
        submitButton = new JButton();
        pages = new ArrayList<JPanel>();
        tabbedPane = new JTabbedPane();

        for (int i = 0; i < questions.size(); i++) { // 5 for now
            Question workingQuestion = questions.get(i);
            JPanel page = new JPanel();
            JLabel question = new JLabel();
            a0 = new JRadioButton();
            a1 = new JRadioButton();
            a2 = new JRadioButton();
            a3 = new JRadioButton();

            question.setText("Question " + (i + 1) + ": " + workingQuestion.getQuestion());
            ArrayList<String> choices = workingQuestion.getChoices();
            a0.setText("A) " + choices.get(0));
            a1.setText("B) " + choices.get(1));
            a2.setText("C) " + choices.get(2));
            a3.setText("D) " + choices.get(3));

            correctAnswerIndices.add(workingQuestion.getCorrectAnswerIndex());

            ButtonGroup bg = new ButtonGroup();
            bg.add(a0);
            bg.add(a1);
            bg.add(a2);
            bg.add(a3);
            buttonGroups.add(bg);

            page.setLayout(null);

            question.setBounds(100, 50, 500, 30);
            a0.setBounds(100, 100, 300, 30);
            a1.setBounds(100, 130, 300, 30);
            a2.setBounds(100, 160, 300, 30);
            a3.setBounds(100, 190, 300, 30);

            page.add(question);
            page.add(a0);
            page.add(a1);
            page.add(a2);
            page.add(a3);
            pages.add(page);

            if (facade.getUser().getType().equalsIgnoreCase("student")) {
                // Add a submit button to the page
                submitButton = new JButton("Submit");
                submitButton.setBounds(320, 275, 100, 30);
                submitButton.addActionListener(this);
                submitButton.setName("submit " + Integer.toString(i));
                page.add(submitButton);
            }

            if (facade.getUser().getId().equals(workingCourse.getTeacher().getId())) {
                JButton editQuestion = new JButton();
                JButton deleteQuestion = new JButton();
                JButton addQuestion = new JButton();
                editQuestion.addActionListener(this);
                deleteQuestion.addActionListener(this);
                addQuestion.addActionListener(this);
                editQuestion.setText("Edit");
                deleteQuestion.setText("Delete");
                addQuestion.setText("Add");
    
                editQuestion.setName("edit " + Integer.toString(i));
                deleteQuestion.setName("delete " + Integer.toString(i));
                addQuestion.setName("add");
    
                editQuestion.setBounds(200, 300, 100, 20);
                deleteQuestion.setBounds(300, 300 , 100, 20);
                addQuestion.setBounds(100, 300, 100, 20);
                frame1.add(editQuestion);
                frame1.add(deleteQuestion);
                frame1.add(addQuestion);
            }
 
            tabbedPane.add("Question " + (i + 1), page);
        }
        


        tabbedPane.setBounds(0, 0, 500, 500);
        frame1.add(tabbedPane);

        frame1.setSize(500, 500);
        frame1.setLayout(null);
        frame1.setVisible(true); 
    } 

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        String[] splitArray = btn.getName().split("\\s+");
        String action = splitArray[0];
        int questionBtnIndex;

        if (action.equals("add")) {
            new AddQuestion(facade, workingQuiz, null, false, true);
            frame1.setVisible(false);
        } else if (action.equals("edit")) {
            questionBtnIndex = Integer.parseInt(splitArray[1]);
            new AddQuestion(facade, workingQuiz, questions.get(questionBtnIndex), true, false);
            frame1.setVisible(false);
        } else if (action.equals("delete")) {
            questionBtnIndex = Integer.parseInt(splitArray[1]);
            facade.deleteQuestion(workingQuiz, questions.get(questionBtnIndex));
            frame1.setVisible(false);
        } else if (action.equals("submit")) {
            questionBtnIndex = Integer.parseInt(splitArray[1]);

            int correctAnswerIndex = correctAnswerIndices.get(questionBtnIndex);
    
            ButtonGroup bg = buttonGroups.get(questionBtnIndex);
            int buttonIndex = 0;
            for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
    
                if (button.isSelected() && buttonIndex == correctAnswerIndex) {
                    numCorrectChoices++;
                }
    
                buttonIndex++;
            }
            numChoices++;
            btn.setVisible(false);
    
            if (numChoices == questions.size()) {
                double grade = 100 * (double) numCorrectChoices / numChoices;
                grade = Math.ceil(grade);
                JOptionPane.showMessageDialog(frame1,"Your grade is " + grade + "%.","Quiz Result",JOptionPane.INFORMATION_MESSAGE);
    
                facade.updateStudentProgress(workingCourse.getId(), workingQuiz, grade);
            }
        }



        
    }
}
    
