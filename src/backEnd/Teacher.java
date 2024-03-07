package backEnd;
//import java.util.ArrayList;
import java.util.UUID;

public class Teacher extends User {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    //private ArrayList<Course> coursesCreated;

    /**
     * Constructor that calls super to create teacher with UUID
     */
    public Teacher() {
        super(NIL_UUID, "teacher", "none", "none", "none", "none", "none", NIL_UUID, NIL_UUID, NIL_UUID);
    }

    /**
     * @param id randomly generated UUID for the teacher
     * @param firstName first name of the teacher
     * @param lastName last name of the teacher
     * @param userName username of the teacher
     * @param email email of the teacher
     * @param password password of the teacher
     * @param currentCourseID courseID of the course teacher created
     * @param currentTopicID topicID of the topic teacher created
     * @param currentLessonID lessonID of the lesson teacher created
     * constructor that calls super with UUID passed
     */
    public Teacher(UUID id, String firstName, String lastName, String userName, 
    String email, String password, UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        super(id, "teacher", firstName, lastName, userName, email, password, currentCourseID, currentTopicID, currentLessonID);
    }

    /**
     * @param firstName first name of the teacher
     * @param lastName last name of the teacher
     * @param userName username of the teacher
     * @param email email of the teacher
     * @param password password of the teacher
     * @param currentCourseID courseID of the course teacher created
     * @param currentTopicID topicID of the topic teacher created
     * @param currentLessonID lessonID of the lesson teacher created
     * constructor that calls super without UUID passed
     */
    public Teacher(String firstName, String lastName, String userName, 
    String email, String password, UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        super("teacher", firstName, lastName, userName, email, password, currentCourseID, currentTopicID, currentLessonID);
    }

}
