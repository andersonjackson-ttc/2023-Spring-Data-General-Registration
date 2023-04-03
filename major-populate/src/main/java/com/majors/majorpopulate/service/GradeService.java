package com.majors.majorpopulate.service;

import com.majors.majorpopulate.POJO.Grade;

public interface GradeService{

    void saveGrade(Grade tblStudentTranscript);

    
    Grade findStatusByCourseIdAndStudentId(String courseId, int studentId);
    
    
}
