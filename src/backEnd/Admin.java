package backEnd;
import java.util.UUID;

/**
 * The Admin class represents an Admin of the LMS
 */
public class Admin extends User {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    
    /**
     * Creates a new Admin object with appropriate values for all fields.
     */
    public Admin() {
        super(NIL_UUID, "admin", "none", "none", "none", "none", "none", NIL_UUID, NIL_UUID, NIL_UUID);
    }

    /**
     * Creates a new Admin object with the specified field values.
     * 
     * @param id the unique ID of the admin
     * @param firstName the first name of the admin
     * @param lastName the last name of the admin
     * @param userName the username of the admin
     * @param email the email address of the admin
     * @param password the password of the admin
     * @param currentCourseID the unique ID of the current course the admin is viewing
     * @param currentTopicID the unique ID of the current topic the admin is viewing
     * @param currentLessonID the unique ID of the current lesson the admin is viewing
     */
    public Admin(UUID id, String firstName, String lastName, String userName, 
    String email, String password, UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        super(id, "admin", firstName, lastName, userName, email, password, currentCourseID, currentTopicID, currentLessonID);
    }

    /**
     * Creates a new Admin object with the specified field values and an ID.
     * 
     * @param firstName the first name of the admin
     * @param lastName the last name of the admin
     * @param userName the username of the admin
     * @param email the email address of the admin
     * @param password the password of the admin
     * @param currentCourseID the unique ID of the current course the admin is viewing
     * @param currentTopicID the unique ID of the current topic the admin is viewing
     * @param currentLessonID the unique ID of the current lesson the admin is viewing
     */
    public Admin(String firstName, String lastName, String userName, 
    String email, String password, UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        super("admin", firstName, lastName, userName, email, password, currentCourseID, currentTopicID, currentLessonID);
    }
}
