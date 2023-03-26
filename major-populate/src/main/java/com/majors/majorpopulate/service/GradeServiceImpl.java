package com.majors.majorpopulate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeRepository adminRepo;

    @Override
    public void saveGrade(Grade tblStudentTranscript) {
        this.adminRepo.save(tblStudentTranscript);
    }

    
}
