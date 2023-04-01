package com.majors.majorpopulate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.majors.majorpopulate.POJO.PreReq;
import com.majors.majorpopulate.service.CourseService;

@Controller 
public class CourseController {
    
    @Autowired
    private CourseService cs;

    /* @GetMapping("/showPreReqs")
    public List<PreReq> getCourseSearch(Model model, String course_id) {
        List<PreReq> list = cs.getPreReqsByCourseId(course_id);
        model.addAttribute("pre_reqs_name", list);
        return list;
    } */
    @GetMapping("/showPreReqs")
    public ResponseEntity<List<PreReq>> getPreReqsByCourseId(@RequestParam String course_id) {
        var response = new ResponseEntity<List<PreReq>>(cs.getPreReqsByCourseId(course_id), HttpStatus.OK);
        return response;
    }
}
    
