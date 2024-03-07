package test;
import backEnd.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
* @author Caleb Brunson
*/

public class DataWriterTest {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    public static final LocalDate DATE = LocalDate.of(2020, 1, 8);
    public UserList userList = UserList.getInstance();
	public ArrayList<User> users = userList.getUsers();
    public CourseList courseList = CourseList.getInstance();
    public ArrayList<Course> courses = courseList.getCourses();

    @Before
    public void setup() {
        UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();

        CourseList.getInstance().getCourses().clear();
		DataWriter.saveCourses();
    }

    @After
    public void tearDown() {
        UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();

        CourseList.getInstance().getCourses().clear();
		DataWriter.saveCourses();
    }

    // Users
    @Test
    public void testWritingZeroUsers() {
        users = DataLoader.getUsers();
        assertEquals(0, users.size());
    }

    
    @Test
    public void testWritingOneUser() {
        users.add(new Student("bob", "jones", "bjones", "bj@gmail.com", "bj123", NIL_UUID, NIL_UUID, NIL_UUID));
        DataWriter.saveUsers();
        assertEquals("bjones", DataLoader.getUsers().get(0).getUserName());
    }

    
    @Test
    public void testWritingFiveUsers() {
        users.add(new Student("bob", "jones", "abjones", "bj@gmail.com", "bj123", NIL_UUID, NIL_UUID, NIL_UUID));
        users.add(new Student("bob", "jones", "bbjones", "bj@gmail.com", "bj123", NIL_UUID, NIL_UUID, NIL_UUID));
        users.add(new Student("bob", "jones", "cbjones", "bj@gmail.com", "bj123", NIL_UUID, NIL_UUID, NIL_UUID));
        users.add(new Student("bob", "jones", "dbjones", "bj@gmail.com", "bj123", NIL_UUID, NIL_UUID, NIL_UUID));
        users.add(new Student("bob", "jones", "ebjones", "bj@gmail.com", "bj123", NIL_UUID, NIL_UUID, NIL_UUID));    
        DataWriter.saveUsers();
        assertEquals("ebjones", DataLoader.getUsers().get(4).getUserName());
    }

    @Test
	public void testWritingEmptyUser() {
        users.add(new Student(NIL_UUID, "", "", "", "", "", NIL_UUID, NIL_UUID, NIL_UUID));
        DataWriter.saveUsers();
		assertEquals("", DataLoader.getUsers().get(0).getUserName());
    }

    @Test
	public void testWritingNullUser() {
        users.add(new Student(NIL_UUID, "", "", null, "", "", NIL_UUID, NIL_UUID, NIL_UUID));
        DataWriter.saveUsers();
		assertEquals(null, DataLoader.getUsers().get(0).getUserName());
    }
    

    // Courses
    
    @Test
    public void testWritingZeroCourses() {
        courses = DataLoader.getCourses();
        assertEquals(0, courses.size());
    }

    @Test
    public void testWritingOneCourse() {
        courses.add(createCourse("course1"));
        DataWriter.saveCourses();
        assertEquals("course1", DataLoader.getCourses().get(0).getTitle());
    }

    /*
    @Test
    public void testWritingFiveCourses() {

    }

    @Test
	public void testWritingEmptyCourse() {

    }

    @Test
	public void testWritingNullCourse() {
        
    }
    */

    private Course createCourse(String courseTitle) {
        User teacher = new Teacher("jimmy", "john", "jimmyj", "jimmyj@gmail.com", 
        "howtojimmy31", NIL_UUID, NIL_UUID, NIL_UUID);
    
        ArrayList<Comment> comments = new ArrayList<Comment>();
    
        comments.add(new Comment(teacher, DATE, "blank comment", new ArrayList<Comment>()));
    
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    
        lessons.add(new Lesson("lesson title", "lesson content", comments));
    
        ArrayList<Question> questions = new ArrayList<Question>();
    
        ArrayList<String> choices = new ArrayList<String>();
    
        choices.add("question choice");
    
        questions.add(new Question("question", choices, 0));
    
        Quiz quiz = new Quiz("quiz title", "quiz description", questions);
    
        ArrayList<Topic> topics = new ArrayList<Topic>();
    
        topics.add(new Topic("topic title", "topic description", quiz, lessons, comments));
    
        ArrayList<StudentProgress> studentProgresses = new ArrayList<StudentProgress>();
    
        ArrayList<Grade> grades = new ArrayList<Grade>();
    
        grades.add(new Grade(NIL_UUID, 0));
    
        User student = new Student("jay", "jones", "jayj",
        "jayj@gmail.com", "jj1234", NIL_UUID, NIL_UUID, NIL_UUID);
    
        studentProgresses.add(new StudentProgress(student, grades));
    
        ArrayList<Review> reviews = new ArrayList<Review>();
    
        reviews.add(new Review(student, DATE, 5, "review comment"));
    
        Course course = new Course(courseTitle, Language.PYTHON, "course description", (Teacher) teacher, 
        topics, reviews, comments, studentProgresses);
    
        users.add(teacher);
        users.add(student);
        DataWriter.saveUsers();
    
        return course;
    }
    
}
