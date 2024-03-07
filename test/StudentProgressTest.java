package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import backEnd.Grade;
import backEnd.Student;
import backEnd.StudentProgress;
import backEnd.User;

/**
 * @author Jordan Wood
 */

public class StudentProgressTest {
    private User student;
    private ArrayList<Grade> grades;
    private StudentProgress studentProgress;

    @Before
    public void setUp() {
        student = new Student();
        grades = new ArrayList<>();
        grades.add(new Grade(student.getId(), 85));
        grades.add(new Grade(student.getId(), 90));
        studentProgress = new StudentProgress(student, grades);
    }

    @Test
    public void testDefaultConstructor() {
        StudentProgress sp = new StudentProgress();
        assertNotNull(sp);
        assertNotNull(sp.getStudent());
        assertNotNull(StudentProgress.getGrades());
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(studentProgress);
        assertEquals(student, studentProgress.getStudent());
        assertEquals(grades, StudentProgress.getGrades());
    }
    
    @Test
    public void testToString() {
        String expected = "StudentProgress [student=" + student + ", grades=" + grades + "]";
        assertEquals(expected, studentProgress.toString());
    }
}
