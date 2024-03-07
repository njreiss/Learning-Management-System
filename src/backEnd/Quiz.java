package backEnd;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This class represents a Quiz object
 */
public class Quiz {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    protected UUID id;
    protected String title;
    protected String description;
    protected ArrayList<Question> questions;

    /**
     * Constructs a new Quiz object with default values.
     */
    public Quiz() {
        this.id = NIL_UUID;
        this.title = "none";
        this.description = "none";
        this.questions = new ArrayList<Question>();
    }

    /**
     * Creates a new Quiz object with the specified UUID, title, description and list of questions.
     * @param id the UUID of the quiz
     * @param title the title of the quiz
     * @param description the description of the quiz
     * @param questions the list of questions in the quiz
     */
    public Quiz(UUID id, String title, String description, ArrayList<Question> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    /**
     * Creates a new Quiz object with a randomly generated UUID, the specified title, description, and list of questions.
     * @param title the title of the quiz
     * @param description the description of the quiz
     * @param questions the list of questions in the quiz
     */
    public Quiz(String title, String description, ArrayList<Question> questions) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    /**
     * Returns the Question object with the specified UUID, or null if the UUID is not found.
     * @param id the UUID of the Question to find
     * @return the Question object with the specified UUID, or null if the UUID is not found
     */    
    public Question getQuestionByUUID(UUID id) {
        for (Question question : questions) {
            if (question.getId().equals(id)) {
                return question;
            }
        }
        return null;
    }

    //GETTERS AND SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * Returns a string representation of the Quiz object.
     * @return a string representation of the Quiz object.
     */
    @Override
    public String toString() {
        return "Quiz [id=" + id + ", title=" + title + ", description=" + description + ", questions=" + questions
                + "]";
    }
}
