package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;

import backEnd.Review;
import backEnd.Student;
import backEnd.Teacher;
import backEnd.User;

/**
 * @author Jordan Wood
 */

public class ReviewTest {
    @Test
    public void testConstructor() {
        User reviewer = new Teacher();
        LocalDate date = LocalDate.of(2021, 4, 9);
        int rating = 4;
        String comment = "This was a great course!";
        Review review = new Review(reviewer, date, rating, comment);
        assertEquals(review.getReviewer(), reviewer);
        assertEquals(review.getDate(), date);
        assertEquals(review.getRating(), rating);
        assertEquals(review.getComment(), comment);
        assertNotNull(review.getId());
    }
    
    @Test
    public void testConstructorWithId() {
        UUID id = UUID.randomUUID();
        User reviewer = new Student();
        LocalDate date = LocalDate.of(2021, 4, 10);
        int rating = 3;
        String comment = "The course was okay.";
        Review review = new Review(id, reviewer, date, rating, comment);
        assertEquals(review.getId(), id);
        assertEquals(review.getReviewer(), reviewer);
        assertEquals(review.getDate(), date);
        assertEquals(review.getRating(), rating);
        assertEquals(review.getComment(), comment);
    }
    
    @Test
    public void testDefaultConstructor() {
        Review review = new Review();
        assertEquals(review.getReviewer().getClass(), Student.class);
        assertNotNull(review.getDate());
        assertEquals(review.getRating(), -1);
        assertEquals(review.getComment(), "none");
        assertNotNull(review.getId());
    }
    
    @Test
    public void testToString() {
        UUID id = UUID.randomUUID();
        User reviewer = new Teacher();
        LocalDate date = LocalDate.of(2021, 4, 11);
        int rating = 5;
        String comment = "This course was amazing!";
        Review review = new Review(id, reviewer, date, rating, comment);
        String expected = "Review [id=" + id + ", reviewer=" + reviewer + ", date=" + date + ", rating=" + rating + ", comment="
                + comment + "]";
        assertEquals(review.toString(), expected);
    }
}
