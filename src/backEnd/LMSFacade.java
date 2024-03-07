package backEnd;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;  
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;  

public class LMSFacade {
    public static final UUID NIL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    public static final int QUIZ_LIMIT = 1;
    public static final int TOPIC_LIMIT = 15; 
    public static final int LESSON_LIMIT = 15;
    public static final int QUESTION_LIMIT = 15;
    JFrame frame1;
    private UserList userList;
    private CourseList courseList;
    private User user; // the current user
    // Objects for creating a course
    private Course courseCreated;
    private ArrayList<Lesson> lessonsCreated;
    private Quiz quizCreated;
    private ArrayList<Question> questionsCreated;
    // Booleans to check if limits were reached
    private int quizCount;
    private int topicCount;
    private int lessonCount;
    private int questionCount;

    /**
     * default constructor of the facade
     */
    public LMSFacade() {
        this.userList =  UserList.getInstance();
        this.courseList = CourseList.getInstance();
        this.user = null;
        this.courseCreated = new Course(); //new Course();
        this.lessonsCreated = new ArrayList<Lesson>();
        this.quizCreated = null;
        this.questionsCreated = new ArrayList<Question>();
        this.quizCount = 0;
        this.topicCount = 0;
        this.lessonCount = 0;
        this.questionCount = 0;
    }

    
    /** 
     * @return current user list
     */
    public UserList getUserList() {
        return userList;
    }

    /**
     * @return current course list
     */
    public CourseList getCourseList() {
        return courseList;
    }

    public HashMap<String, StudentProgress> getCompletedCourses(UUID studentID) {
        HashMap<String, StudentProgress> completedCourses = new HashMap<String, StudentProgress>();
        if (user.getType().equals("student")) {
            ArrayList<Course> courses = courseList.getCourses();
            for (Course course : courses) {
                if (!(course.getStudentProgressByStudentUUID(studentID) == null)) {
                    StudentProgress sp = course.getStudentProgressByStudentUUID(studentID);
                    completedCourses.put(course.getTitle(), sp);
                }
            }
        }
        return completedCourses;
    }

    /**
     * @return current user logged in
     */
    public User getUser() {
        return user;
    }

    /**
     * @return check if current quiz ammount has reached the quiz limit
     */
    public boolean reachedQuizLimit() {
        return quizCount == QUIZ_LIMIT;
    }

    /**
     * @return check if topic ammount has reached the topic limit
     */
    public boolean reachedTopicLimit() {
        return topicCount == TOPIC_LIMIT;
    }

    /**
     * @return check if lesson ammount has reached the lesson limit
     */
    public boolean reachedLessonLimit() {
        return lessonCount == LESSON_LIMIT;
    }

    /**
     * @return check if question ammount has reached the question limit
     */
    public boolean reachedQuestionLimit() {
        return questionCount == QUESTION_LIMIT;
    }

