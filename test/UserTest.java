package test;

import org.junit.Assert;
import org.junit.Test;

import backEnd.Student;
import backEnd.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.UUID;

/**
 * @author Jordan Wood
 */

public class UserTest {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Test
    public void testUserConstructorWithGeneratedId() {
        User user = new Student();

        assertNotNull(user.getId());
        assertEquals("student", user.getType());
        assertEquals("none", user.getFirstName());
        assertEquals("none", user.getLastName());
        assertEquals("none", user.getUserName());
        assertEquals("none", user.getEmail());
        assertEquals("none", user.getPassword());
        assertNotNull(user.getCurrentCourseID());
        assertNotNull(user.getCurrentTopicID());
        assertNotNull(user.getCurrentLessonID());
    }

    @Test
    public void testUserConstructorWithGivenId() {
        User user = new Student();

        assertEquals(NIL_UUID, user.getId());
        assertEquals("student", user.getType());
        assertEquals("none", user.getFirstName());
        assertEquals("none", user.getLastName());
        assertEquals("none", user.getUserName());
        assertEquals("none", user.getEmail());
        assertEquals("none", user.getPassword());
        assertNotNull(user.getCurrentCourseID());
        assertNotNull(user.getCurrentTopicID());
        assertNotNull(user.getCurrentLessonID());
    }

    @Test
    public void testUserToString() {
        UUID userId = UUID.randomUUID();
        User user = new Student();

        String expectedString = "User [id=" + NIL_UUID + ", type=student, firstName=none, lastName=none, " +
                "userName=none, email=none, password=none, currentCourseID=" +
                user.getCurrentCourseID() + ", currentTopicID=" + user.getCurrentTopicID() +
                ", currentLessonID=" + user.getCurrentLessonID() + "]";

        assertEquals(expectedString, user.toString());
    }
}
