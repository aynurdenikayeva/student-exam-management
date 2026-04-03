package com.aynur.examsystem.controller;

import com.aynur.examsystem.entity.Student;
import com.aynur.examsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student savedStudent = studentService.registerStudent(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPassword()
        );
        return ResponseEntity.ok(savedStudent);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student student) {
        boolean valid = studentService.validateLogin(student.getEmail(), student.getPassword());
        if (valid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        // Burada artıq login olan istifadəçi yoxlanacaq SecurityConfig vasitəsilə
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}