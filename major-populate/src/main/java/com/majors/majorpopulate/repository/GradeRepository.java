package com.majors.majorpopulate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.majors.majorpopulate.POJO.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Query(value = "Select course_status FROM tbl_student_transcript WHERE course_id = :courseId AND student_id = :studentId ", nativeQuery = true)
    Grade findStatusByCourseIdAndStudentId(@Param("courseId") String courseId, @Param("studentId") int studentId);
}
