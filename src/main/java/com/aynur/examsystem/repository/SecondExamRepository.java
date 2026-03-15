package com.aynur.examsystem.repository;

import com.aynur.examsystem.entity.SecondExam;
import com.aynur.examsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SecondExamRepository extends JpaRepository<SecondExam, Long> {
    boolean existsByStudentId(Long studentId);
    Optional<SecondExam> findByStudent(Student student);

}