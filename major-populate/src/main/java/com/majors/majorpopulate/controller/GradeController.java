package com.majors.majorpopulate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.service.GradeService;
import com.majors.majorpopulate.service.RegistrationService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class GradeController {

    @Autowired
    private GradeService gradesService;
    
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/gradeSubmit")
    public void saveGrade(@ModelAttribute Grade tblStudentTranscript, HttpServletResponse response) throws Exception {
        gradesService.save(tblStudentTranscript);
        // registrationService.deleteByCourseIdAndStudentId(tblStudentTranscript.getCourseId(), MajorService.getStudentId());
        // RegistrationDTO deleteFromRegistration = new RegistrationDTO();
        // deleteFromRegistration.setStudentId(tblStudentTranscript.getStudentId());
        // deleteFromRegistration.setTerm(tblStudentTranscript.getTermId());
        // deleteFromRegistration.setCourseId(tblStudentTranscript.getTermId());
        // deleteFromRegistration.setMajorId(MajorService.loggedInUser.get(0).getMajorID());
        // deleteFromRegistration.setRegDTS("Registered");;
        // registrationService.delete(deleteFromRegistration);
        // MajorService.adminDeleteSection(tblStudentTranscript.getStudentId(), tblStudentTranscript.getCourseId());
        response.sendRedirect("/deleteCourseFromRegistration?courseId=" + tblStudentTranscript.getCourseId() + "&studentId=" + tblStudentTranscript.getStudentId());     
    }
}
