package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.UUID;
import org.junit.Test;
import backEnd.Teacher;

/**
 * @author Jordan Wood
 */

public class TeacherTest {
    @Test
    public void testConstructorWithUUID() {
        UUID id = UUID.randomUUID();
        Teacher teacher = new Teacher(id, "John", "Doe", "jdoe", "jdoe@example.com", "password", null, null, null);
        assertEquals(id, teacher.getId());
        assertEquals("teacher", teacher.getType());
        assertEquals("John", teacher.getFirstName());
        assertEquals("Doe", teacher.getLastName());
        assertEquals("jdoe", teacher.getUserName());
        assertEquals("jdoe@example.com", teacher.getEmail());
        assertEquals("password", teacher.getPassword());
        assertNull(teacher.getCurrentCourseID());
        assertNull(teacher.getCurrentTopicID());
        assertNull(teacher.getCurrentLessonID());
    }

    @Test
    public void testConstructorWithoutUUID() {
        Teacher teacher = new Teacher("John", "Doe", "jdoe", "jdoe@example.com", "password", null, null, null);
        assertNotNull(teacher.getId());
        assertEquals("teacher", teacher.getType());
        assertEquals("John", teacher.getFirstName());
        assertEquals("Doe", teacher.getLastName());
        assertEquals("jdoe", teacher.getUserName());
        assertEquals("jdoe@example.com", teacher.getEmail());
        assertEquals("password", teacher.getPassword());
        assertNull(teacher.getCurrentCourseID());
        assertNull(teacher.getCurrentTopicID());
        assertNull(teacher.getCurrentLessonID());
    }

    @Test
    public void testToString() {
        Teacher teacher = new Teacher(UUID.randomUUID(), "Jane", "Smith", "jsmith", "jsmith@example.com", "password", null, null, null);
        String expectedString = "User [id=" + teacher.getId() + ", type=teacher, firstName=Jane, lastName=Smith, userName=jsmith, email=jsmith@example.com, password=password, currentCourseID=null, currentTopicID=null, currentLessonID=null]";
        assertEquals(expectedString, teacher.toString());
    }
}
