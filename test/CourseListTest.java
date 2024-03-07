package test;

import org.junit.After;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import backEnd.CourseList;
import backEnd.DataWriter;
import backEnd.Language;
import backEnd.StudentProgress;
import backEnd.UserList;
import backEnd.User;
import backEnd.Teacher;
import backEnd.Topic;
import backEnd.Review;
import backEnd.Comment;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import backEnd.Course;
import java.util.UUID;


public class CourseListTest {
    private static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private CourseList courseList = CourseList.getInstance();
    private ArrayList<Course> courses = courseList.getCourses();
    private Language language;
    private UserList userList= UserList.getInstance(); // users in ex
    private ArrayList<User> users = userList.getUsers(); // userList in ex

    

    @Before
     public void setupClass() {
        ArrayList<Topic> topics = new ArrayList<Topic>();
        ArrayList<Review> reviews = new ArrayList<Review>();
        ArrayList<Comment> comments = new ArrayList<Comment>();

        ArrayList<StudentProgress> studentProg = new ArrayList<StudentProgress>();
        Teacher teacher1 = new Teacher("Danny", "Phantom","dphan", "phantom@email.com", "dphan", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        Teacher teacher2 = new Teacher("bob", "jones", "bj32", "bj@gmail.com", "bjiscool123", 
        NIL_UUID, NIL_UUID, NIL_UUID);
        Course course1 = new Course("javaworld", Language.JAVA, "cool java course", teacher1, topics, reviews, comments, studentProg);
        Course course2 = new Course("CSSWORLD", Language.CSS, "The world of css", teacher2, topics, reviews, comments, studentProg);
        courses.add(course1);
        courses.add(course2);
        // users.add(new Student(NIL_UUID, "bob", "jones", "bj32", "bj@gmail.com", "bjiscool123", 
        // NIL_UUID, NIL_UUID, NIL_UUID));
        // users.add(new Teacher(NIL_UUID, "jeremy", "lewis", "jlewis", "jlewis@gmail.com", "jlewrocks74", 
        // NIL_UUID, NIL_UUID, NIL_UUID));
        // userList.users = users;
    //     users.clear();
    //     
    //     users.add(new Student("Loe", "Fmith","lfmith", "fmith@email.com", "fmmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));
    //     users.add(new Student("Soe", "Jmith","sjmith", "jmith@email.com", "sjmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));

         DataWriter.saveCourses();

        // testList.addUser("Student", "Joe", "Smith","jsmith", "smith@email.com", "jsmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        // testList.addUser("Student", "Loe", "Fmith","lfmith", "fmith@email.com", "fmmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        // testList.addUser("Student", "Soe", "Jmith","sjmith", "jmith@email.com", "sjmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

    }
    @After
    public void TearDownClass() {
        CourseList.getInstance().getCourses().clear();
        DataWriter.saveCourses();
    }

    @Test
    public void getCourseByUUIDTest() {
        
        ArrayList<Topic> topics = new ArrayList<Topic>();
        ArrayList<Review> reviews = new ArrayList<Review>();
        ArrayList<Comment> comments = new ArrayList<Comment>();

        ArrayList<StudentProgress> studentProg = new ArrayList<StudentProgress>();
        UUID id = UUID.randomUUID();
        Teacher teacher3 = new Teacher("the", "dude", "dude32", "dude@gmail.com", "dude123", 
        NIL_UUID, NIL_UUID, NIL_UUID);
        Course course3= new Course(id, "cppworld", Language.CPP, "cool cpp course", teacher3, topics, reviews, comments, studentProg);
        courses.add(course3);

        Course expected = course3;
        Course actually = courseList.getCourseByUUID(id);
        assertEquals(expected, actually);
    }

    @Test
    public void getCoursesTest() {
        ArrayList<Course> expected = courses;
        ArrayList<Course> actually = courseList.getCourses();

        assertEquals(expected, actually);
    }

    @Test 
    public void haveCourseOnNil() {
        boolean expected = false;
        boolean actually = courseList.haveCourse(NIL_UUID);
        assertEquals(expected, actually);

    }

    @Test 
    public void haveCourseOnUUID() {
        ArrayList<Topic> topics = new ArrayList<Topic>();
        ArrayList<Review> reviews = new ArrayList<Review>();
        ArrayList<Comment> comments = new ArrayList<Comment>();

        ArrayList<StudentProgress> studentProg = new ArrayList<StudentProgress>();
        UUID id = UUID.randomUUID();
        Teacher teacher3 = new Teacher("the", "dude", "dude32", "dude@gmail.com", "dude123", 
        NIL_UUID, NIL_UUID, NIL_UUID);
        Course course3= new Course(id, "cppworld", Language.CPP, "cool cpp course", teacher3, topics, reviews, comments, studentProg);
        courses.add(course3);

        boolean expected = true;
        boolean actually = courseList.haveCourse(id);
        assertEquals(expected, actually);

    }

    @Test
    public void addCourseTest() {
        ArrayList<Topic> topics = new ArrayList<Topic>();
        ArrayList<Review> reviews = new ArrayList<Review>();
        ArrayList<Comment> comments = new ArrayList<Comment>();

        ArrayList<StudentProgress> studentProg = new ArrayList<StudentProgress>();
        Teacher teacher3 = new Teacher("the", "dude", "dude32", "dude@gmail.com", "dude123", 
        NIL_UUID, NIL_UUID, NIL_UUID);
        Course course3= new Course("cppworld", Language.CPP, "cool cpp course", teacher3, topics, reviews, comments, studentProg);

        boolean expected = true;
        boolean actually = courseList.addCourse(course3);
        assertEquals(expected, actually);
    }
    
}
