package university.management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import university.management.model.Course;
import university.management.model.LearnerProfile;
import university.management.model.Program;
import university.management.model.Student;
import university.management.services.CoursesService;
import university.management.services.LearnerProfileService;
import university.management.services.ProgramService;
import university.management.services.StudentsService;

import java.util.List;


@Controller
public class HomePageController {
    private final LearnerProfileService learnerProfileService;
    private final StudentsService studentsService;
    private final ProgramService programService;
    private final CoursesService coursesService;

    public HomePageController(LearnerProfileService learnerProfileService,
                              StudentsService studentsService,
                              ProgramService programService,
                              CoursesService coursesService) {

        this.learnerProfileService = learnerProfileService;
        this.studentsService = studentsService;
        this.programService = programService;
        this.coursesService = coursesService;
    }

    /**
     * Shows all the courses on the homepage
     */
    @GetMapping("/courses")
    public String getNumberOfStudentPerCourse(Model model) {
        List<Course> courses =
                coursesService.getAllCourses();
        model.addAttribute("courses", courses);

        model.addAttribute("show_students", false);
        model.addAttribute("show_courses", true);
        model.addAttribute("show_programs", false);
        model.addAttribute("show_scores", false);
        return "public_view";
    }

    /**
     * Shows a list of all the students, registered to the university
     */
    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<Student> allRegisteredStudents = studentsService.getAllRegisteredStudents();
        model.addAttribute("students", allRegisteredStudents);

        model.addAttribute("show_students", true);
        model.addAttribute("show_courses", false);
        model.addAttribute("show_programs", false);
        model.addAttribute("show_scores", false);
        return "public_view";
    }

    /**
     * Shows the active students data, sorted by GPA
     */
    @GetMapping("/scores")
    public String getActiveStudentsSortedByGpa(Model model) {
        List<LearnerProfile> activeStudentsProfiles =
                learnerProfileService.getActiveStudentProfilesOrderedByGpa();
        model.addAttribute("students_profiles", activeStudentsProfiles);

        model.addAttribute("show_students", false);
        model.addAttribute("show_courses", false);
        model.addAttribute("show_programs", false);
        model.addAttribute("show_scores", true);
        return "public_view";
    }

    /**
     * Shows all the programs that students are currently enrolled in
     */
    @GetMapping("/programs")
    public String getProgramsWithStudents(Model model) {
        List<Program> programs = programService.getAllProgramsWithStudents();
        model.addAttribute("programs", programs);

        model.addAttribute("show_students", false);
        model.addAttribute("show_courses", false);
        model.addAttribute("show_programs", true);
        model.addAttribute("show_scores", false);
        return "public_view";
    }
}
