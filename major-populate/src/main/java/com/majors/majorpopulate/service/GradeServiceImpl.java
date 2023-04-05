package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeRepository gradeRepo;

    @Override
    public void save(Grade tblStudentTranscript) {
        gradeRepo.save(tblStudentTranscript);
    }

    // @Override
    // public List<Grade> findByCourseIdAndStudentId(String courseId, int studentId) {
    //     List<Grade> status; 
    //     status = gradeRepo.findByCourseIdAndStudentId(courseId, studentId);
    //     return status;
    // }

    @Override
    public List<Grade> findByCourseIdAndStudentId(String courseId, Integer studentId) {
        List<Grade> status; 
        status = gradeRepo.findByCourseIdAndStudentId(courseId, studentId);
        return status;
    }
    
}
