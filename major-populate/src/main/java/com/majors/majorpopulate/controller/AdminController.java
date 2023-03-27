package com.majors.majorpopulate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.majors.majorpopulate.service.MajorService;



@Controller
public class AdminController {

    @GetMapping("/insertGrades")
    public String getCoursesToGrade(@RequestParam(value = "Id", required = false) int id, Model model) throws Exception{
        model.addAttribute("studentsSchedule", MajorService.getRegisteredSections(id));
        model.addAttribute("student", MajorService.getStudentById(id));
        return "admin-grades-section-list";
    }





    

}
