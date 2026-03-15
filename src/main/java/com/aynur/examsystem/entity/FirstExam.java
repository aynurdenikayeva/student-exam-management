package com.aynur.examsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "first_exam")
@Data
public class FirstExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    private Double result;
    private Boolean pass;

    @Column(name="second_exam_qr_url")
    private String secondExamQrUrl;
}