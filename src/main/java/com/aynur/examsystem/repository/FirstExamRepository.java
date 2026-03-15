package com.aynur.examsystem.repository;

import com.aynur.examsystem.entity.FirstExam;
import com.aynur.examsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirstExamRepository extends JpaRepository<FirstExam,Long> {
    Optional<FirstExam> findByStudent(Student student);
}
