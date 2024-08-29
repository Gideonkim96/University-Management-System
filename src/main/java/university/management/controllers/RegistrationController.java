package university.management.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import university.management.model.Program;
import university.management.model.Student;
import university.management.services.ProgramService;
import university.management.services.StudentsService;

import java.time.LocalDate;
import java.util.Locale;


@Controller
public class RegistrationController {
    private final StudentsService studentsService;
    private final ProgramService programService;

    public RegistrationController(StudentsService studentsService, ProgramService programService) {
        this.studentsService = studentsService;
        this.programService = programService;
    }

    /**
     * Handles the form submission when a new student registers to the university
     */
    @GetMapping("/register")
    public String signUpNewStudent(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("email") String email,
                                   @RequestParam("dateOfBirth")
                                   @DateTimeFormat(pattern = "yyyy-MM-dd")
                                           LocalDate dateOfBirth,
                                   @RequestParam("fieldOfStudy") String fieldOfStudy,
                                   @RequestParam("degree") String degreeString) {
        Program.Degree degree = Program.Degree.valueOf(degreeString.toUpperCase(Locale.ROOT));
        Program program = programService.getOrCreateProgram(fieldOfStudy, degree);

        studentsService.addStudent(firstName, lastName, email, dateOfBirth, program);

        return "redirect:/";
    }
}
