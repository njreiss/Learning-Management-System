package backEnd;
//import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    //private ArrayList<StudentProgress> coursesEnrolled;
    //private ArrayList<StudentProgress> coursesCompleted;

    /**
     * Constructor that calls super to create student with UUID
     */
    public Student() {
        super(NIL_UUID, "student", "none", "none", "none", "none", "none", NIL_UUID, NIL_UUID, NIL_UUID);
    }

    /**
     * 
     * @param id randomly generated UUID for the student
     * @param firstName first name of the student
     * @param lastName last name of the student
     * @param userName username of the student
     * @param email email of the student
     * @param password password of the student
     * @param currentCourseID courseID of the course student is taking
     * @param currentTopicID topicID of the topic student is taking
     * @param currentLessonID lessonID of the lesson student is taking
     * constructor that calls super with UUID passed
     */
    public Student(UUID id, String firstName, String lastName, String userName, 
    String email, String password, UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        super(id, "student", firstName, lastName, userName, email, password, currentCourseID, currentTopicID, currentLessonID);
    }

    /**
     * 
     * @param firstName first name of the student
     * @param lastName last name of the student
     * @param userName username of the student
     * @param email email of the student
     * @param password password of the student
     * @param currentCourseID courseID of the course student is taking
     * @param currentTopicID topicID of the topic student is taking
     * @param currentLessonID lessonID of the lesson student is taking
     * constructor that calls super without UUID passed
     */
    public Student(String firstName, String lastName, String userName, 
    String email, String password, UUID currentCourseID, UUID currentTopicID, UUID currentLessonID) {
        super("student", firstName, lastName, userName, email, password, currentCourseID, currentTopicID, currentLessonID);
    }
}
