package backEnd;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This class represents a Lesson object
 */
public class Lesson {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private UUID id;
    private String title;
    private String content;
    private ArrayList<Comment> comments;

    /**
     * Constructs a new Lesson object with default values.
     */
    public Lesson() {
        this.id = NIL_UUID;
        this.title = "none";
        this.content = "none";
        this.comments = new ArrayList<Comment>();
    }

    /**
     * Constructs a new Lesson object with the given values.
     * @param title the title of the lesson
     * @param content the content of the lesson
     * @param comments the comments associated with the lesson
     */
    public Lesson(String title, String content, ArrayList<Comment> comments) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    /**
     * Constructs a new Lesson object with the given title and content.
     * @param title the title of the lesson
     * @param content the content of the lesson
     */
    public Lesson(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.comments = null;
    }

    /**
     * Constructs a new Lesson object with the given values.
     * @param id the unique identifier for the lesson
     * @param title the title of the lesson
     * @param content the content of the lesson
     * @param comments the comments associated with the lesson
     */
    public Lesson(UUID id, String title, String content, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Returns a string representation of the Lesson object.
     * @return a string representation of the Lesson object
     */
    @Override
    public String toString() {
        return "Lesson [id=" + id + ", title=" + title + ", content=" + content + ", comments=" + comments + "]";
    } 
}
