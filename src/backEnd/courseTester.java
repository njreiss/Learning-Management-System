package backEnd;
import java.util.ArrayList;
import java.time.LocalDate;

public class courseTester {
    public static void main(String args[])  {  
        // Create Quiz with Questions
        ArrayList<String> choices = new ArrayList<String>();
        choices.add("bob");
        Question question1 = new Question("Why?", choices, 0);
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(question1);
        Quiz quiz1 = new Quiz("Sky Quiz", "This will test you.", questions);

        // Create Lessons
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        Lesson lesson1 = new Lesson("bob", "jane", null);
        lessons.add(lesson1);

        // Create User (Student) and Comments
        LocalDate student1DOB = LocalDate.of(2002, 3, 12);
        LocalDate comment1Date = LocalDate.of(2023, 3, 17);
        User student1 = new Student("caleb", "brunson", "crb", "crb@email.com", "pass123", null, null, null);
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Comment comment1 = new Comment(student1, comment1Date, "Why is the sky blue?", null);
        comments.add(comment1);

        // Create Topics
        ArrayList<Topic> topics = new ArrayList<Topic>();
        Topic topic1 = new Topic("The Sky", "Learn about the sky.", quiz1, lessons, comments);
        topics.add(topic1);

        // Create StudentProgress
        ArrayList<StudentProgress> progressList = new ArrayList<StudentProgress>();
        ArrayList<Double> grades = new ArrayList<Double>();
        grades.add(100.0);
        //StudentProgress student1Progress = new StudentProgress(student1, grades);
        //progressList.add(student1Progress);

        // Create Reviews
        ArrayList<Review> reviews = new ArrayList<Review>();
        //Review review1 = new Review(student1, comment1Date, 5, "Great course!!!");
        //reviews.add(review1);

        // Create Teacher and Course
        LocalDate teacher1DOB = LocalDate.of(1994, 6, 17);
        User teacher1 = new Teacher("bob", "jones", "bj36", "bobj@email.com", "scooby420", null, null, null);
        Course course1 = new Course("Sky Course", Language.C, "Learn about Sky", (Teacher)teacher1, topics, reviews, comments, progressList);

        System.out.println(teacher1);

    }

}
