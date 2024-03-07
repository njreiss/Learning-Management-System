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
 * JUnit test for DataLoader class
 * Note: first start with users then move to courses
 * @author Caleb Brunson
 */

public class DataLoaderTest {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    public static final UUID TEACHER_UUID = UUID.fromString("7b23c04e-9f5a-4eff-aaf7-d74a9f03d8c0");
    public static final UUID STUDENT_UUID = UUID.fromString("6a73cd69-8316-4e4e-9e69-a4f743cbe148");
    public static final LocalDate DATE = LocalDate.of(2020, 1, 8);
    private UserList userList = UserList.getInstance();
	private ArrayList<User> users = userList.getUsers();
    private CourseList courseList = CourseList.getInstance();
    private ArrayList<Course> courses = courseList.getCourses();

    @Before
    public void setup() {
        users.clear();
        users.add(new Student(NIL_UUID, "bob", "jones", "bj32", "bj@gmail.com", "bjiscool123", 
        NIL_UUID, NIL_UUID, NIL_UUID));
        DataWriter.saveUsers();

        courses.clear();
        courses.add(createCourse());
        DataWriter.saveCourses();
    }

    @After
    public void tearDown() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();

        CourseList.getInstance().getCourses().clear();
        DataWriter.saveCourses();
    }

    // User Methods
    @Test
    public void testGetUsersSize() {
        users = DataLoader.getUsers();
        assertEquals(3, users.size());
    }

    @Test
    public void testGetUsersSizeZero() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
        assertEquals(0, users.size());
    }

    @Test
    public void testGetUserUUID() {
        users = DataLoader.getUsers();
        assertEquals(NIL_UUID, users.get(0).getId());
    }

    @Test
    public void testGetUserType() {
        users = DataLoader.getUsers();
        assertEquals("student", users.get(0).getType());
    }

    @Test
    public void testGetUserFullName() {
        users = DataLoader.getUsers();
        assertEquals("bob jones", users.get(0).getFullName());
    }

    @Test
    public void testGetUserFirstName() {
        users = DataLoader.getUsers();
        assertEquals("bob", users.get(0).getFirstName());
    }

    @Test
    public void testGetUserLastName() {
        users = DataLoader.getUsers();
        assertEquals("jones", users.get(0).getLastName());
    }

    @Test
    public void testGetUserUserName() {
        users = DataLoader.getUsers();
        assertEquals("bj32", users.get(0).getUserName());
    }

    @Test
    public void testGetUserEmail() {
        users = DataLoader.getUsers();
        assertEquals("bj@gmail.com", users.get(0).getEmail());
    }

    @Test
    public void testGetUserPassword() {
        users = DataLoader.getUsers();
        assertEquals("bjiscool123", users.get(0).getPassword());
    }

    @Test
    public void testGetUserCurrentCourseID() {
        users = DataLoader.getUsers();
        assertEquals(NIL_UUID, users.get(0).getCurrentCourseID());
    }

    @Test
    public void testGetUserCurrentTopicID() {
        users = DataLoader.getUsers();
        assertEquals(NIL_UUID, users.get(0).getCurrentTopicID());
    }

    @Test
    public void testGetUserCurrentLessonID() {
        users = DataLoader.getUsers();
        assertEquals(NIL_UUID, users.get(0).getCurrentLessonID());
    }

    // Course Methods
    @Test
    public void testGetCoursesSize() {
        courses = DataLoader.getCourses();
        assertEquals(1, courses.size());
    }

    @Test
    public void testGetCoursesSizeZero() {
        CourseList.getInstance().getCourses().clear();
        DataWriter.saveCourses();
        assertEquals(0, courses.size());
    }

    @Test
    public void testGetCourseUUID() {
        courses = DataLoader.getCourses();
        assertEquals(NIL_UUID, courses.get(0).getId());
    }

    @Test
    public void testGetCourseTitle() {
        courses = DataLoader.getCourses();
        assertEquals("course title", courses.get(0).getTitle());
    }

    @Test
    public void testGetCourseLanguage() {
        courses = DataLoader.getCourses();
        assertEquals(Language.PYTHON, courses.get(0).getLanguage());
    }

    @Test
    public void testGetCourseDescription() {
        courses = DataLoader.getCourses();
        assertEquals("course description", courses.get(0).getDescription());
    }

    @Test
    public void testGetCourseTeacherFullName() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getFullName(), courses.get(0).getTeacher().getFullName());
    }

    @Test
    public void testGetCourseTeacherUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getId(), courses.get(0).getTeacher().getId());
    }

    @Test
    public void testGetCourseTeacherFirstName() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getFirstName(), courses.get(0).getTeacher().getFirstName());
    }

    @Test
    public void testGetCourseTeacherLastName() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getLastName(), courses.get(0).getTeacher().getLastName());
    }

    @Test
    public void testGetCourseTeacherUserName() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getUserName(), courses.get(0).getTeacher().getUserName());
    }

    @Test
    public void testGetCourseTeacherEmail() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getEmail(), courses.get(0).getTeacher().getEmail());
    }

    @Test
    public void testGetCourseTeacherType() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getType(), courses.get(0).getTeacher().getType());
    }

    @Test
    public void testGetCourseTeacherPassword() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getPassword(), courses.get(0).getTeacher().getPassword());
    }

    @Test
    public void testGetCourseTeacherCurrentCourseID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getCurrentCourseID(), courses.get(0).getTeacher().getCurrentCourseID());
    }

    @Test
    public void testGetCourseTeacherCurrentTopicID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getCurrentTopicID(), courses.get(0).getTeacher().getCurrentTopicID());
    }

    @Test
    public void testGetCourseTeacherCurrentLessonID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTeacher().getCurrentLessonID(), courses.get(0).getTeacher().getCurrentLessonID());
    }

    @Test
    public void testGetCourseStudentProgressStudentFullName() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getFullName(), courses.get(0).getStudentProgresses().get(0).getStudent().getFullName());
    }

    @Test
    public void testGetCourseStudentProgressStudentUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getId(), courses.get(0).getStudentProgresses().get(0).getStudent().getId());
    }

    @Test
    public void testGetCourseStudentProgressStudentFirstName() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getFirstName(), courses.get(0).getStudentProgresses().get(0).getStudent().getFirstName());
    }

    @Test
    public void testGetCourseStudentProgressStudentLastName() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getLastName(), courses.get(0).getStudentProgresses().get(0).getStudent().getLastName());
    }

    @Test
    public void testGetCourseStudentProgressStudentUserName() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getUserName(), courses.get(0).getStudentProgresses().get(0).getStudent().getUserName());
    }

    @Test
    public void testGetCourseStudentProgressStudentEmail() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getEmail(), courses.get(0).getStudentProgresses().get(0).getStudent().getEmail());
    }

    @Test
    public void testGetCourseStudentProgressStudentType() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getType(), courses.get(0).getStudentProgresses().get(0).getStudent().getType());
    }

    @Test
    public void testGetCourseStudentProgressStudentPassword() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getPassword(), courses.get(0).getStudentProgresses().get(0).getStudent().getPassword());
    }

    @Test
    public void testGetCourseStudentProgressStudentCurrentCourseID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getCurrentCourseID(), courses.get(0).getStudentProgresses().get(0).getStudent().getCurrentCourseID());
    }

    @Test
    public void testGetCourseStudentProgressStudentCurrentTopicID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getCurrentTopicID(), courses.get(0).getStudentProgresses().get(0).getStudent().getCurrentTopicID());
    }

    @Test
    public void testGetCourseStudentProgressStudentCurrentLessonID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getStudent().getCurrentLessonID(), courses.get(0).getStudentProgresses().get(0).getStudent().getCurrentLessonID());
    }

    @Test
    public void testGetCourseStudentProgressGradeUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getGrades().get(0).getId(), courses.get(0).getStudentProgresses().get(0).getGrades().get(0).getId());
    }

    @Test
    public void testGetCourseStudentProgressGradeQuizUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getStudentProgresses().get(0).getGrades().get(0).getQuizID(), courses.get(0).getStudentProgresses().get(0).getGrades().get(0).getQuizID());
    }

    @Test
    public void testGetCourseStudentProgressGradeQuizGradePercentage() {
        courses = DataLoader.getCourses();
        assertSame(createCourse().getStudentProgresses().get(0).getGrades().get(0).getGradePercentage(), courses.get(0).getStudentProgresses().get(0).getGrades().get(0).getGradePercentage());
    }

    @Test
    public void testGetCourseTopicUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getId(), courses.get(0).getTopics().get(0).getId());
    }

    @Test
    public void testGetCourseTopicTitle() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getTitle(), courses.get(0).getTopics().get(0).getTitle());
    }

    @Test
    public void testGetCourseTopicDescription() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getDescription(), courses.get(0).getTopics().get(0).getDescription());
    }

    @Test
    public void testGetCourseTopicQuizUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getQuiz().getId(), courses.get(0).getTopics().get(0).getQuiz().getId());
    }

    @Test
    public void testGetCourseTopicQuizTitle() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getQuiz().getTitle(), courses.get(0).getTopics().get(0).getQuiz().getTitle());
    }

    @Test
    public void testGetCourseTopicQuizDescription() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getQuiz().getDescription(), courses.get(0).getTopics().get(0).getQuiz().getDescription());
    }

    @Test
    public void testGetCourseTopicQuizQuestionUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getQuiz().getQuestions().get(0).getId(), courses.get(0).getTopics().get(0).getQuiz().getQuestions().get(0).getId());
    }

    @Test
    public void testGetCourseTopicQuizQuestion() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getQuiz().getQuestions().get(0).getQuestion(), courses.get(0).getTopics().get(0).getQuiz().getQuestions().get(0).getQuestion());
    }

    @Test
    public void testGetCourseTopicQuizQuestionChoices() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getQuiz().getQuestions().get(0).getChoices(), courses.get(0).getTopics().get(0).getQuiz().getQuestions().get(0).getChoices());
    }

    @Test
    public void testGetCourseTopicQuizQuestionCorrectAnswerIndex() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getQuiz().getQuestions().get(0).getCorrectAnswerIndex(), courses.get(0).getTopics().get(0).getQuiz().getQuestions().get(0).getCorrectAnswerIndex());
    }

    @Test
    public void testGetCourseTopicCommentUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getComments().get(0).getId(), courses.get(0).getTopics().get(0).getComments().get(0).getId());
    }

    @Test // remove later
    public void testGetCourseTopicCommentContent() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getComments().get(0).getContent(), courses.get(0).getTopics().get(0).getComments().get(0).getContent());
    }

    @Test // remove later
    public void testGetCourseTopicCommentDate() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getComments().get(0).getDate(), courses.get(0).getTopics().get(0).getComments().get(0).getDate());
    }

    @Test
    public void testGetCourseTopicCommentReplys() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getComments().get(0).getReplys(), courses.get(0).getTopics().get(0).getComments().get(0).getReplys());
    }

    @Test
    public void testGetCourseTopicCommentUser() {
        courses = DataLoader.getCourses();
        assertSame(createCourse().getTopics().get(0).getComments().get(0).getUser(), courses.get(0).getTopics().get(0).getComments().get(0).getUser());
    }

    @Test
    public void testGetCourseTopicLessonUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getLessons().get(0).getId(), courses.get(0).getTopics().get(0).getLessons().get(0).getId());
    }

    @Test
    public void testGetCourseTopicLessonTitle() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getLessons().get(0).getTitle(), courses.get(0).getTopics().get(0).getLessons().get(0).getTitle());
    }

    @Test
    public void testGetCourseTopicLessonContent() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getTopics().get(0).getLessons().get(0).getContent(), courses.get(0).getTopics().get(0).getLessons().get(0).getContent());
    }

   @Test
   public void testGetCourseReviewUUID() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getReviews().get(0).getId(), courses.get(0).getReviews().get(0).getId());
   }

   @Test
   public void testGetCourseReviewRating() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getReviews().get(0).getRating(), courses.get(0).getReviews().get(0).getRating());
   }

   @Test
   public void testGetCourseReviewDate() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getReviews().get(0).getDate(), courses.get(0).getReviews().get(0).getDate());
   }

   @Test
   public void testGetCourseReviewComment() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getReviews().get(0).getComment(), courses.get(0).getReviews().get(0).getComment());
   }

   @Test
   public void testGetCourseReviewReviewer() {
        courses = DataLoader.getCourses();
        assertEquals(createCourse().getReviews().get(0).getReviewer(), courses.get(0).getReviews().get(0).getReviewer());
   }

   private Course createCourse() {
    User teacher = new Teacher(TEACHER_UUID, "jimmy", "john", "jimmyj", "jimmyj@gmail.com", 
    "howtojimmy31", NIL_UUID, NIL_UUID, NIL_UUID);

    ArrayList<Comment> comments = new ArrayList<Comment>();

    comments.add(new Comment(NIL_UUID, teacher, DATE, "blank comment", new ArrayList<Comment>()));

    ArrayList<Lesson> lessons = new ArrayList<Lesson>();

    lessons.add(new Lesson(NIL_UUID, "lesson title", "lesson content", comments));

    ArrayList<Question> questions = new ArrayList<Question>();

    ArrayList<String> choices = new ArrayList<String>();

    choices.add("question choice");

    questions.add(new Question(NIL_UUID, "question", choices, 0));

    Quiz quiz = new Quiz(NIL_UUID, "quiz title", "quiz description", questions);

    ArrayList<Topic> topics = new ArrayList<Topic>();

    topics.add(new Topic(NIL_UUID, "topic title", "topic description", quiz, lessons, comments));

    ArrayList<StudentProgress> studentProgresses = new ArrayList<StudentProgress>();

    ArrayList<Grade> grades = new ArrayList<Grade>();

    grades.add(new Grade(NIL_UUID, NIL_UUID, 0));

    User student = new Student(STUDENT_UUID, "jay", "jones", "jayj",
    "jayj@gmail.com", "jj1234", NIL_UUID, NIL_UUID, NIL_UUID);

    studentProgresses.add(new StudentProgress(student, grades));

    ArrayList<Review> reviews = new ArrayList<Review>();

    reviews.add(new Review(NIL_UUID, student, DATE, 5, "review comment"));

    Course course = new Course(NIL_UUID, "course title", Language.PYTHON, "course description", (Teacher) teacher, 
    topics, reviews, comments, studentProgresses);

    users.add(teacher);
    users.add(student);
    DataWriter.saveUsers();

    return course;
}
   

}
