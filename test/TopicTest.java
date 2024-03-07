package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import backEnd.Lesson;
import backEnd.Quiz;
import backEnd.Topic;
import backEnd.Comment;

/**
 * @author Jordan Wood
 */

public class TopicTest {
    @Test
    public void testDefaultConstructor() {
        Topic topic = new Topic();
        assertEquals(Topic.NIL_UUID, topic.getId());
        assertEquals("none", topic.getTitle());
        assertEquals("none", topic.getDescription());
        assertNotNull(topic.getQuiz());
        assertNotNull(topic.getLessons());
        assertNotNull(topic.getComments());
        assertTrue(topic.getLessons().isEmpty());
        assertTrue(topic.getComments().isEmpty());
    }

    @Test
    public void testConstructorWithParameters() {
        Quiz quiz = new Quiz();
        ArrayList<Lesson> lessons = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        Topic topic = new Topic("Math", "Introduction to Algebra", quiz, lessons, comments);
        assertNotNull(topic.getId());
        assertEquals("Math", topic.getTitle());
        assertEquals("Introduction to Algebra", topic.getDescription());
        assertEquals(quiz, topic.getQuiz());
        assertEquals(lessons, topic.getLessons());
        assertEquals(comments, topic.getComments());
    }

    @Test
    public void testConstructorWithUUID() {
        UUID id = UUID.randomUUID();
        Quiz quiz = new Quiz();
        ArrayList<Lesson> lessons = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        Topic topic = new Topic(id, "Math", "Introduction to Algebra", quiz, lessons, comments);
        assertEquals(id, topic.getId());
        assertEquals("Math", topic.getTitle());
        assertEquals("Introduction to Algebra", topic.getDescription());
        assertEquals(quiz, topic.getQuiz());
        assertEquals(lessons, topic.getLessons());
        assertEquals(comments, topic.getComments());
    }

    @Test
    public void testGetLessonByUUID() {
        Lesson lesson1 = new Lesson("Lesson 1", "Content 1");
        Lesson lesson2 = new Lesson("Lesson 2", "Content 2");
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson1);
        lessons.add(lesson2);
        Topic topic = new Topic("Math", "Introduction to Algebra", new Quiz(), lessons, new ArrayList<Comment>());
        assertEquals(lesson1, topic.getLessonByUUID(lesson1.getId()));
        assertEquals(lesson2, topic.getLessonByUUID(lesson2.getId()));
        assertNull(topic.getLessonByUUID(UUID.randomUUID()));
    }

    @Test
    public void testGetCommentByUUID() {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);
        Topic topic = new Topic("Math", "Introduction to Algebra", new Quiz(), new ArrayList<Lesson>(), comments);
        assertEquals(comment1, topic.getCommentByUUID(comment1.getId()));
        assertEquals(comment2, topic.getCommentByUUID(comment2.getId()));
        assertNull(topic.getCommentByUUID(UUID.randomUUID()));
    }

    @Test
    public void testCreateLesson() {
        Topic topic = new Topic();
        Lesson lesson = topic.createLesson("Lesson 1", "Content 1");
        assertNotNull(lesson);
        assertEquals("Lesson 1", lesson.getTitle());
        assertEquals("Content 1", lesson.getContent());
        assertEquals(1, topic.getLessons().size());
        assertEquals(lesson, topic.getLessons().get(0));
    }

    @Test
    public void testToString() {
        UUID id = UUID.randomUUID();
        String title = "Mathematics";
        String description = "This topic covers various mathematical concepts";
        Quiz quiz = new Quiz();
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Topic topic = new Topic(id, title, description, quiz, lessons, comments);
        String expected = "Topic [id=" + id + ", title=" + title + ", description=" + description 
                        + ", quiz=" + quiz + ", lessons=" + lessons + ", comments=" + comments + "]";
        String actual = topic.toString();
        assertEquals(expected, actual);
    }
}
