package com.aynur.examsystem.controller;

import com.aynur.examsystem.dto.SecondExamRegisterRequest;
import com.aynur.examsystem.service.SecondExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secondExam")
@RequiredArgsConstructor
public class SecondExamController {
    private final SecondExamService secondExamService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SecondExamRegisterRequest request) {
        return secondExamService.register(request);

    }
}