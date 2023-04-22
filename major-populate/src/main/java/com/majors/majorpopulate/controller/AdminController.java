package com.majors.majorpopulate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.service.MajorService;
import com.majors.majorpopulate.service.RegistrationService;



@Controller
public class AdminController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/insertGrades")
    public String getCoursesToGrade(@RequestParam(value = "Id", required = false) int id, Model model) throws Exception{
        model.addAttribute("studentsSchedule", registrationService.findByStudentId(id));
        model.addAttribute("student", MajorService.getStudentById(id));
        
        return "admin-grades-section-list";
    }

    @GetMapping("/deleteCourseFromRegistration")
    public String removeSection(String courseId, int studentId) throws Exception {
        registrationService.deleteByCourseIdAndStudentId(courseId, studentId);
        return "redirect:/exemptGrades?Id=" + studentId;
    }

    @GetMapping("/exemptGrades")
    public String getExemptGradesForm(@RequestParam(value = "Id", required = false) int studentId, Model model){
        model.addAttribute("studentId", studentId);
        Grade grade = new Grade();
        grade.setStudentId(studentId);
        model.addAttribute("addGrade", grade);
        return "admin-exempt-grades-form";
    }
}
