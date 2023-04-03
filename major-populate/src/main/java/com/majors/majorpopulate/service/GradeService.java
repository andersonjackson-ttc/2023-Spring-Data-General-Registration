package com.majors.majorpopulate.service;

import java.util.List;

import com.majors.majorpopulate.POJO.Grade;

public interface GradeService{

    void saveGrade(Grade tblStudentTranscript);

    
    // <List>Grade findStatusByCourseIdAndStudentId(String courseId, int studentId);
    List<Grade> findByCourseIdAndStudentId(String courseId, Integer studentId);
    
}