    /**
     * @param username username of user
     * @param password password of user
     * @return true if the user entered correct credentials that matches user account
     */
    public boolean login(String username, String password) {
        User loggedInUser = userList.getUser(username);
        if (userList.authUser(loggedInUser, password)) {
            this.user = loggedInUser;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param type the type of user (Admin, Teacher, Student)
     * @param firstName first name of a user
     * @param lastName last name of a user
     * @param username username of a user
     * @param email email of a user
     * @param password password of a user
     * @return true if the user was successfully created
     */
    public boolean signUp(String type, String firstName, String lastName, String username, String email, String password) {
        if(userList.addUser(type, firstName, lastName, username, email, password, NIL_UUID, NIL_UUID, NIL_UUID)) {
            // User successfully added to db
            this.user = userList.getUser(username);
            return true;
        } else {
            return false;
        }
    }

    /**
     * signout the user (set current user to null)
     */
    public void signOut() {
        this.user = null;
    }

    // COURSE CREATION, EDITING, DELETION
    public void printLesson(String fileName, String content) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(content);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printCourseCertificate(String fileName, String courseName, String studentName, double grade) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("===============================================");
            printWriter.println("CERTIFICATE OF COMPLETITION");
            printWriter.println("Course: " + courseName);
            printWriter.println("Student: " + studentName);
            printWriter.println("Final Grade: " + grade);
            printWriter.println("This certificate was printed on: " + LocalDate.now());
            printWriter.println("===============================================");
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * print the course that was created
     */
    public void printCourseCreated() {
        System.out.println(courseCreated);
    }

    /**
     * @param title title of a course
     * @param language programming language of a course
     * @param description description of a course
     * create a course which will set input to current course being created
     */
    public void createCourse(String title, Language language, String description) {
        courseCreated.setId(UUID.randomUUID());
        courseCreated.setTitle(title);
        courseCreated.setLanguage(language);
        courseCreated.setDescription(description);
        courseCreated.setTeacher((Teacher)user);
        
        if (user == null) {
            JOptionPane.showMessageDialog(frame1,"User is not logged in.","Alert",JOptionPane.WARNING_MESSAGE);
        } else if (courseCreated.getTopics().isEmpty()) {
            JOptionPane.showMessageDialog(frame1,"Topics are incomplete.","Alert",JOptionPane.WARNING_MESSAGE);
        } else {
            if (courseList.addCourse(courseCreated)) {
            } else {
                JOptionPane.showMessageDialog(frame1,"Failed to add course.","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void deleteCourse(Course course) {}

    /**
     * @param title the title of a topic
     * @param description the description of a topic
     * create a topic which will set input to current topic being created
     */
    public void createTopic(String title, String description) {
        Topic topic = new Topic();
        topic.setId(UUID.randomUUID());
        topic.setTitle(title);
        topic.setDescription(description);
        topic.setLessons(lessonsCreated);
        topic.setQuiz(quizCreated);

        if (lessonsCreated.isEmpty()) {
            JOptionPane.showMessageDialog(frame1,"Lessons are incomplete.","Alert",JOptionPane.WARNING_MESSAGE);
        } else if (quizCreated == null) {
            JOptionPane.showMessageDialog(frame1,"Quiz is incomplete.","Alert",JOptionPane.WARNING_MESSAGE);
        } else {
            courseCreated.getTopics().add(topic);
            topicCount++;
            // Reset lessons and questions array lists for the next topic
            lessonsCreated = new ArrayList<Lesson>();
            questionsCreated = new ArrayList<Question>();
            // Reset counts except for topic count
            lessonCount = 0;
            quizCount = 0;
            questionCount = 0;
        }
    }

    public void deleteTopic(Course course, Topic topic) {
        System.out.println("Delete: " + topic.getTitle());
        ArrayList<Topic> topics = course.getTopics();
        topics.remove(topic);
        courseList.saveCourses();
    }

    public void editTopic(Topic topic, String title, String description) {
        System.out.println("Edit: " + topic.getTitle());
        topic.setTitle(title);
        topic.setDescription(description);
        courseList.saveCourses();
    }


    /**
     * @param title the title of a lesson
     * @param content the content of a lesson
     * create a lesson which will set input to current lesson being created
     */
    public void createLesson(String title, String content) {
        Lesson lesson = new Lesson();
        lesson.setId(UUID.randomUUID());
        lesson.setTitle(title);
        lesson.setContent(content);

        lessonsCreated.add(lesson);
        lessonCount++;
    }

    public void deleteLesson(Topic topic, Lesson lesson) {
        ArrayList<Lesson> lessons = topic.getLessons();
        lessons.remove(lesson);
        courseList.saveCourses();
    }

    public void editLesson(Lesson lesson, String title, String content) {
        lesson.setTitle(title);
        lesson.setContent(content);
        courseList.saveCourses();
    }


    /**
     * @param title the title of a quiz
     * @param description the description of a quiz
     * create a quiz which will set input to current quiz being created
     */
    public void createQuiz(String title, String description) {
        quizCreated = new Quiz();
        quizCreated.setId(UUID.randomUUID());
        quizCreated.setTitle(title);
        quizCreated.setDescription(description);

        if (questionsCreated.isEmpty()) {
            JOptionPane.showMessageDialog(frame1,"Questions are incomplete.","Alert",JOptionPane.WARNING_MESSAGE);
        } else {
            quizCreated.setQuestions(questionsCreated);
            quizCount++;
        }
    }

    public void deleteQuiz(Topic topic, Quiz quiz) {
        topic.setQuiz(new Quiz());
        courseList.saveCourses();
    }

    public void editQuiz(Quiz quiz, String title, String description) {
        quiz.setTitle(title);
        quiz.setDescription(description);
        courseList.saveCourses();
    }

    /**
     * @param questionString the question being asked
     * @param choices a list of answer choice
     * @param correctAnswerIndex the index of the correct answer
     * create a question which will set input to a current question being created
     */
    public void createQuestion(String questionString, ArrayList<String> choices, int correctAnswerIndex) {
        Question question = new Question();
        question.setId(UUID.randomUUID());
        question.setQuestion(questionString);
        question.setChoices(choices);
        question.setCorrectAnswerIndex(correctAnswerIndex);

        questionsCreated.add(question);
        questionCount++;
    }

    public void addQuestionToQuiz(Quiz quiz, String question, ArrayList<String> choices, int ansIndex) {
        Question questionToAdd = new Question();
        questionToAdd.setId(UUID.randomUUID());
        questionToAdd.setQuestion(question);
        questionToAdd.setChoices(choices);
        questionToAdd.setCorrectAnswerIndex(ansIndex);

        ArrayList<Question> questions = quiz.getQuestions();
        questions.add(questionToAdd);
        courseList.saveCourses();
    }
    
    public void deleteQuestion(Quiz quiz, Question question) {
        ArrayList<Question> questions = quiz.getQuestions();
        questions.remove(question);
        courseList.saveCourses();
    }

    public void editQuestion(Question questionToEdit, String question, ArrayList<String> choices, int ansIndex) {
        questionToEdit.setQuestion(question);
        questionToEdit.setChoices(choices);
        questionToEdit.setCorrectAnswerIndex(ansIndex);
        courseList.saveCourses();
    }

    public void addComment(ArrayList<Comment> comments, String content) {
        Comment comment = new Comment(user, LocalDate.now(), content, new ArrayList<Comment>());
        comments.add(comment);
        courseList.saveCourses();
    }


    public void deleteComment() {}

    public void editComment() {}

    public Review createReview(Review review) {
        return null;
    }

    public void deleteReview(Review review) {}

    public void editReview(Review review) {}

    // SEARCHING FUNCTIONALITY
    public ArrayList<Course> getCourseByKeyword(String keyword) {
        return null;
    }

    public Course getCourseByTeacher(String teacherName) {
        return null;
    }

    public Course getCourseByRating(int rating) {
        return null;
    }

    public ArrayList<StudentProgress> getCourseProgress() {
        return null;
    }

    public ArrayList<Course> getAllCourses() {
        return null;
    }

    public StudentProgress getCourseProgressByKeyword(String keyword) {
        return null;
    }

    public ArrayList<StudentProgress> getAllCourseProgress() {
        return null;
    }


    // QUIZ TAKING AND GRADING

    /**
     * @param courseID current course ID
     * @param quiz a quiz
     * @param gradePercentage the student's grade
     * update all relavent student progress with grade
     */
    public void updateStudentProgress(UUID courseID, Quiz quiz, double gradePercentage) {
        Course course = courseList.getCourseByUUID(courseID);
        UUID userID = user.getId();
        UUID quizID = quiz.getId();
        ArrayList<StudentProgress> studentProgresses = course.getStudentProgresses();

        if (studentProgresses.contains(course.getStudentProgressByStudentUUID(userID))) { 
            //1. sp is not empty and has the user
            StudentProgress sp = course.getStudentProgressByStudentUUID(userID);
            ArrayList<Grade> grades = sp.getGrades();
            System.out.println(grades);
            if (hasCompletedQuiz(grades, quizID)) {
                System.out.println("Quiz was already taken");
                Grade previousGrade = sp.getGradeByQuizUUID(quizID);
                previousGrade.setGradePercentage(gradePercentage);
            } else {
                grades.add(new Grade(quiz.getId(), gradePercentage));
            }
        } else {
            //2. sp is not empty and does not have user
            // or sp is empty and thus does not have the user
            ArrayList<Grade> grades = new ArrayList<Grade>();
            grades.add(new Grade(quiz.getId(), gradePercentage));
            StudentProgress sp = new StudentProgress(user, grades);
            studentProgresses.add(sp);
        }

        courseList.saveCourses();
    }

    /** MAY REMOVE THIS METHOD
     * @param grades ArrayList of grades for that user's student progress
     * @param quizID quizID of the quiz 
     * @return true if the quiz was completed (i.e. a grade already exists)
    */
    private boolean hasCompletedQuiz(ArrayList<Grade> grades, UUID quizID) {
        for (Grade g : grades) {
            if (g.getQuizID().equals(quizID)) {
                return true;
            }
        }
        return false;
    }


    // ADMIN PRIVLEDGES OVER TEACHERS
    public void addTeacher(Teacher teacher) {}

    public void removeTeacher(Teacher teacher) {}



}
