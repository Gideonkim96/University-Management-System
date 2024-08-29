package university.management.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String title;
    private String description;
    private int numberOfCredits;

    @ManyToMany
    @JoinTable(name = "COURSE_ENROLLMENTS",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "enrolled_student_id", referencedColumnName = "id"))
    private Set<Student> enrolledStudents;

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addEnrolledStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void removeEnrolledStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public Course() {
    }

    public Course(String title, String description, int numberOfCredits) {
        this.title = title;
        this.description = description;
        this.numberOfCredits = numberOfCredits;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
