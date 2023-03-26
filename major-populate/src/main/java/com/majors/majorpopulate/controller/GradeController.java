package com.majors.majorpopulate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.service.GradeService;
import com.majors.majorpopulate.service.MajorService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class GradeController {

    @Autowired
    private GradeService gradesService;
    

    @PostMapping("/gradeSubmit")
    public void saveGrade(@ModelAttribute Grade tblStudentTranscript, HttpServletResponse response) throws Exception {
        gradesService.saveGrade(tblStudentTranscript);
        MajorService.adminDeleteSection(tblStudentTranscript.getStudentId(), tblStudentTranscript.getCourseId());
        response.sendRedirect("/adminStudentSchedule?Id=" + tblStudentTranscript.getStudentId());
        
    }
    
}
