package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;

import backEnd.Comment;
import backEnd.Lesson;

/**
 * @author Jordan Wood
 */

public class LessonTest {
    @Test
    public void testLessonDefaultConstructor() {
        Lesson lesson = new Lesson();
        assertEquals(Lesson.NIL_UUID, lesson.getId());
        assertEquals("none", lesson.getTitle());
        assertEquals("none", lesson.getContent());
        assertNotNull(lesson.getComments());
    }
    
    @Test
    public void testLessonConstructorWithTitleAndContent() {
        String title = "Test Title";
        String content = "Test Content";
        Lesson lesson = new Lesson(title, content);
        assertNotNull(lesson.getId());
        assertEquals(title, lesson.getTitle());
        assertEquals(content, lesson.getContent());
        assertNull(lesson.getComments());
    }
    
    @Test
    public void testLessonConstructorWithTitleContentAndComments() {
        String title = "Test Title";
        String content = "Test Content";
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        Lesson lesson = new Lesson(title, content, comments);
        assertNotNull(lesson.getId());
        assertEquals(title, lesson.getTitle());
        assertEquals(content, lesson.getContent());
        assertEquals(comments, lesson.getComments());
    }
    
    @Test
    public void testLessonConstructorWithIdTitleContentAndComments() {
        UUID id = UUID.randomUUID();
        String title = "Test Title";
        String content = "Test Content";
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        Lesson lesson = new Lesson(id, title, content, comments);
        assertEquals(id, lesson.getId());
        assertEquals(title, lesson.getTitle());
        assertEquals(content, lesson.getContent());
        assertEquals(comments, lesson.getComments());
    }

    @Test
    public void testLessonToString() {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Comment comment = new Comment();
        comments.add(comment);
        Lesson lesson = new Lesson("test title", "test content", comments);
        String expected = "Lesson [id=" + lesson.getId() + ", title=test title, content=test content, comments=" + comments.toString() + "]";
        String actual = lesson.toString();
        assertEquals(expected, actual);
    }
}
