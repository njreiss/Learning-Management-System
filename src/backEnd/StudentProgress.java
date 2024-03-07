package backEnd;
import java.util.ArrayList;
import java.util.UUID;

public class StudentProgress {
    private User student;
    private static ArrayList<Grade> grades;

    /**
     * default constructor for student progress
     */
    public StudentProgress() {
        this.student = new Student();
        this.grades = new ArrayList<Grade>();
    }

    /**
     * 
     * @param student currect student that is logged in
     * @param grades array list of student grades
     * constructor that sets current student and their grades to the progress
     */
    public StudentProgress(User student, ArrayList<Grade> grades) {
        this.student = student;
        this.grades = grades;
    }

    //GETTERS AND SETTERS
    public User getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    public static ArrayList<Grade> getGrades() {
        return grades;
    }

    public Grade getGradeByQuizUUID(UUID id) {
        for (Grade g : grades) {
            if (g.getQuizID().equals(id)) {
                return g;
            }
        }
        return null;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "StudentProgress [student=" + student + ", grades=" + grades + "]";
    }

    //OTHER METHODS
    public void createCertificate() {
        
    }
}
