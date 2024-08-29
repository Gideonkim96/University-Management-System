package university.management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import university.management.model.Course;
import university.management.model.Student;
import university.management.services.CoursesService;
import university.management.services.StudentsService;

import java.util.Optional;


@Controller
public class StudentsController {
    private final StudentsService studentsService;
    private final CoursesService coursesService;

    public StudentsController(StudentsService studentsService,
                              CoursesService coursesService) {
        this.studentsService = studentsService;
        this.coursesService = coursesService;
    }

    /**
     * Shows the student's contact information and profile in the student portal
     */
    @RequestMapping("/student")
    public String getStudentProfile(@RequestParam("student_id") Long studentId, Model model) {
        Optional<Student> student = studentsService.findStudentById(studentId);
        if (student.isEmpty()) {
            System.err.println(String.format("Student with Id %d doesn't exist",
                    studentId));
            return "redirect:/";
        }
        model.addAttribute("student_id", student.get().getId());
        model.addAttribute("student", student.get());
        showStudentProfile(model);

        return "student_view";
    }

    /**
     * Shows all the available courses and all the courses that the student is enrolled in
     */
    @GetMapping("/student/courses")
    public String getAllCourses(@RequestParam("student_id") Long studentId, Model model) {

        Optional<Student> student = studentsService.findStudentById(studentId);
        if (student.isEmpty()) {
            System.err.println(String.format("Student with Id %d doesn't exist",
                    studentId));
            return "redirect:/";
        }
        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));
        showAllCourses(model);
        return "student_view";
    }

    /**
     * Handles a request to enroll a student to a course
     */
    @PostMapping("/student/enroll")
    public String enrollInCourse(@RequestParam("student_id") Long studentId,
                                 @RequestParam("course_id") Long courseId,
                                 Model model) {
        Optional<Student> student = studentsService.findStudentById(studentId);

        if (student.isEmpty()) {
            System.err.printf("Trying to enroll a student with Id %d that doesn't exist%n",
                    studentId);
            return "redirect:/";
        }

        Optional<Course> course = coursesService.findCourse(courseId);
        if (course.isEmpty()) {
            System.err.printf("Trying to enroll a student to course Id: %d that doesn't exist%n",
                    courseId);
            return "redirect:/";
        }

        coursesService.enrollStudent(course.get(), student.get());

        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));

        showAllCourses(model);
        return "student_view";
    }

    /**
     * Handles a request from a student to leave a course they are enrolled in
     */
    @PostMapping("/student/leave_course")
    public String leaveCourse(@RequestParam("student_id") Long studentId,
                              @RequestParam("course_id") Long courseId,
                              Model model) {
        Optional<Student> student = studentsService.findStudentById(studentId);

        if (student.isEmpty()) {
            System.err.printf("Trying to un-enroll a student with Id %d that doesn't exist%n",
                    studentId);
            return "redirect:/";
        }

        Optional<Course> course = coursesService.findCourse(courseId);
        if (course.isEmpty()) {
            System.err.printf("Trying to un-enroll a student from course Id: %d that doesn't " +
                            "exist%n",
                    courseId);
            return "redirect:/";
        }

        coursesService.unEnrollStudent(course.get(), student.get());

        model.addAttribute("student_id", studentId);
        model.addAttribute("all_courses", coursesService.getAllCourses());
        model.addAttribute("student_courses",
                studentsService.getAllStudentCourses(student.get()));

        showAllCourses(model);
        return "student_view";
    }

    private void showStudentProfile(Model model) {
        model.addAttribute("show_courses", false);
    }

    private void showAllCourses(Model model) {
        model.addAttribute("show_courses", true);
    }
}
