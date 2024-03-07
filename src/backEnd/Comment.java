package backEnd;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
The Comment class represents a comment object with a 
unique ID, user, date, content, and an ArrayList of replies.
*/
public class Comment {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private UUID id;
    private User user;
    private LocalDate date;
    private String content;
    private ArrayList<Comment> replys;

    /**
     * Creates a new Comment object with appropriate values for all fields.
     */
    public Comment() {
        this.id = NIL_UUID;
        this.user = new Student(); // default User
        this.date = LocalDate.now();
        this.content = "none";
        this.replys = new ArrayList<Comment>();
    }
    
    /**
     * Constructor for creating a Comment object with a specified user, date, content, and list of replies
     * @param user The user who wrote the comment
     * @param date The date the comment was created
     * @param content The content of the comment
     * @param replys The list of replies to the comment
     */
    public Comment(User user, LocalDate date, String content, ArrayList<Comment> replys) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.date = date;
        this.content = content;
        this.replys = replys;
    }

    /**
     * Constructor for creating a Comment object with a specified ID, user, date, content, and list of replies
     * @param id The unique ID of the comment
     * @param user The user who wrote the comment
     * @param date The date the comment was created
     * @param content The content of the comment
     * @param replys The list of replies to the comment
     */
    public Comment(UUID id, User user, LocalDate date, String content, ArrayList<Comment> replys) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.content = content;
        this.replys = replys;
    }

    /**
     * Finds a reply comment in the list of replies by its unique ID
     * @param id The unique ID of the reply comment to find
     * @return The Comment object with the specified ID, or null if not found
     */
    public Comment getReplyByUUID(UUID id) {
        for (Comment reply : replys) {
            if (reply.getId().equals(id)) {
                return reply;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Comment> getReplys() {
        return replys;
    }

    public void setReplys(ArrayList<Comment> replys) {
        this.replys = replys;
    }

    /**
     * Returns a String representation of the Comment object.
     * @return a String containing the values of the id, user, date, content, and replys fields
    */
    @Override
    public String toString() {
        return "Comment [id=" + id + ", user=" + user + ", date=" + date + ", content=" + content + ", replys=" + replys
                + "]";
    }
}
