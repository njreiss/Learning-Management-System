package test;

import backEnd.Admin;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import java.util.UUID;

/**
 * @author Jordan Wood
 */

public class AdminTest extends Admin {
    private UUID id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private UUID currentCourseID;
    private UUID currentTopicID;
    private UUID currentLessonID;
    private Admin admin;
    
    @Before
    public void setUp() {
        String uuidString = "00000000-0000-0000-0000-000000000000";
        id = UUID.fromString(uuidString);
        firstName = "John";
        lastName = "Doe";
        userName = "johndoe";
        email = "johndoe@example.com";
        password = "password";
        currentCourseID = UUID.fromString(uuidString);
        currentTopicID = UUID.fromString(uuidString);
        currentLessonID = UUID.fromString(uuidString);
        admin = new Admin(id, firstName, lastName, userName, email, password, currentCourseID, currentTopicID, currentLessonID);
    }
    
    @After
    public void tearDown() {
        id = null;
        firstName = null;
        lastName = null;
        userName = null;
        email = null;
        password = null;
        currentCourseID = null;
        currentTopicID = null;
        currentLessonID = null;
        admin = null;
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(Admin.NIL_UUID, admin.getId());
        assertEquals("admin", admin.getType());
        assertEquals("John", admin.getFirstName());
        assertEquals("Doe", admin.getLastName());
        assertEquals("johndoe", admin.getUserName());
        assertEquals("johndoe@example.com", admin.getEmail());
        assertEquals("password", admin.getPassword());
        assertEquals(Admin.NIL_UUID, admin.getCurrentCourseID());
        assertEquals(Admin.NIL_UUID, admin.getCurrentTopicID());
        assertEquals(Admin.NIL_UUID, admin.getCurrentLessonID());
    }

    @Test
    public void testParameterizedConstructor() {
        String uuidString = "00000000-0000-0000-0000-000000000000";
        UUID id = UUID.fromString(uuidString);
        UUID courseID = UUID.randomUUID();
        UUID topicID = UUID.randomUUID();
        UUID lessonID = UUID.randomUUID();

        Admin admin = new Admin(id, "John", "Doe", "johndoe", "johndoe@example.com", "password", courseID, topicID, lessonID);

        assertEquals(id, admin.getId());
        assertEquals("admin", admin.getType());
        assertEquals("John", admin.getFirstName());
        assertEquals("Doe", admin.getLastName());
        assertEquals("johndoe", admin.getUserName());
        assertEquals("johndoe@example.com", admin.getEmail());
        assertEquals("password", admin.getPassword());
        assertEquals(courseID, admin.getCurrentCourseID());
        assertEquals(topicID, admin.getCurrentTopicID());
        assertEquals(lessonID, admin.getCurrentLessonID());
    }

    @Test
    public void testParameterizedConstructorWithoutID() {
        UUID courseID = UUID.randomUUID();
        UUID topicID = UUID.randomUUID();
        UUID lessonID = UUID.randomUUID();

        Admin admin = new Admin("John", "Doe", "johndoe", "johndoe@example.com", "password", courseID, topicID, lessonID);

        assertEquals("admin", admin.getType());
        assertEquals("John", admin.getFirstName());
        assertEquals("Doe", admin.getLastName());
        assertEquals("johndoe", admin.getUserName());
        assertEquals("johndoe@example.com", admin.getEmail());
        assertEquals("password", admin.getPassword());
        assertEquals(courseID, admin.getCurrentCourseID());
        assertEquals(topicID, admin.getCurrentTopicID());
        assertEquals(lessonID, admin.getCurrentLessonID());
    }
}
