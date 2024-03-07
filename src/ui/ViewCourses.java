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

public class ViewCourses implements ActionListener{
  private final LMSFacade facade;
  JFrame frame1;
  JButton viewTopic;
  ArrayList<Course> courses;
  ArrayList<Topic> buttonTopics;
  JTabbedPane tabbedPane;
  JButton viewComments;
  JButton addComment;

  ViewCourses(LMSFacade facade){
    this.facade = facade;
    this.courses = facade.getCourseList().getCourses();
    this.buttonTopics = new ArrayList<Topic>();
    
    
    frame1 = new JFrame();
    tabbedPane = new JTabbedPane();

    frame1.setLayout(null);
    // for statement should populate string J with the title of the course for
    // each course the student is enrolled in. Should iterate once for each 
    // course in courses array. 
    for (int i = 0; i < courses.size(); i++) {
    // for (int i = 0; i < courses.size(); i++) {
      Course workingCourse = courses.get(i);
      JPanel p1 = new JPanel();
      p1.setLayout(null);
      String j = i + " ";
      tabbedPane.add(j, p1);
      JLabel courseName = new JLabel();
      JLabel authorName = new JLabel();
      JLabel courseDescription = new JLabel();
      JLabel topicLabel = new JLabel();
      // JLabel 


      courseName.setText("Title: " + workingCourse.getTitle()); // Course name
      authorName.setText("Teacher: " + workingCourse.getTeacher().getFullName()); // Teacher name
      courseDescription.setText("Description: " + workingCourse.getDescription()); // Course description
      topicLabel.setText("Topics: ");

      courseName.setFont(new Font(courseName.getFont().getName(), Font.BOLD, courseName.getFont().getSize()));
      topicLabel.setFont(new Font(courseName.getFont().getName(), Font.BOLD, courseName.getFont().getSize()));

      courseName.setBounds(50, 50, 300, 20);
      authorName.setBounds(50, 70, 300, 20);
      courseDescription.setBounds(50, 100, 300, 20);
      topicLabel.setBounds(50, 130, 300, 20);

      p1.add(courseName);
      p1.add(authorName);
      p1.add(courseDescription);
      p1.add(topicLabel);
      tabbedPane.add(workingCourse.getTitle() , p1);
      // should parse through all topics in course
      ArrayList<Topic> topics = courses.get(i).getTopics();
      for (int k = 0; k < topics.size(); k++) {
        Topic workingTopic = topics.get(k);
        buttonTopics.add(workingTopic);
        JLabel topicName = new JLabel();
        viewTopic = new JButton(); // Initalize viewTopic JBUTTON

        if (facade.getUser().getId().equals(workingCourse.getTeacher().getId())) {
          JButton editTopic = new JButton();
          editTopic.addActionListener(this);
          JButton deleteTopic = new JButton();
          deleteTopic.addActionListener(this);
          editTopic.setText("Edit");
          editTopic.setName("edit " + Integer.toString(i) + " " + Integer.toString(k));
          deleteTopic.setText("Delete");
          deleteTopic.setName("delete " + Integer.toString(i) + " " +  Integer.toString(k));

          editTopic.setBounds(350, 160 + (k * 30), 100, 20);
          deleteTopic.setBounds(450, 160 + (k * 30), 100, 20);
          p1.add(editTopic);
          p1.add(deleteTopic);
        }
        
        viewTopic.addActionListener(this);
        topicName.setText(workingTopic.getTitle()); // title/name of the topic
        viewTopic.setText("View");
        viewTopic.setName("view " + Integer.toString(i) + " " + Integer.toString(k));
        topicName.setBounds(150, 160 + (k * 30), 100, 20);
        viewTopic.setBounds(250, 160 + (k * 30), 100, 20);
        p1.add(topicName);
        p1.add(viewTopic);

      }

      viewComments = new JButton();
      viewComments.setText("View Comments");
      viewComments.setName("viewcomments " + Integer.toString(i) + " 0");
      viewComments.setBounds(350, 500, 150, 30);
      viewComments.addActionListener(this);
      frame1.add(viewComments);
  
      addComment = new JButton();
      addComment.setText("Add Comment");
      addComment.setName("addcomment " + Integer.toString(i) + " 0");
      addComment.setBounds(200, 500, 150, 30);
      addComment.addActionListener(this);
      frame1.add(addComment);
    }
    

    tabbedPane.setBounds(0,0,600,500);
    
    frame1.setVisible(true);
    frame1.setSize(600, 600);

    frame1.add(tabbedPane);
  }
  public void actionPerformed(ActionEvent e) {
    JButton btn = (JButton)e.getSource();
    String[] splitArray = btn.getName().split("\\s+");
    String action = splitArray[0];
    int courseBtnIndex = Integer.parseInt(splitArray[1]);
    int topicBtnIndex = Integer.parseInt(splitArray[2]);
    Course courseOfInterest = courses.get(courseBtnIndex);
    Topic topicOfInterest = courseOfInterest.getTopics().get(topicBtnIndex);
    if (action.equals("edit")) {
      // Edit the topic information
      new AddTopic(facade, topicOfInterest, true);
      frame1.setVisible(false);
    } else if (action.equals("delete")) {
      // Delete the topic
      facade.deleteTopic(courseOfInterest, topicOfInterest);
      frame1.setVisible(false);
    } else if (action.equals("view")) {
      // View that particular topic
      new ViewTopic(facade, courseOfInterest, topicOfInterest);
    } else if (action.equals("viewcomments")) {
      new ViewComments(facade, courseOfInterest.getComments());
    } else if (action.equals("addcomment")) {
      new AddComment(facade, courseOfInterest.getComments());
    }
  }
}