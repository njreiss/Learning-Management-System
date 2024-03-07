package ui;
import java.util.ArrayList;
import java.util.HashMap;
import backEnd.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Author njreiss
 */

public class viewDashboard implements ActionListener{
  private final LMSFacade facade;
  JFrame frame1;
  HashMap<String, StudentProgress> completedCourses;
  JLabel coursesLabel;
  User user;

  viewDashboard(LMSFacade facade){
    this.facade = facade;
    this.user = facade.getUser();  
    this.completedCourses = facade.getCompletedCourses(user.getId());
    
    frame1 = new JFrame();

    frame1.setLayout(null);
    // for statement should populate string J with the title of the course for
    // each course the student is enrolled in. Should iterate once for each 
    // course in courses array. 
    coursesLabel = new JLabel();
    coursesLabel.setText("Completed Courses");
    coursesLabel.setFont(new Font(coursesLabel.getFont().getName(), Font.BOLD, coursesLabel.getFont().getSize()));

    coursesLabel.setBounds(50, 50, 200, 20);
    

    frame1.add(coursesLabel);


    int i = 0;
    for (String courseString : completedCourses.keySet()) {
      frame1.setLayout(null);
      
      String j = i + " ";
      JLabel courseName = new JLabel();
      JButton viewButton = new JButton();
      JButton printButton = new JButton();
      // JLabel 

    
      courseName.setText(courseString); // Course name
      viewButton.setText("view");
      viewButton.setName("view " + courseString);
      viewButton.addActionListener(this);
      printButton.setText("print");
      printButton.setName("print " + courseString);
      printButton.addActionListener(this);

      courseName.setFont(new Font(courseName.getFont().getName(), Font.BOLD, courseName.getFont().getSize()));

      courseName.setBounds(50, 80 + (i * 30), 150, 20);
      viewButton.setBounds(200, 80 + (i * 30), 100, 20);
      printButton.setBounds(300, 80 + (i * 30), 100, 20);
     

      frame1.add(courseName);
      frame1.add(viewButton);
      frame1.add(printButton);
      // should parse through all topics in course

      i++;
    }
    

    
    frame1.setVisible(true);
    frame1.setSize(600, 600);

  }
  public void actionPerformed(ActionEvent e) {
    JButton btn = (JButton)e.getSource();
    String[] stringArray = btn.getName().split("\\s+");
    String action = stringArray[0];
    String courseString = stringArray[1];
    StudentProgress workingProgress = completedCourses.get(courseString);
    ArrayList<Grade> grades = workingProgress.getGrades();
      double gradeTotal = 0;
      for (Grade g : grades) {
        gradeTotal = gradeTotal + g.getGradePercentage();
      }
      double avgGrade = gradeTotal / grades.size();

    if (action.equals("view")) {
      JOptionPane.showMessageDialog(frame1,"Your Average Grade for " + courseString + " is: " + avgGrade + "%.","Quiz Result",JOptionPane.INFORMATION_MESSAGE);
    } else if (action.equals("print")) {
      String fileName = "src/" + courseString + " certificate.txt";
      String studentName = user.getFullName();
      facade.printCourseCertificate(fileName, courseString, studentName, avgGrade);
    }
    
    
    
  }
}