package backEnd;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class is responsible for writing the data to JSON files. 
 * It has two methods, one to save the list of users to a 
 * JSON file and one to save the list of courses to a JSON file. 
 * It uses the org.json.simple library to create the JSON objects
 * and arrays that will be written to the files.
@author Caleb Brunson
 */
public class DataWriter extends DataConstants {

    /**
     * Saves the list of users to a JSON file.
     */
    public static void saveUsers() {
        UserList users = UserList.getInstance();
        ArrayList<User> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();
		
		// Creation of Objects in JSON 
		for(int i=0; i< userList.size(); i++) {
			jsonUsers.add(getUserJSON(userList.get(i)));
		}

        // Write the JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a JSONObject from the given User object.
     *
     * @param user the User object to create the JSONObject from
     * @return the JSONObject created from the User object
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getId().toString());
        userDetails.put(USER_TYPE, user.getType());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_USER_NAME, user.getUserName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_CURRENT_COURSE_ID, user.getCurrentCourseID().toString());
        userDetails.put(USER_CURRENT_TOPIC_ID, user.getCurrentTopicID().toString());
        userDetails.put(USER_CURRENT_LESSON_ID, user.getCurrentLessonID().toString());

        return userDetails;
    }

    /**
     * Saves the list of courses to a JSON file.
     */
    public static void saveCourses() {
        CourseList courses = CourseList.getInstance();
        ArrayList<Course> courseList = courses.getCourses();
        JSONArray jsonCourses = new JSONArray();

        // Creation of Objects in JSON 
		for(int i=0; i< courseList.size(); i++) {
			jsonCourses.add(getCourseJSON(courseList.get(i)));
		}

        // Write the JSON file
        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) { 
            file.write(jsonCourses.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a JSONObject from the given Course object.
     *
     * @param course the Course object to create the JSONObject from
     * @return the JSONObject created from the Course object
     */
    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put(COURSE_ID, course.getId().toString());
        courseDetails.put(COURSE_TITLE, course.getTitle());
        courseDetails.put(COURSE_LANGUAGE, course.getLanguage().toString());
        courseDetails.put(COURSE_DESCRIPTION, course.getDescription());
        courseDetails.put(COURSE_TEACHER_ID, course.getTeacher().getId().toString());

        // Students: studentProgress
        JSONArray studentsArray = new JSONArray();
        for (StudentProgress sp : course.getStudentProgresses()) {
            JSONObject studentsDetails = new JSONObject();
            studentsDetails.put(COURSE_STUDENT_ID, sp.getStudent().getId().toString());

            //studentsDetails.put(COURSE_QUIZ_GRADES, sp.getQuizGrades());
            JSONArray gradesArray = new JSONArray();
            for (Grade g : sp.getGrades()) {
                JSONObject gradesDetails = new JSONObject();
                gradesDetails.put(COURSE_ID, g.getId().toString());
                gradesDetails.put(COURSE_QUIZ_ID, g.getQuizID().toString());
                gradesDetails.put(COURSE_GRADE_PERCENTAGE, g.getGradePercentage());

                gradesArray.add(gradesDetails);
            }

            studentsDetails.put(COURSE_GRADES, gradesArray);

            studentsArray.add(studentsDetails);
        }
        courseDetails.put(COURSE_STUDENTS, studentsArray);

        // Topics
        JSONArray topicsArray = new JSONArray();
        for (Topic t : course.getTopics()) {
            JSONObject topicsDetails = new JSONObject();
            topicsDetails.put(COURSE_ID, t.getId().toString());
            topicsDetails.put(COURSE_TITLE, t.getTitle());
            topicsDetails.put(COURSE_DESCRIPTION, t.getDescription());
            // Lessons
            JSONArray lessonsArray = new JSONArray();
            for (Lesson l : t.getLessons()) {
                JSONObject lessonsDetails = new JSONObject();
                lessonsDetails.put(COURSE_ID, l.getId().toString());
                lessonsDetails.put(COURSE_TITLE, l.getTitle());
                lessonsDetails.put(COURSE_CONTENT, l.getContent());

                // Comments
                JSONArray commentsArray = new JSONArray();
                ArrayList<Comment> comments = l.getComments();
                commentRecursionJSON(commentsArray, comments);
                lessonsDetails.put(COURSE_COMMENTS, commentsArray);
                lessonsArray.add(lessonsDetails);
            }
            topicsDetails.put(COURSE_LESSONS, lessonsArray);
            // Quiz
            JSONObject quizDetails = new JSONObject();
            Quiz quiz = t.getQuiz();
            quizDetails.put(COURSE_ID, quiz.getId().toString());
            quizDetails.put(COURSE_TITLE, quiz.getTitle());
            quizDetails.put(COURSE_DESCRIPTION, quiz.getDescription());
            // Quiz Questions
            JSONArray questionsArray = new JSONArray();
            ArrayList<Question> questions = quiz.getQuestions();
            for (Question q : questions) {
                JSONObject questionDetails = new JSONObject();
                questionDetails.put(COURSE_ID, q.getId().toString());
                questionDetails.put(COURSE_QUESTION, q.getQuestion());
                questionDetails.put(COURSE_CHOICES, q.getChoices());
                questionDetails.put(COURSE_CORRECT_ANSWER_INDEX, q.getCorrectAnswerIndex());
                questionsArray.add(questionDetails);
            }
            quizDetails.put(COURSE_QUESTIONS, questionsArray);
            topicsDetails.put(COURSE_QUIZ, quizDetails);
            
            // Topic comments
            JSONArray topicCommentsArray = new JSONArray();
            ArrayList<Comment> topicComments = t.getComments();
            commentRecursionJSON(topicCommentsArray, topicComments);
            topicsDetails.put(COURSE_COMMENTS, topicCommentsArray);

            topicsArray.add(topicsDetails);
        }
        courseDetails.put(COURSE_TOPICS, topicsArray);

        // Comments
        JSONArray courseCommentsArray = new JSONArray();
        ArrayList<Comment> courseComments = course.getComments();
        commentRecursionJSON(courseCommentsArray, courseComments);
        courseDetails.put(COURSE_COMMENTS, courseCommentsArray);

        // Reviews
        JSONArray reviewsArray = new JSONArray();
        for (Review r : course.getReviews()) {
            JSONObject reviewsDetails = new JSONObject();
            reviewsDetails.put(COURSE_ID, r.getId().toString());
            reviewsDetails.put(COURSE_STUDENT_ID, r.getReviewer().getId().toString());
            reviewsDetails.put(COURSE_DATE, r.getDate().toString());
            reviewsDetails.put(COURSE_RATING, r.getRating());
            reviewsDetails.put(COURSE_CONTENT, r.getComment());
            reviewsArray.add(reviewsDetails);
        }
        courseDetails.put(COURSE_REVIEWS, reviewsArray);

        return courseDetails;
    }

    /**
     * Recursively converts a list of Comment objects into a JSONArray of JSONObjects, 
     * where each JSONObject represents a Comment and its details, including any replies it may have.
     * @param commentsArray The JSONArray to which the converted JSONObjects will be added.
     * @param comments The list of Comment objects to be converted.
     */
    public static void commentRecursionJSON(JSONArray commentsArray, ArrayList<Comment> comments) {
        for (Comment c : comments) {
            JSONObject commentsDetails = new JSONObject();
            commentsDetails.put(COURSE_ID, c.getId().toString());
            commentsDetails.put(COURSE_USER_ID, c.getUser().getId().toString());
            commentsDetails.put(COURSE_DATE, c.getDate().toString());
            commentsDetails.put(COURSE_CONTENT, c.getContent());

            JSONArray repliesArray = new JSONArray();
            ArrayList<Comment> replies = c.getReplys();

            if (!replies.isEmpty()) {
                commentRecursionJSON(repliesArray, replies);
                commentsDetails.put(COURSE_REPLYS, repliesArray);
            } else {
                commentsDetails.put(COURSE_REPLYS, new ArrayList<Comment>());
            }

            commentsArray.add(commentsDetails);
        }
    }
}
