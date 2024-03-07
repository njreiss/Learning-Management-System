package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import backEnd.*;

/*
 * Author njreiss
 */
public class AddLesson implements ActionListener{
  private final LMSFacade facade;
  Lesson lessonToEdit;
  boolean edit;
  JFrame frame1;
  JLabel l1;
  JButton button1;

  JLabel nameLabel;
  JTextField nameField;
  JLabel contentLabel;
  JTextArea contentField;
  JScrollPane scroll;

  AddLesson(LMSFacade facade, Lesson lessonToEdit, boolean edit){
    this.facade = facade;
    this.lessonToEdit = lessonToEdit;
    this.edit = edit;

    frame1 = new JFrame();
    l1 = new JLabel();
    button1 = new JButton();

    nameLabel = new JLabel();
    nameField = new JTextField(); 
    contentLabel = new JLabel();
    contentField = new JTextArea();
    contentField.setBounds(100, 140, 300, 200);
    contentField.setLineWrap(true);
    scroll = new JScrollPane(contentField);

    l1.setFont(new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize()));

    if (edit) {
      l1.setText("Edit Lesson");
    } else {
      l1.setText("Create Lesson");
    }
    
    nameLabel.setText("Lesson Name: ");
    contentLabel.setText("Lesson Content:");
    button1.setText("Submit");
    button1.addActionListener(this);

    l1.setBounds(100, 50, 300, 15);

    nameLabel.setBounds(100, 80, 150, 20);
    nameField.setBounds(250, 80, 150, 20);
    contentLabel.setBounds(100, 110, 150, 20);
    scroll.setBounds(100, 140, 300, 200);
    button1.setBounds(300, 350, 100, 30);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    button1.setBounds(300, 350, 100, 30);

    frame1.add(l1);

    frame1.add(nameLabel);
    frame1.add(nameField);
    frame1.add(contentLabel);
    frame1.add(scroll);
    frame1.add(button1);

    frame1.setSize(500, 450);
    frame1.setLayout(null);
    frame1.setVisible(true);
    frame1.setResizable(false);
  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      String name = nameField.getText();
      String content = contentField.getText();

      System.out.println(name);
      System.out.println(content);

      if (name.isBlank() || content.isBlank()) {
        JOptionPane.showMessageDialog(frame1,"Name or content are incomplete.","Alert",JOptionPane.WARNING_MESSAGE);
      } else {
        if (edit) {
          facade.editLesson(lessonToEdit, name, content);
        } else {
          facade.createLesson(name, content);
        }
      }

      frame1.setVisible(false);
    }
  }
}
