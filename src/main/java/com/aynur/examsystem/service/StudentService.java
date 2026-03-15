package com.aynur.examsystem.service;

import com.aynur.examsystem.entity.Student;
import com.aynur.examsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public Student registerStudent(String firstName, String lastName, String email, String rawPassword) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setPassword(passwordEncoder.encode(rawPassword));
        return studentRepository.save(student);
    }
    public boolean validateLogin(String email, String rawPassword) {
        return studentRepository.findByEmail(email)
                .map(student -> passwordEncoder.matches(rawPassword, student.getPassword()))
                .orElse(false);
    }
    public Object getAllStudents()
        {
        return studentRepository.findAll();
        }
}