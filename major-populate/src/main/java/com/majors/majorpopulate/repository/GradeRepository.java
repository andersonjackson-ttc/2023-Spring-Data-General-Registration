package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.POJO.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    
    List<Grade> findByCourseIdAndStudentId(String courseId, Integer studentId);
}
