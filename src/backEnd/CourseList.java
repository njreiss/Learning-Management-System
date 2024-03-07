package backEnd;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The CourseList class represents a list of courses.
 * It provides methods to access and modify courses in the list.
 */
public class CourseList {
    private ArrayList<Course> courses;
    private static CourseList courseList;
    
    /**
     * Constructs a new CourseList object and initializes it with courses from DataLoader.
     */
    private CourseList() {
        courses = DataLoader.getCourses();
    }

    /**
     * Returns the singleton instance of CourseList.
     * @return the singleton instance of CourseList
     */
    public static CourseList getInstance() {
        if(courseList == null) {
            courseList = new CourseList();
        }
        return courseList;
    }

    /**
     * Returns the Course object with the given UUID.
     * @param id the UUID of the course to be returned
     * @return the Course object with the given UUID, or null if not found
     */
    public Course getCourseByUUID(UUID id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

     /**
     * Returns the list of all courses.
     * @return the list of all courses
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Checks whether a course with the given UUID exists in the list.
     * @param id the UUID of the course to be checked
     * @return true if a course with the given UUID exists, false otherwise
     */
    public boolean haveCourse(UUID id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return true;
            }
         }
        return false;
    }

    /**
     * Saves the courses in the list to the data file.
     */
    public void saveCourses() {
        DataWriter.saveCourses();
    }

    /**
     * Adds a new course with the given parameters to the list.
     * @param title the title of the course
     * @param language the language of the course
     * @param description the description of the course
     * @param teacher the teacher of the course
     * @param topics the topics covered in the course
     * @param reviews the reviews of the course
     * @param comments the comments on the course
     * @param studentProgresses the progress of each student in the course
     * @return true if the course is added successfully, false otherwise
     */
    public boolean addCourse(String title, Language language, String description, 
    Teacher teacher, ArrayList<Topic> topics, ArrayList<Review> reviews, 
    ArrayList<Comment> comments, ArrayList<StudentProgress> studentProgresses) {
        // create instance of a course with params and UUID.rando()
        Course findCourse = new Course(title, language, description, teacher, topics, reviews, comments, studentProgresses);
        courses.add(findCourse);
        
        if(findCourse.getId() != null) {
            courseList.saveCourses();
            return true;
        }
        return false;
    }

    /**
     * Adds the given course to the list.
     * @param course the course to be added
     * @return true if the course is added successfully, false otherwise
     */
    public boolean addCourse(Course course) {
        courses.add(course);

        if(course.getId() != null) {
            courseList.saveCourses();
            return true;
        }
        return false;
    }
    


    
}