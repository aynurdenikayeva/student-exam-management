package com.aynur.examsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "second_exam")
@Data
public class SecondExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    private Double result;
    private Boolean pass;
}