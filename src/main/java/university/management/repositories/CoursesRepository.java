package university.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.management.model.Course;

/**
 * @author Michael Pogrebinsky - www.topdeveloperacademy.com
 * A Spring Data JPA Repository to perform operations on the Courses table.
 */
@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {
}
