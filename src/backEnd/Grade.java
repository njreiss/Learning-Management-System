package backEnd;

import java.util.UUID;

/**
 * A class representing a grade object, with a unique id, quiz id and grade percentage.
 */
public class Grade {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private UUID id;
    private UUID quizID;
    private static double gradePercentage;

    /**
     * Constructs a new Grade object with NIL_UUID, NIL_UUID, 
     * and -1 as the default values for id, quizID and gradePercentage respectively.
     */
    public Grade() {
        this.id = NIL_UUID;
        this.quizID = NIL_UUID;
        Grade.gradePercentage = -1;
    }

    /**
     * Constructs a new Grade object with the given id, quizID and gradePercentage values.
     * @param id The unique identifier for the grade object.
     * @param quizID The unique identifier for the quiz object that the grade is associated with.
     * @param gradePercentage The percentage score of the quiz.
     */
    public Grade(UUID id, UUID quizID, double gradePercentage) {
        this.id = id;
        this.quizID = quizID;
        this.gradePercentage = gradePercentage;
    }

    /**
     * Constructs a new Grade object with a randomly generated UUID as the id value, 
     * and the given quizID and gradePercentage values.
     * @param quizID The unique identifier for the quiz object that the grade is associated with.
     * @param gradePercentage The percentage score of the quiz.
     */
    public Grade(UUID quizID, double gradePercentage) {
        this.id = UUID.randomUUID();
        this.quizID = quizID;
        this.gradePercentage = gradePercentage;
    }

    // GETTERS AND SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getQuizID() {
        return quizID;
    }

    public void setQuiz(UUID quizID) {
        this.quizID = quizID;
    }

    public static double getGradePercentage() {
        return gradePercentage;
    }

    public void setGradePercentage(double gradePercentage) {
        this.gradePercentage = gradePercentage;
    }

    /**
     * Returns a string representation of the Grade object, including its id, quizID, and grade percentage.
     * @return a string representation of the Grade object
     */
    @Override
    public String toString() {
        return "Grade [id=" + id + ", quizID=" + quizID + ", gradePercentage=" + gradePercentage + "]";
    }
}
