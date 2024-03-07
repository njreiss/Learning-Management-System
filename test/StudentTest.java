package test;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

import backEnd.Student;

/**
 * @author Jordan Wood
 */

public class StudentTest {
    @Test
    public void testStudentDefaultConstructor() {
        Student student = new Student();
        assertEquals(Student.NIL_UUID, student.getId());
        assertEquals("student", student.getType());
        assertEquals("none", student.getFirstName());
        assertEquals("none", student.getLastName());
        assertEquals("none", student.getUserName());
        assertEquals("none", student.getEmail());
        assertEquals("none", student.getPassword());
        assertEquals(Student.NIL_UUID, student.getCurrentCourseID());
        assertEquals(Student.NIL_UUID, student.getCurrentTopicID());
        assertEquals(Student.NIL_UUID, student.getCurrentLessonID());
    }

    @Test
    public void testStudentUUIDConstructor() {
        UUID id = UUID.randomUUID();
        Student student = new Student(id, "John", "Doe", "johndoe", "johndoe@example.com", "password", 
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        assertEquals(id, student.getId());
        assertEquals("student", student.getType());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("johndoe", student.getUserName());
        assertEquals("johndoe@example.com", student.getEmail());
        assertEquals("password", student.getPassword());
        assertEquals(student.getCurrentCourseID(), student.getCurrentCourseID());
        assertEquals(student.getCurrentTopicID(), student.getCurrentTopicID());
        assertEquals(student.getCurrentLessonID(), student.getCurrentLessonID());
    }

    @Test
    public void testStudentConstructor() {
        Student student = new Student("Jane", "Doe", "janedoe", "janedoe@example.com", "password",
                UUID.fromString("00000000-0000-0000-0000-000000000000"), UUID.fromString("00000000-0000-0000-0000-000000000000"), UUID.fromString("00000000-0000-0000-0000-000000000000"));
        assertEquals(Student.NIL_UUID, student.NIL_UUID);
        assertEquals("student", student.getType());
        assertEquals("Jane", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("janedoe", student.getUserName());
        assertEquals("janedoe@example.com", student.getEmail());
        assertEquals("password", student.getPassword());
        assertEquals(student.getCurrentCourseID(), student.getCurrentCourseID());
        assertEquals(student.getCurrentTopicID(), student.getCurrentTopicID());
        assertEquals(student.getCurrentLessonID(), student.getCurrentLessonID());
    }
}
