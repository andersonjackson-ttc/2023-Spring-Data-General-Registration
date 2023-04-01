package com.majors.majorpopulate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.majors.majorpopulate.service.CourseService;

@Controller 
public class CourseController {
    
    @Autowired
    private CourseService cs;

    @GetMapping("/showPreReqs")
    public List<String> getCourseSearch(Model model, String course_id) {
        List<String> list = cs.getPreReqsByCourseId(course_id);
        model.addAttribute("pre_reqs_name", list);
        return list;
    }
}
    
