package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import backEnd.Grade;

/**
 * @author Jordan Wood
 */

public class GradeTest {
    private UUID id;
    private UUID quizId;
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Before
    public void setUp() {
        String uuidString = "00000000-0000-0000-0000-000000000000";
        id = UUID.fromString(uuidString);
        quizId = UUID.fromString(uuidString);
    }
    
    @Test
    public void testGradeDefaultConstructor() {
        Grade grade = new Grade();
        assertEquals(Grade.NIL_UUID, grade.getId());
        assertEquals(Grade.NIL_UUID, grade.getQuizID());
        assertEquals(-1.0, grade.getGradePercentage(), 0.001);
    }

    @Test
    public void testGradeConstructorWithId() {
        String uuidString = "00000000-0000-0000-0000-000000000000";
        UUID id = UUID.fromString(uuidString);
        UUID quizID = UUID.fromString(uuidString);
        double gradePercentage = 80.0;
        Grade grade = new Grade(id, quizID, gradePercentage);
        assertEquals(id, grade.getId());
        assertEquals(quizID, grade.getQuizID());
        assertEquals(gradePercentage, Grade.getGradePercentage(), 0.001);
    }

    @Test
    public void testGradeConstructorWithoutId() {
        UUID quizId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        double gradePercentage = 85.0;
        Grade grade = new Grade(quizId, gradePercentage);
        assertEquals(Grade.NIL_UUID, grade.NIL_UUID);
        assertEquals(quizId, grade.getQuizID());
        assertEquals(gradePercentage, Grade.getGradePercentage(), 0.001);
    }

    @Test
    public void testToString() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UUID quizID = UUID.fromString("22222222-2222-2222-2222-222222222222");
        double gradePercentage = 87.5;
        Grade grade = new Grade(id, quizID, gradePercentage);
        String expectedOutput = "Grade [id=00000000-0000-0000-0000-000000000000, quizID=22222222-2222-2222-2222-222222222222, gradePercentage=87.5]";
        assertEquals(expectedOutput, grade.toString());
}
}
