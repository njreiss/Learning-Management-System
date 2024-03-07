package test;

import backEnd.Comment;
import backEnd.Student;
import backEnd.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Jordan Wood
 */

public class CommentTest extends Comment {
    public static final UUID NIL_UUID = UUID.fromString("a598fb8f-d3f5-4c4c-b230-33e375ed2a92");
    private Comment comment;
    private User user;
    private LocalDate date;
    private String content;
    private ArrayList<Comment> replys;

    @Before
    public void setUp() {
        comment = new Comment();
        String uuidString = "00000000-0000-0000-0000-000000000000";
        user = new Student();
        date = LocalDate.now();
        content = "none";
        replys = new ArrayList<Comment>();
    }

    @After
    public void tearDown() {
        user = null;
        date = null;
        content = null;
        replys = null;
    }

    @Test
    public void testDefaultConstructor() {;
        assertEquals(Comment.NIL_UUID, comment.getId());
        assertEquals("none", comment.getContent());
        assertEquals(LocalDate.now(), comment.getDate());
        assertEquals(new Student(), comment.getUser());
        assertEquals(new ArrayList<Comment>(), comment.getReplys());
    }

    @Test
    public void testParameterizedConstructor() {
        UUID id = UUID.randomUUID();
        User user = new Student();
        LocalDate date = LocalDate.of(2022, 4, 9);
        String content = "This is a comment";
        ArrayList<Comment> replies = new ArrayList<>();
        Comment reply1 = new Comment(new Student(), LocalDate.of(2022, 4, 9), "This is a reply", new ArrayList<>());
        Comment reply2 = new Comment(new Student(), LocalDate.of(2022, 4, 9), "This is another reply", new ArrayList<>());
        replies.add(reply1);
        replies.add(reply2);
        comment = new Comment(id, user, date, content, replies);
        assertEquals(id, comment.getId());
        assertEquals(user, comment.getUser());
        assertEquals(date, comment.getDate());
        assertEquals(content, comment.getContent());
        assertEquals(replies, comment.getReplys());
    }

    @Test
    public void testGetReplyByUUID() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Comment reply1 = new Comment(new Student(), LocalDate.of(2022, 4, 9), "This is a reply", new ArrayList<>());
        Comment reply2 = new Comment(new Student(), LocalDate.of(2022, 4, 9), "This is another reply", new ArrayList<>());
        reply1.setId(id1);
        reply2.setId(id2);
        comment.getReplys().add(reply1);
        comment.getReplys().add(reply2);
        assertEquals(reply1, comment.getReplyByUUID(id1));
        assertEquals(reply2, comment.getReplyByUUID(id2));
        assertNull(comment.getReplyByUUID(UUID.randomUUID()));
    }

    @Test
    public void testToString() {
        UUID id = UUID.randomUUID();
        User user = new Student();
        LocalDate date = LocalDate.of(2022, 4, 9);
        String content = "This is a comment";
        ArrayList<Comment> replies = new ArrayList<>();
        Comment reply1 = new Comment(new Student(), LocalDate.of(2022, 4, 9), "This is a reply", new ArrayList<>());
        Comment reply2 = new Comment(new Student(), LocalDate.of(2022, 4, 9), "This is another reply", new ArrayList<>());
        replies.add(reply1);
        replies.add(reply2);
        comment = new Comment(id, user, date, content, replies);
        String expected = "Comment [id=" + id + ", user=" + user + ", date=" + date + ", content=" + content + ", replys=" + replies + "]";
        assertEquals(expected, comment.toString());
    }
}
