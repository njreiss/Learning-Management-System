package backEnd;

/**
 * This abstract class contains constants used for JSON file names and property keys.
 */
public abstract class DataConstants {
    // User JSON file
    protected static final String USER_FILE_NAME = "json/users.json";
    protected static final String USER_ID = "id";
    protected static final String USER_TYPE = "type";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_USER_NAME = "username";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_DATE_OF_BIRTH = "dateOfBirth";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_CURRENT_COURSE_ID = "currentCourseID";
    protected static final String USER_CURRENT_TOPIC_ID = "currentTopicID";
    protected static final String USER_CURRENT_LESSON_ID = "currentLessonID";

    // Course JSON file
    protected static final String COURSE_FILE_NAME = "json/courses.json";
    protected static final String COURSE_ID = "id";
    protected static final String COURSE_TITLE = "title";
    protected static final String COURSE_LANGUAGE = "language";
    protected static final String COURSE_DESCRIPTION = "description";
    protected static final String COURSE_TEACHER_ID = "teacherID";
    protected static final String COURSE_STUDENTS = "students";
    protected static final String COURSE_STUDENT_ID = "studentID";
    protected static final String COURSE_GRADES = "grades";
    protected static final String COURSE_QUIZ_ID = "quizID";
    protected static final String COURSE_GRADE_PERCENTAGE = "gradePercentage";
    protected static final String COURSE_TOPICS = "topics";
    protected static final String COURSE_LESSONS = "lessons";
    protected static final String COURSE_CONTENT = "content";
    protected static final String COURSE_COMMENTS = "comments";
    protected static final String COURSE_USER_ID = "userID";
    protected static final String COURSE_DATE = "date";
    protected static final String COURSE_REPLYS = "replys";
    protected static final String COURSE_QUIZ = "quiz";
    protected static final String COURSE_QUESTIONS = "questions";
    protected static final String COURSE_QUESTION = "question";
    protected static final String COURSE_CHOICES = "choices";
    protected static final String COURSE_CORRECT_ANSWER_INDEX = "correctAnswerIndex";
    protected static final String COURSE_REVIEWS = "reviews";
    protected static final String COURSE_RATING = "rating";
}
