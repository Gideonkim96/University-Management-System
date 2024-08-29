package university.management.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import university.management.model.Program;
import university.management.repositories.ProgramsRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ProgramService {

    private final ProgramsRepository programsRepository;

    public ProgramService(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }
    /**
     * Returns the Program object by field of study and degree from the database if it exists
     * or creates a new object
     */
    public Program getOrCreateProgram(String fieldOfStudy, Program.Degree degree) {
        Optional<Program> program = programsRepository.findByFieldOfStudyAndDegree(fieldOfStudy,
                degree);
        if (program.isPresent()) {
            return program.get();
        }
        return new Program(fieldOfStudy, degree);
    }

    /**
     * Returns all the programs from the database
     */
    public List<Program> getAllProgramsWithStudents() {
        Sort sortOrder = Sort.by("fieldOfStudy").ascending()
                .and(Sort.by("degree").ascending());
        return programsRepository.findAll(sortOrder);
    }

}