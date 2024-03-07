package backEnd;
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    public ArrayList<User> users;
    public static UserList userList;

    /**
     * default constructor for userlist
     */
    public UserList() {
        users = DataLoader.getUsers();
    }

    /**
     * @return the current instance of user list
     */
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    /**
     * @param userName username of a user
     * search through all users for user matching the username
     * @return the user found
     */
    public User getUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @param id UUID of a user
     * search through all users for user matching the id
     * @return the user found
     */
    public User getUserByUUID(UUID id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @param userName username of a user
     * check if user with the username already exists
     * @return false if user does not exist
     */
    public boolean haveUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param email email of a user
     * check if user with the email already exists
     * @return false if user does not exist
     */
    public boolean haveEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param type the type of users (Admin, Teacher, Student)
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param userName username of the user
     * @param email email of the user
     * @param password password of the user
     * @param currentCourseID current course ID
     * @param currentTopicID current topic ID
     * @param currentLessonID current lesson ID
     * check if the user's username or email exists in database, if not create the user with it's type and add it to the user array list and database
     * @return true on successfully database addition
     */
    public boolean addUser(String type, String firstName, String lastName, String userName, String email, String password,
    UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        if (haveUser(userName) || haveUser(email)) {
            return false;
        }

        if (type.equalsIgnoreCase("admin")) {
            System.out.println("Input Admin");
            users.add(new Admin(firstName, lastName, userName, email, password,
            currentCourseID, currentTopicID, currentLessonID));
        } else if (type.equalsIgnoreCase("teacher")) {
            System.out.println("Input Teacher");
            users.add(new Teacher(firstName, lastName, userName, email, password,
            currentCourseID, currentTopicID, currentLessonID));
        } else if (type.equalsIgnoreCase("student")) {
            System.out.println("Input Student");
            users.add(new Student(firstName, lastName, userName, email, password,
            currentCourseID, currentTopicID, currentLessonID));
        }
        userList.saveUsers();
        return true;
    }

    public void saveUsers() {
        DataWriter.saveUsers();
    }

    /**
     * @param user user attemption to login
     * @param password password entered
     * @return true if user password matches user information
     */
    public boolean authUser(User user, String password) {
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    //public void deleteUserList(UserList userList) {} may remove or edit this

    //public void editUserList(UserList userList) {} may remove or edit this
}
