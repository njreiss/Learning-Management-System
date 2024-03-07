package backEnd;
import java.util.ArrayList;

public class dataTester {
    public static void main(String args[])  {  
        System.out.println("Testing DataLoader");

        System.out.println("--------------------------------------------------------------------\n");
        UserList us = UserList.getInstance();
        ArrayList<User> users = us.getUsers();
        printUsers(users);
        users.clear();
        printUsers(users);
        

        // Add a User
        //us.addUser("admin", "bob", "jones", "bj32", "bj@gmail.com", "5892759fshkag");

        UserList us2 = UserList.getInstance();
        //printUsers(us2.getUsers());

        
        CourseList cs = CourseList.getInstance();
        ArrayList<Course> courses = cs.getCourses();
        //printCourses(courses);
        System.out.println("\n \n");
        
        


        //System.out.println(course.getId());
        
        
        //System.out.println(courses.get(0).getTopics());
        
        
        System.out.println("--------------------------------------------------------------------\n");
        //us.addUser("admin", "bob", "jones", "bj32", "bj@gmail.com", "5892759fshkag");
        //printUsers(us.getUsers());
        //us.saveUsers();
        
    }

    public static void printUsers(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i);
            System.out.println("***" + users.get(i));
        }
    }

    public static void printCourses(ArrayList<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("***" + courses.get(i));
        }
    }
}
