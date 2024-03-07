package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.rules.TestRule;

import backEnd.*;

public class LMSFacadeTest {
    LMSFacade lmsFacade = new LMSFacade();

    private static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private   UserList userList= UserList.getInstance(); // users in ex
	private  ArrayList<User> users = userList.getUsers(); // userList in ex

    private Course courseCreated;
    private ArrayList<Lesson> lessonsCreated;
    private Quiz quizCreated;
    private ArrayList<Question> questionsCreated;
    // Booleans to check if limits were reached
    private int quizCount;
    private int topicCount;
    private int lessonCount;
    private int questionCount;

    @Before
     public void setupClass() {
        
        users.add(new Student(NIL_UUID, "bob", "jones", "bj32", "bj@gmail.com", "bjiscool123", 
        NIL_UUID, NIL_UUID, NIL_UUID));
        users.add(new Teacher(NIL_UUID, "jeremy", "lewis", "jlewis", "jlewis@gmail.com", "jlewrocks74", 
        NIL_UUID, NIL_UUID, NIL_UUID));
        userList.users = users;
    //     users.clear();
    //     
    //     users.add(new Student("Loe", "Fmith","lfmith", "fmith@email.com", "fmmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));
    //     users.add(new Student("Soe", "Jmith","sjmith", "jmith@email.com", "sjmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));

         DataWriter.saveUsers();

        // testList.addUser("Student", "Joe", "Smith","jsmith", "smith@email.com", "jsmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        // testList.addUser("Student", "Loe", "Fmith","lfmith", "fmith@email.com", "fmmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        // testList.addUser("Student", "Soe", "Jmith","sjmith", "jmith@email.com", "sjmithpw", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

    }
    @After
    public void TearDownClass() {
        UserList.getInstance().getUsers().clear();
        
        DataWriter.saveUsers();
    }

    @Test
    public void loginSuccessTest() {
        boolean expected = true;
        boolean actually = lmsFacade.login("bj32", "bjiscool123");

        assertEquals(expected, actually);
    }

    @Test
    public void loginFailureTest() {
        boolean expected = false;
        boolean actually = lmsFacade.login("bj32", null);

        assertEquals(expected, actually);
    }

    @Test
    public void signUpSuccessTest() {
        boolean expected = true;
        boolean actually = lmsFacade.signUp("Student", "John", "Jones", "JBJones", "JohnBones@email.com", "GOATOFUFC");

        assertEquals(expected, actually);
    }

    public void reachedQuizLimitSucessTest() {
        
        lmsFacade.createQuiz("TempQuiz", "This is a temperary quiz");

        boolean expected = true;
        boolean actually = lmsFacade.reachedQuestionLimit();

        assertEquals(expected, actually);
    }

    public void reachedQuizLimitfailTest() {

        LMSFacade emptyLMS = new LMSFacade();

        boolean expected = false;
        boolean actually = emptyLMS.reachedQuestionLimit();

        assertEquals(expected, actually);
    }

    public void reachedTopicLimitFalseTest() {
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");

        boolean expected = false;
        boolean actually = lmsFacade.reachedTopicLimit();

        assertEquals(expected, actually);
    }
    public void reachedTopicLimitTrueTest() {
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");
        lmsFacade.createTopic("TempTopic", "This is a temperary topic");


        boolean expected = true;
        boolean actually = lmsFacade.reachedTopicLimit();

        assertEquals(expected, actually);
    }

    
}
