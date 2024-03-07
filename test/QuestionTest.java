package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;
import backEnd.Question;

/**
 * @author Jordan Wood
 */

public class QuestionTest {
    @Test
    public void testDefaultConstructor() {
        Question question = new Question();
        assertEquals(Question.NIL_UUID, question.getId());
        assertEquals("none", question.getQuestion());
        assertTrue(question.getChoices().isEmpty());
        assertEquals(-1, question.getCorrectAnswerIndex());
    }

    @Test
    public void testConstructorWithRandomUUID() {
        String questionText = "What is the capital of France?";
        ArrayList<String> choices = new ArrayList<>();
        choices.add("London");
        choices.add("Paris");
        choices.add("Berlin");
        int correctAnswerIndex = 1;

        Question question = new Question(questionText, choices, correctAnswerIndex);

        assertNotNull(question.getId());
        assertEquals(questionText, question.getQuestion());
        assertEquals(choices, question.getChoices());
        assertEquals(correctAnswerIndex, question.getCorrectAnswerIndex());
    }

    @Test
    public void testConstructorWithSpecifiedUUID() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
        String questionText = "What is the largest planet in our solar system?";
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Mercury");
        choices.add("Venus");
        choices.add("Earth");
        choices.add("Jupiter");
        int correctAnswerIndex = 3;

        Question question = new Question(id, questionText, choices, correctAnswerIndex);

        assertEquals(id, question.getId());
        assertEquals(questionText, question.getQuestion());
        assertEquals(choices, question.getChoices());
        assertEquals(correctAnswerIndex, question.getCorrectAnswerIndex());
    }

    @Test
    public void testToString() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String questionText = "none";
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Mercury");
        choices.add("Venus");
        choices.add("Earth");
        choices.add("Jupiter");
        int correctAnswerIndex = 3;

        Question question = new Question(id, questionText, choices, correctAnswerIndex);
        String expected = "Question [id=" + id + ", question=" + questionText + ", choices=" + choices + ", correctAnswerIndex=" + correctAnswerIndex + "]";
        assertEquals(expected, question.toString());
    }
}

