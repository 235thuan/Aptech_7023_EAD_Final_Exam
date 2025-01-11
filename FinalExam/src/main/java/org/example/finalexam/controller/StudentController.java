package org.example.finalexam.controller;

import jakarta.validation.Valid;
import org.example.finalexam.dto.StudentScoreDTO;
import org.example.finalexam.entities.Score;
import org.example.finalexam.entities.Student;
import org.example.finalexam.services.ScoreService;
import org.example.finalexam.services.StudentService;
import org.example.finalexam.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class StudentController {
    private final StudentService studentService;
    private final ScoreService scoreService;
    private final SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService,
                             ScoreService scoreService,
                             SubjectService subjectService) {
        this.studentService = studentService;
        this.scoreService = scoreService;
        this.subjectService = subjectService;
    }

    // Show the student creation form
    @GetMapping("/create")
    public String showCreateStudentForm(Model model) {
        // Add an empty student object to the model for the form
        model.addAttribute("student", new Student());
        return "create";  // This will return the create_student.html view
    }

    // Handle the form submission and create the student
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult result, Model model) {
        // If validation fails, return to the form with error messages
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors() + " validate errors");
            model.addAttribute("student", student);  // Re-add the student object to the model
            return "create";  // Return the form again with errors
        }

        // Save the student using the service
        Student savedStudent = studentService.saveStudent(student);

        // Add the saved student to the model to display in the view
        model.addAttribute("student", savedStudent);

        // Redirect to the list page or student details page after successful creation
        return "redirect:/details/" + savedStudent.getStudentId();  // Redirect to the student details page
    }

    // Show the student details page after creation
    @GetMapping("/{id}")
    public String showStudentDetails(@PathVariable("id") int studentId, Model model) {
        // Fetch the student from the service using the ID
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + studentId));

        // Add the student to the model
        model.addAttribute("student", student);

        // Return the student_details view
        return "details";  // This will return the student_details.html view
    }

    // List all students
    @GetMapping("/")
    public String listAll(Model model) {
        List<StudentScoreDTO> studentsWithScores = scoreService.getAllStudentsWithScores();
        model.addAttribute("students", studentsWithScores);
        return "list";
    }

    // Hiển thị form nhập điểm
    @GetMapping("/score/create")
    public String showCreateScoreForm(Model model) {
        model.addAttribute("score", new Score());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "create_score";
    }

    // Xử lý nhập điểm
    @PostMapping("/score/create")
    public String createScore(@Valid @ModelAttribute Score score,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "create_score";
        }

        try {
            scoreService.saveScore(score);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "create_score";
        }
    }
    @GetMapping("/score/edit/{id}")
    public String showEditScoreForm(@PathVariable("id") int id, Model model) {
        Score score = scoreService.getScoreById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid score ID: " + id));

        model.addAttribute("score", score);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());

        return "edit_score";
    }

    // Xử lý cập nhật điểm
    @PostMapping("/score/edit/{id}")
    public String updateScore(@PathVariable("id") int id,
                              @Valid @ModelAttribute Score score,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "edit_score";
        }

        try {
            score.setStudentScoreId(id);
            scoreService.saveScore(score);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "edit_score";
        }
    }

    // Xử lý xóa điểm
    @PostMapping("/score/delete/{id}")
    public String deleteScore(@PathVariable("id") int id) {
        try {
            studentService.deleteStudent(id);
            return "redirect:/?success=deleted";
        } catch (Exception e) {
            return "redirect:/?error=delete-failed";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditStudentForm(@PathVariable("id") int studentId, Model model) {
        Optional<Student> studentOpt = studentService.getStudentById(studentId);

        if (studentOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid student ID: " + studentId);
        }
        model.addAttribute("student", studentOpt.get());  // Add the existing student to the model
        return "edit";  // Return the edit_student.html view
    }

    // Handle the form submission for editing a student
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") int studentId, @Valid @ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";  // Return the edit form if there are errors
        }

        // Set the ID of the student to ensure it gets updated correctly
        student.setStudentId(studentId);

        // Save the updated student
        studentService.saveStudent(student);

        // Add the updated student to the model
        model.addAttribute("student", student);

        // Redirect to the student details page
        return "redirect:/details/{id}";  // Redirect to the details page after update
    }


    @GetMapping("/create/{studentId}")
    public String showCreateForm(@PathVariable Integer studentId, Model model) {
        return studentService.getStudentById(studentId)
                .map(student -> {
                    model.addAttribute("student", student);
                    model.addAttribute("subjects", subjectService.getAllSubjects());
                    model.addAttribute("score", new Score());
                    return "create_score_id";
                })
                .orElse("redirect:/student/?error=student-not-found");
    }

    @PostMapping("/create/{studentId}")
    public String createScore(@PathVariable Integer studentId,
                              @Valid @ModelAttribute Score score,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            studentService.getStudentById(studentId).ifPresent(student ->
                    model.addAttribute("student", student));
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "create_score_id";
        }

        try {
            return studentService.getStudentById(studentId)
                    .map(student -> {
                        score.setStudent(student);
                        scoreService.saveScore(score);
                        return "redirect:/?success=score-added";
                    })
                    .orElse("redirect:/?error=student-not-found");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            studentService.getStudentById(studentId).ifPresent(student ->
                    model.addAttribute("student", student));
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "create_score_id";
        }
    }
}
