package university.management.model;

import javax.persistence.*;


@Entity
@Table(name = "LearnerProfiles")
public class LearnerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private int numberOfCredits;

    private float gpa;// grade point average
    private boolean isGraduated;
    private short startYear;

    @OneToOne(mappedBy = "learnerProfile")
    private Student student;


    public LearnerProfile() {
    }

    public LearnerProfile(short startYear) {
        this.startYear = startYear;
    }

    public Student getStudent(){
        return student;
    }

    public Long getProfileId() {
        return profileId;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public float getGpa() {
        return gpa;
    }

    public boolean isGraduated() {
        return isGraduated;
    }

    public short getStartYear() {
        return startYear;
    }
}
