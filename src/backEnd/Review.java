package backEnd;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a review object that holds information about a review for a course.
 */
public class Review {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private UUID id;
    private User reviewer;
    private LocalDate date;
    private int rating;
    private String comment;

    /**
     * Constructs a new review object with default values.
     */
    public Review() {
        this.id = NIL_UUID;
        this.reviewer = new Student();
        this.date = LocalDate.now();
        this.rating = -1;
        this.comment = "none";
    }

    /**
     * Constructs a new review object with specified values.
     * @param reviewer the user who wrote the review
     * @param date the date the review was written
     * @param rating the rating given in the review
     * @param comment the comment written in the review
     */
    public Review(User reviewer, LocalDate date, int rating, String comment) {
        this.id = UUID.randomUUID();
        this.reviewer = reviewer;
        this.date = date;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Constructs a new review object with specified values.
     * @param id the unique identifier of the review
     * @param reviewer the user who wrote the review
     * @param date the date the review was written
     * @param rating the rating given in the review
     * @param comment the comment written in the review
     */
    public Review(UUID id, User reviewer, LocalDate date, int rating, String comment) {
        this.id = id;
        this.reviewer = reviewer;
        this.date = date;
        this.rating = rating;
        this.comment = comment;
    }

    //GETTERS AND SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns a string representation of the Review object.
     * @return A string containing the id, reviewer name, date, rating, 
     * and comment of the Review object.
     */
    @Override
    public String toString() {
        return "Review [id=" + id + ", reviewer=" + reviewer + ", date=" + date + ", rating=" + rating + ", comment="
                + comment + "]";
    }
}
