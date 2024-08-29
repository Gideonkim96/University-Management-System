package university.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.management.model.Program;

import java.util.Optional;

/**
 * @author Michael Pogrebinsky - www.topdeveloperacademy.com
 * A JPA Repository to perform operations on the Programs table
 */
@Repository
public interface ProgramsRepository extends JpaRepository<Program, Long> {
    Optional<Program> findByFieldOfStudyAndDegree(String fieldOfStudy, Program.Degree degree);
}
