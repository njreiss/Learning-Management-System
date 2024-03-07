package backEnd;
import java.util.ArrayList;
import java.util.UUID;

public class Topic {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private UUID id;
    private String title;
    private String description;
    private Quiz quiz;
    private ArrayList<Lesson> lessons;
    private ArrayList<Comment> comments;

    /**
     * Default constructor for creating a topic
     */
    public Topic() {
        this.id = NIL_UUID;
        this.title = "none";
        this.description = "none";
        this.quiz = new Quiz();
        this.lessons = new ArrayList<Lesson>();
        this.comments = new ArrayList<Comment>();
    }

    /**
     * @param title the title of the topic
     * @param description the description of the topic
     * @param quiz the quiz of the topic
     * @param lessons a list of lessons related to the topic
     * @param comments a list of comments
     * Creates a topic with parameters passed for all variables without UUID
     */
    public Topic(String title, String description, Quiz quiz, 
        ArrayList<Lesson> lessons, ArrayList<Comment> comments) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.quiz = quiz;
        this.lessons = lessons;
        this.comments = comments;
    }

    /**
     * @param id the id of the topic
     * @param title the title of the topic
     * @param description the description of the topic
     * @param quiz the quiz of the topic
     * @param lessons a list of lessons related to the topic
     * @param comments a list of comments
     * Creates a topic with parameters passed variables with UUID
     */
    public Topic(UUID id, String title, String description, Quiz quiz, 
        ArrayList<Lesson> lessons, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quiz = quiz;
        this.lessons = lessons;
        this.comments = comments;
    }

    /**
     * @param id the lessonID of a lesson
     * search the array list of lessons for a specific lesson by UUID
     * @return the lesson is returned if it exist in the list
     */
    public Lesson getLessonByUUID(UUID id) {
        for (Lesson lesson : lessons) {
            if (lesson.getId().equals(id)) {
                return lesson;
            }
        }
        return null;
    }

    /**
     * @param id the commentID of a comment
     * search the array list of comments for a specific comment by UUID
     * @return the comment is returned if it exist in the list
     */
    public Comment getCommentByUUID(UUID id) {
        for (Comment comment : comments) {
            if (comment.getId().equals(id)) {
                return comment;
            }
        }
        return null;
    }
    
    /**
     * @param title the title of a lesson
     * @param content the content of a lesson
     * after creating the new lesson it is added to the list of lessons for the course
     * @return the lesson is returned from this function
     */
    public Lesson createLesson(String title, String content) {
        Lesson newLesson = new Lesson(title, content);
        lessons.add(newLesson);
        return newLesson;
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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Topic [id=" + id + ", title=" + title + ", description=" + description + ", quiz=" + quiz + ", lessons="
                + lessons + ", comments=" + comments + "]";
    }

}