package backEnd;
import java.util.UUID;

public abstract class User {
    private UUID id;
    private String type;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private UUID currentCourseID;
    private UUID currentTopicID;
    private UUID currentLessonID;

    /**
     * @param type the type of user (Admin, Teacher, Student)
     * @param firstName first name of user
     * @param lastName last name of user
     * @param userName username of user
     * @param email email of user
     * @param password password of user
     * @param currentCourseID current course ID
     * @param currentTopicID current topic ID
     * @param currentLessonID current lesson ID
     */
    public User(String type, String firstName, String lastName, String userName, String email, 
    String password, UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.currentCourseID = currentCourseID;
        this.currentTopicID = currentTopicID;
        this.currentLessonID = currentLessonID;
    }

    /**
     * @param id randomly generally ID
     * @param type the type of user (Admin, Teacher, Student)
     * @param firstName first name of user
     * @param lastName last name of user
     * @param userName username of user
     * @param email email of user
     * @param password password of user
     * @param currentCourseID current course ID
     * @param currentTopicID current topic ID
     * @param currentLessonID current lesson ID
     */
    public User(UUID id, String type, String firstName, String lastName, String userName, String email, 
    String password, UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        this.id = id;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.currentCourseID = currentCourseID;
        this.currentTopicID = currentTopicID;
        this.currentLessonID = currentLessonID;
    }

    //GETTERS AND SETTERS
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getCurrentCourseID() {
        return currentCourseID;
    }

    public void setCurrentCourseID(UUID currentCourseID) {
        this.currentCourseID = currentCourseID;
    }

    public UUID getCurrentTopicID() {
        return currentTopicID;
    }

    public void setCurrentTopicID(UUID currentTopicID) {
        this.currentTopicID = currentTopicID;
    }

    public UUID getCurrentLessonID() {
        return currentLessonID;
    }

    public void setCurrentLessonID(UUID currentLessonID) {
        this.currentLessonID = currentLessonID;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", type=" + type + ", firstName=" + firstName + ", lastName=" + lastName
                + ", userName=" + userName + ", email=" + email + ", password=" + password + ", currentCourseID="
                + currentCourseID + ", currentTopicID=" + currentTopicID + ", currentLessonID=" + currentLessonID + "]";
    }

}
