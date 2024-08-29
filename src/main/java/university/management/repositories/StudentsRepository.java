package university.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.management.model.Student;

import java.util.Optional;

/**
 * @author Michael Pogrebinsky - www.topdeveloperacademy.com
 * A JPA Repository to perform operations on the Students table
 */
@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
