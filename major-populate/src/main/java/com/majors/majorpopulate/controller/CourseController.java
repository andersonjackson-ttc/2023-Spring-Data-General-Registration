package com.majors.majorpopulate.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.majors.majorpopulate.POJO.CoReq;
import com.majors.majorpopulate.POJO.ElectiveCourses;
import com.majors.majorpopulate.POJO.PreReq;
import com.majors.majorpopulate.service.CourseService;

@Controller 
public class CourseController {
    
    @Autowired
    private CourseService cs;

    @GetMapping("/showCourseName")
    public String getCourseName(Model model, String course_id) {
        String courseName = cs.getNameByCourseId(course_id);
        model.addAttribute("pre_reqs_name", courseName);
        return courseName;
    }
    @GetMapping("/showPreReqs")
    public ResponseEntity<List<PreReq>> getPreReqsByCourseId(@RequestParam String course_id) {
        var response = new ResponseEntity<List<PreReq>>(cs.getPreReqsByCourseId(course_id), HttpStatus.OK);
        return response;
    }
    @GetMapping("/showCoReqs")
    public ResponseEntity<List<CoReq>> getCoReqsByCourseId(@RequestParam String course_id) {
        var response = new ResponseEntity<List<CoReq>>(cs.getCoReqsByCourseId(course_id), HttpStatus.OK);
        return response;
    }
    @GetMapping("/showCoursesInElectiveGroup")
    public ResponseEntity<List<ElectiveCourses>> getCoursesByElectiveGroup(@RequestParam int elective_id){
        var response = new ResponseEntity<List<ElectiveCourses>>(cs.getCoursesByElectiveGroupId(elective_id),HttpStatus.OK);
        return response;
    }
}
    
