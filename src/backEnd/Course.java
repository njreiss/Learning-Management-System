package backEnd;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The Course class represents a course in the system.
 */
public class Course {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private UUID id;
    private String title;
    private Language language;
    private String description;
    private Teacher teacher;
    private ArrayList<Topic> topics;
    private ArrayList<Review> reviews;
    private ArrayList<Comment> comments;
    private ArrayList<StudentProgress> studentProgresses;

    /**
     * Constructs a new Course object with default field values.
     */
    public Course() {
        this.id = NIL_UUID;
        this.title = "none";
        this.language = Language.NONE;
        this.description = "none";
        this.teacher = new Teacher();
        this.topics = new ArrayList<Topic>();
        this.reviews = new ArrayList<Review>();
        this.comments = new ArrayList<Comment>();
        this.studentProgresses = new ArrayList<StudentProgress>();
    }

    /**
     * Constructs a new course object with the given values.
     * @param title the title of the course.
     * @param language the language used in the course.
     * @param description a description of the course.
     * @param teacher the teacher who teaches the course.
     * @param topics the list of topics covered in the course.
     * @param reviews the list of reviews for the course.
     * @param comments the list of comments for the course.
     * @param studentProgresses the list of student progresses for the course.
     */
    public Course(String title, Language language, String description, 
        Teacher teacher, ArrayList<Topic> topics, ArrayList<Review> reviews, 
        ArrayList<Comment> comments, ArrayList<StudentProgress> studentProgresses) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.language = language;
        this.description = description;
        this.teacher = teacher;
        this.topics = topics;
        this.reviews = reviews;
        this.comments = comments;
        this.studentProgresses = studentProgresses;
    }

    /**
     * Constructs a new course object with the given values.
     * @param id a unique identifier for the course.
     * @param title the title of the course.
     * @param language the language used in the course.
     * @param description a description of the course.
     * @param teacher the teacher who teaches the course.
     * @param topics the list of topics covered in the course.
     * @param reviews the list of reviews for the course.
     * @param comments the list of comments for the course.
     * @param studentProgresses the list of student progresses for the course.
     */
    public Course(UUID id, String title, Language language, String description, 
        Teacher teacher, ArrayList<Topic> topics, ArrayList<Review> reviews, 
        ArrayList<Comment> comments, ArrayList<StudentProgress> studentProgresses) {
            this.id = id;
            this.title = title;
            this.language = language;
            this.description = description;
            this.teacher = teacher;
            this.topics = topics;
            this.reviews = reviews;
            this.comments = comments;
            this.studentProgresses = studentProgresses;
    }

    /**
     * Get the Topic object from the topics list with the specified UUID.
    * @param id the UUID of the Topic to retrieve.
    * @return the Topic object with the specified UUID, or null if not found.
    */
    public Topic getTopicByUUID(UUID id) {
        for (Topic topic : topics) {
            if (topic.getId().equals(id)) {
                return topic;
            }
        }
        return null;
    }

    /**
     * Get the Review object from the reviews list with the specified UUID.
     * 
     * @param id the UUID of the Review to retrieve.
     * @return the Review object with the specified UUID, or null if not found.
     */
    public Review getReviewByUUID(UUID id) {
        for (Review review : reviews) {
            if (review.getId().equals(id)) {
                return review;
            }
        }
        return null;
    }

    /**
     * Returns the Comment with the given UUID, or null if no Comment with that UUID exists in this Course's list of Comments.
     * @param id the UUID of the Comment to retrieve
     * @return the Comment with the given UUID, or null if no Comment with that UUID exists in this Course's list of Comments 
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
     * Returns the StudentProgress object for the student with the given UUID, 
     * or null if no StudentProgress object exists for that student in this Course's list of student progresses.
     * @param id the UUID of the student for which to retrieve the StudentProgress object
     * @return the StudentProgress object for the student with the given UUID, 
     * or null if no StudentProgress object exists for that student in this Course's list of student progresses
     */
    public StudentProgress getStudentProgressByStudentUUID(UUID id) {
        for (StudentProgress sp : studentProgresses) {
            if (sp.getStudent().getId().equals(id)) {
                return sp;
            }
        }
        return null;
    }

    /**
     * 
     * @param title the title of the topic
     * @param description the description of the topic
     * @param quiz the quiz of the topic
     * @param lessons a list of lessons related to the topic
     * after creating the new topic it is added to the list of topics for the course
     * @return the topic is returned from this function
     */
    public Topic createTopic(String title, String description, Quiz quiz, 
    ArrayList<Lesson> lessons, ArrayList<Comment> comments) {
        Topic newTopic = new Topic(title, description, quiz, lessons, comments);
        topics.add(newTopic);
        return newTopic;
        
    }

    // GETTERS AND SETTERS
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<StudentProgress> getStudentProgresses() {
        return studentProgresses;
    }

    public void setStudentProgresses(ArrayList<StudentProgress> studentProgresses) {
        this.studentProgresses = studentProgresses;
    }

    /**
     * Returns a String representation of the Course object.
     * @return a String containing the values of the id, title, language, description, 
     * teacher, topics, reviews, comments, and studentProgress fields
     */
    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + ", language=" + language + ", description=" + description
                + ", teacher=" + teacher + ", topics=" + topics + ", reviews=" + reviews + ", comments=" + comments
                + ", studentProgresses=" + studentProgresses + "]";
    }

}
