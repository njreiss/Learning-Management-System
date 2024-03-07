package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import backEnd.DataWriter;
import backEnd.Student;
import backEnd.Teacher;
import backEnd.User;
import backEnd.UserList;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Jordan Wood
 */

public class UserListTest {

    private static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    // static UserList testList = new UserList();
     private   UserList userList= UserList.getInstance(); // users in ex
	 private  ArrayList<User> users = userList.getUsers(); // userList in ex


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

    
    //list.addUser( );

    
    @Test
    public void getUserTest() {
        // expected variable, actualy variable, compare
        User newUser = new Student("Danny", "Phantom","dphan", "phantom@email.com", "dphan", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        users.add(newUser);
        DataWriter.saveUsers();
        // // testList.addUser("Student", "Danny", "Phantom","dphan", "phantom@email.com", "dphan", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        User expected = newUser;
        User actually = userList.getUser("dphan");        

        assertEquals(expected, actually);
        // assertEquals(expected, actually);

     }

     @Test
     public void getUsersByUUIDTest() {
        UUID id = UUID.randomUUID();
        User newUser = new Student(id, "Danny", "Phantom","dphan", "phantom@email.com", "dphan", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()) ;
        users.add(newUser);
        DataWriter.saveUsers();
        // // testList.addUser("Student", "Danny", "Phantom","dphan", "phantom@email.com", "dphan", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        User expected = newUser;
        User actually = userList.getUserByUUID(id);        

        assertEquals(expected, actually);
     }

     @Test
     public void getUsersTest() {
        ArrayList<User> expected = users;
        ArrayList<User> actually = userList.getUsers();        

        assertEquals(expected, actually);
     }

     @Test
     public void haveUserTest() {
        boolean expected = false;
        boolean actually = userList.haveUser("dphan");        

        assertEquals(expected, actually);
     }

     @Test
     public void haveEmailTest() {
        boolean expected = false;
        boolean actually = userList.haveEmail("phantom@email.com");        

        assertEquals(expected, actually);
     }

     @Test
     public void addUserTest() {
        boolean expected = true;
        boolean actually = userList.addUser("Student", "Abdullah", "Bueno", "abueno", "bestemail@ever.com", "12345", NIL_UUID, NIL_UUID, NIL_UUID);       

        assertEquals(expected, actually);
     }
    // @Test 
    // public void getUsersFull() {
    //     // User newUser1 = new Student("Danny", "Phantom","dphan", "phantom@email.com", "dphan", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()) ;
    //     // User newUser2 = new Student("Banny", "Fantom","bfan", "fantom@email.com", "bfan", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()) ;
 
    //     // ArrayList<User> arrayOfUsers = new ArrayList<User>();
    //     // arrayOfUsers.add(newUser1);
    //     // arrayOfUsers.add(newUser2);

        

    // }
    
}
