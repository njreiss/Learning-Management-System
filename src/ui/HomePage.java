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
public class HomePage implements ActionListener {
  private final LMSFacade facade;
  JLabel l1; 
  JFrame frame1;
  JButton coursesButton;
  JButton searchButton;
  JButton signOutButton;
  JButton dashboardButton;
  JButton createCourseButton;


  HomePage(LMSFacade facade) {
    // Initalize facade
    this.facade = facade;

    // create new displayed objects
    frame1 = new JFrame();
    l1 = new JLabel();

    coursesButton = new JButton();
    searchButton = new JButton();
    signOutButton = new JButton();
    dashboardButton = new JButton();

    coursesButton.addActionListener(this);
    signOutButton.addActionListener(this);
    dashboardButton.addActionListener(this);
    searchButton.addActionListener(this);

    l1.setFont(new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()));
    
    // set text for all prompts
    l1.setText("Welcome to our learning management system, " + facade.getUser().getFirstName());
    coursesButton.setText("Courses");
    searchButton.setText("Search");
    dashboardButton.setText("Dashboard");
    signOutButton.setText("Sign Out");



    // sets bounds for all objects
    l1.setBounds(50, 50, 400, 30);
    coursesButton.setBounds(175, 80, 150, 30);
    searchButton.setBounds(175, 110, 150, 30);
    dashboardButton.setBounds(175, 140, 150, 30);
    signOutButton.setBounds(175, 200, 150, 30);
    

    // adds all ojects
    frame1.add(l1);
    frame1.add(coursesButton);
    frame1.add(searchButton);
    frame1.add(dashboardButton);
    frame1.add(signOutButton);

    // Add createCourseButton if user is a Teacher
    if (facade.getUser().getType().equalsIgnoreCase("teacher")) {
      createCourseButton = new JButton();
      createCourseButton.addActionListener(this);
      createCourseButton.setText("Create Course");
      createCourseButton.setBounds(175, 170, 150, 30);
      frame1.add(createCourseButton);
    }
  
    // creates frame
    frame1.setSize(500, 500);
    frame1.setLayout(null);
    frame1.setVisible(true);
  
    
  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == coursesButton) {
      //frame1.setVisible(false);
      new ViewCourses(facade);
    } else if (e.getSource() == searchButton) {

    } else if (e.getSource() == dashboardButton) {
      new viewDashboard(facade);
    } else if (e.getSource() == createCourseButton) {
      //frame1.setVisible(false);
      new CreateCourse(facade);
    } else if (e.getSource() == signOutButton) {
      facade.signOut();
      frame1.setVisible(false);
      new Landing();
    }
  }
}
