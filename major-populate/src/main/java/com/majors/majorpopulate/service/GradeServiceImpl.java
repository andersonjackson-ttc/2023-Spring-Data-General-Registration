package com.majors.majorpopulate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeRepository gradeRepo;

    @Override
    public void saveGrade(Grade tblStudentTranscript) {
        this.gradeRepo.save(tblStudentTranscript);
    }

    @Override
    public Grade findStatusByCourseIdAndStudentId(String courseId, int studentId) {
        Grade status = gradeRepo.findStatusByCourseIdAndStudentId(courseId, studentId);
        return status;
        }

    
}
