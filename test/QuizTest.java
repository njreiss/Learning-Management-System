package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import org.junit.Test;
import backEnd.Question;
import backEnd.Quiz;

/**
 * @author Jordan Wood
 */

public class QuizTest {
    @Test
    public void testConstructorDefault() {
        Quiz quiz = new Quiz();
        assertEquals(quiz.getId(), Quiz.NIL_UUID);
        assertEquals(quiz.getTitle(), "none");
        assertEquals(quiz.getDescription(), "none");
        assertTrue(quiz.getQuestions().isEmpty());
    }
    
    // test constructor with UUID, title, description and questions
    @Test
    public void testConstructorWithParams() {
        UUID id = UUID.randomUUID();
        String title = "Math Quiz";
        String description = "Test your math skills!";
        ArrayList<Question> questions = new ArrayList<Question>();
        Quiz quiz = new Quiz(id, title, description, questions);
        assertEquals(quiz.getId(), id);
        assertEquals(quiz.getTitle(), title);
        assertEquals(quiz.getDescription(), description);
        assertEquals(quiz.getQuestions(), questions);
    }
    
    // test constructor with title, description and questions
    @Test
    public void testConstructorWithRandomUUID() {
        String title = "Math Quiz";
        String description = "Test your math skills!";
        ArrayList<Question> questions = new ArrayList<Question>();
        Quiz quiz = new Quiz(title, description, questions);
        assertNotNull(quiz.getId());
        assertEquals(quiz.getTitle(), title);
        assertEquals(quiz.getDescription(), description);
        assertEquals(quiz.getQuestions(), questions);
    }

    @Test
    public void testGetQuestionByUUID() {
        // Create a new quiz with a list of questions
        ArrayList<Question> questions = new ArrayList<Question>();
        Question q1 = new Question("What is the capital of France?", new ArrayList<String>(Arrays.asList("London", "Paris", "Berlin")), 1);
        Question q2 = new Question("What is the tallest mountain in the world?", new ArrayList<String>(Arrays.asList("Everest", "Kilimanjaro", "Denali")), 0);
        questions.add(q1);
        questions.add(q2);
        Quiz quiz = new Quiz(UUID.randomUUID(), "General Knowledge", "A quiz about general knowledge.", questions);

        // Test finding a question by UUID
        Question expectedQuestion = q1;
        Question actualQuestion = quiz.getQuestionByUUID(q1.getId());
        assertEquals(expectedQuestion, actualQuestion);

        // Test finding a question that doesn't exist
        Question nullQuestion = quiz.getQuestionByUUID(UUID.randomUUID());
        assertNull(nullQuestion);
    }

    @Test
    public void testToString() {
        // Create a new quiz
        Quiz quiz = new Quiz(UUID.randomUUID(), "General Knowledge", "A quiz about general knowledge.", new ArrayList<Question>());

        // Test toString()
        String expectedString = "Quiz [id=" + quiz.getId() + ", title=General Knowledge, description=A quiz about general knowledge., questions=[]]";
        String actualString = quiz.toString();
        assertEquals(expectedString, actualString);
    }
}
