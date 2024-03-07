package test;

import backEnd.Course;
import backEnd.Language;
import backEnd.Lesson;
import backEnd.Quiz;
import backEnd.Teacher;
import backEnd.Topic;
import backEnd.Review;
import backEnd.Student;
import backEnd.Comment;
import backEnd.StudentProgress;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
/**
 * @author Jordan Wood
 */

public class CourseTest {
    private Course course;
    private UUID id;
    private String title;
    private Language language;
    private String description;
    private Teacher teacher;
    private ArrayList<Topic> topics;
    private ArrayList<Review> reviews;
    private ArrayList<Comment> comments;
    private ArrayList<StudentProgress> studentProgresses;
    private Topic topic1;
    private Topic topic2;
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Before
    public void setUp() {
        course = new Course();
        String uuidString = "00000000-0000-0000-0000-000000000000";
        id = UUID.fromString(uuidString);
        title = "none";
        language = Language.NONE;
        description = "none";
        teacher = new Teacher();
        topics = new ArrayList<Topic>();
        reviews = new ArrayList<Review>();
        comments = new ArrayList<Comment>();
        studentProgresses = new ArrayList<StudentProgress>();
        topic1 = new Topic();
        topic2 = new Topic();
        topics.add(topic1);
        topics.add(topic2);
    }

    @After
    public void tearDown() {
        course = null;
        id = null;
        title = null;
        language = null;
        description = null;
        teacher = null;
        topics = null;
        reviews = null;
        comments = null;
        studentProgresses = null;
    }

    @Test
    public void testDefaultConstructor() {
        Course course = new Course();
        assertEquals(Course.NIL_UUID, course.getId());
        assertEquals("none", course.getTitle());
        assertEquals(Language.NONE, course.getLanguage());
        assertEquals("none", course.getDescription());
        assertNotNull(course.getTeacher());
        assertNotNull(course.getTopics());
        assertNotNull(course.getReviews());
        assertNotNull(course.getComments());
        assertNotNull(course.getStudentProgresses());
    }
    
    @Test
    public void testConstructorWithTitleLanguageDescriptionTeacherTopicsReviewsCommentsStudentProgresses() {
        UUID id = UUID.randomUUID();
        Course course = new Course("Web Development", Language.JAVASCRIPT, "A course about web development",
                teacher, topics, reviews, comments, studentProgresses);
        assertNotNull(course.getId());
        assertEquals("Web Development", course.getTitle());
        assertEquals(Language.JAVASCRIPT, course.getLanguage());
        assertEquals("A course about web development", course.getDescription());
        assertEquals(teacher, course.getTeacher());
        assertEquals(topics, course.getTopics());
        assertEquals(reviews, course.getReviews());
        assertEquals(comments, course.getComments());
        assertEquals(studentProgresses, course.getStudentProgresses());
    }
    
    @Test
    public void testConstructorWithIdTitleLanguageDescriptionTeacherTopicsReviewsCommentsStudentProgresses() {
        Course course = new Course(UUID.randomUUID(), "Web Development", Language.JAVASCRIPT, "A course about web development",
                teacher, topics, reviews, comments, studentProgresses);
        assertNotNull(course.getId());
        assertEquals("Web Development", course.getTitle());
        assertEquals(Language.JAVASCRIPT, course.getLanguage());
        assertEquals("A course about web development", course.getDescription());
        assertEquals(teacher, course.getTeacher());
        assertEquals(topics, course.getTopics());
        assertEquals(reviews, course.getReviews());
        assertEquals(comments, course.getComments());
        assertEquals(studentProgresses, course.getStudentProgresses());
    }

    @Test
    public void testGetTopicByUUID() {
        Topic foundTopic1 = course.getTopicByUUID(NIL_UUID);
        assertEquals(topic1, foundTopic1);

        Topic foundTopic2 = course.getTopicByUUID(id);
        assertEquals(topic2, foundTopic2);

        UUID invalidTopicId = UUID.randomUUID();
        Topic nullTopic = course.getTopicByUUID(invalidTopicId);
        assertNull(nullTopic);
    }

    @Test
    public void testGetReviewByUUID() {
        Review review = new Review();
        UUID reviewId = review.getId();
        course.getReviews().add(review);

        Review foundReview = course.getReviewByUUID(reviewId);
        assertEquals(review, foundReview);

        UUID invalidReviewId = UUID.randomUUID();
        Review nullReview = course.getReviewByUUID(invalidReviewId);
        assertNull(nullReview);
    }

    @Test
    public void testGetCommentByUUID() {
        Comment comment = new Comment();
        UUID commentId = comment.getId();
        course.getComments().add(comment);

        Comment foundComment = course.getCommentByUUID(commentId);
        assertEquals(comment, foundComment);

        UUID invalidCommentId = UUID.randomUUID();
        Comment nullComment = course.getCommentByUUID(invalidCommentId);
        assertNull(nullComment);
    }

    @Test
    public void testGetStudentProgressByStudentUUID() {
        Student student1 = new Student();
        Student student2 = new Student();

        StudentProgress progress1 = new StudentProgress(student1, null);
        StudentProgress progress2 = new StudentProgress(student2, null);

        UUID student1Id = student1.getId();
        UUID student2Id = student2.getId();

        course.getStudentProgresses().add(progress1);
        course.getStudentProgresses().add(progress2);

        StudentProgress foundProgress1 = course.getStudentProgressByStudentUUID(student1Id);
        assertEquals(progress1, foundProgress1);

        StudentProgress foundProgress2 = course.getStudentProgressByStudentUUID(student2Id);
        assertEquals(progress2, foundProgress2);

        UUID invalidStudentId = UUID.randomUUID();
        StudentProgress nullProgress = course.getStudentProgressByStudentUUID(invalidStudentId);
        assertNull(nullProgress);
    }

    @Test
public void testCreateTopic() {
    // create a new course object
    Course course = new Course();

    // create topic data
    String title = "Test Topic";
    String description = "A test topic for unit testing";
    Quiz quiz = new Quiz();
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    ArrayList<Comment> comments = new ArrayList<Comment>();

    // create a new topic
    Topic topic = course.createTopic(title, description, quiz, lessons, comments);

    // check if topic was created and added to course's topic list
    assertEquals(title, topic.getTitle());
    assertEquals(description, topic.getDescription());
    assertEquals(quiz, topic.getQuiz());
    assertEquals(lessons, topic.getLessons());
    assertEquals(comments, topic.getComments());
    assertTrue(course.getTopics().contains(topic));
}
}
