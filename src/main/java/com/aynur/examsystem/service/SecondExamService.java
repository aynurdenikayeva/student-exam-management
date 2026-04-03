package com.aynur.examsystem.service;

import com.aynur.examsystem.dto.ApiResponse;
import com.aynur.examsystem.dto.SecondExamRegisterRequest;
import com.aynur.examsystem.entity.FirstExam;
import com.aynur.examsystem.entity.SecondExam;
import com.aynur.examsystem.entity.Student;
import com.aynur.examsystem.repository.FirstExamRepository;
import com.aynur.examsystem.repository.SecondExamRepository;
import com.aynur.examsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecondExamService {
    private final StudentRepository studentRepository;
    private final FirstExamRepository firstExamRepository;
    private final SecondExamRepository secondExamRepository;
    private final PasswordEncoder passwordEncoder;
    private final QrCodeService qrCodeService;
    private final EmailService emailService;

    public ResponseEntity<?> register(SecondExamRegisterRequest request) {
        Student student = studentRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (student == null) {
            student = new Student();
            student.setEmail(request.getEmail());
            student.setPassword(passwordEncoder.encode(request.getPassword()));
            studentRepository.save(student);
        } else {
            if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
                return ResponseEntity.status(401)
                        .body(new ApiResponse("Invalid credentials"));
            }
        }
        FirstExam exam = firstExamRepository.findByStudent(student)
                .orElse(null);

        if (exam == null) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse("First exam record not found"));
        }
        if (exam.getResult() < 70) {
            emailService.sendQr(student.getEmail(), null);
            return ResponseEntity.status(403)
                    .body(new ApiResponse("You did not pass the first exam."));
        }
        if (exam.getSecondExamQrUrl() != null) {
            emailService.sendQr(student.getEmail(), exam.getSecondExamQrUrl());
            return ResponseEntity.ok(new ApiResponse("QR already exists, email sent"));
        }
        String qrUrl = qrCodeService.generateQr(student.getEmail());

        exam.setSecondExamQrUrl(qrUrl);
        firstExamRepository.save(exam);

        SecondExam secondExam = new SecondExam();
        secondExam.setStudent(student);
        secondExamRepository.save(secondExam);
        emailService.sendQr(student.getEmail(), qrUrl);

        return ResponseEntity.ok(new ApiResponse("Registration successful. QR sent via email"));
    }
}