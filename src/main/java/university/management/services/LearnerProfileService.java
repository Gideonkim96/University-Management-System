package university.management.services;

import org.springframework.stereotype.Service;
import university.management.model.LearnerProfile;
import university.management.repositories.LearnerProfilesRepository;

import java.util.List;

@Service
public class LearnerProfileService {

    private final LearnerProfilesRepository learnerProfilesRepository;

    public LearnerProfileService(LearnerProfilesRepository learnerProfilesRepository) {
        this.learnerProfilesRepository = learnerProfilesRepository;
    }
    /**
     * Returns all the active students' learner profiles in the database,
     * sorted by their GPA in descending order
     */
    public List<LearnerProfile> getActiveStudentProfilesOrderedByGpa() {
        return learnerProfilesRepository.findAllByIsGraduatedIsFalseOrderByGpaDesc();
    }
}
