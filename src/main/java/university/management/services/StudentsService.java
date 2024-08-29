package university.management.services;

import org.springframework.stereotype.Service;
import university.management.model.Course;
import university.management.model.Program;
import university.management.model.Student;
import university.management.repositories.StudentsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }
    /**
     * Finds a student by email address.
     * Returns {@code Optional.empty()} if a student with this email doesn't exist
     */
    public Optional<Student> findByEmail(String email) {

        return studentsRepository.findByEmail(email);
    }

    /**
     * Returns all registered students from the database
     * @return
     */
    public List<Student> getAllRegisteredStudents() {
        return studentsRepository.findAll();
    }

    /**
     * Adds a new student to the database
     */
    public void addStudent(String firstName,
                           String lastName,
                           String email, LocalDate dateOfBirth,
                           Program program) {
        Student student = new Student(firstName, lastName, email, dateOfBirth);
        studentsRepository.save(student);
    }

    /**
     * Finds a student by their id.
     * Returns {@code Optional.empty()} if a student with this email doesn't exist
     */
    public Optional<Student> findStudentById(Long id) {
        return studentsRepository.findById(id);

    }

    /**
     * Returns all the courses that a given student is enrolled in
     */
    public Set<Course> getAllStudentCourses(Student student) {
        return student.getEnrolledIn();
    }
}
