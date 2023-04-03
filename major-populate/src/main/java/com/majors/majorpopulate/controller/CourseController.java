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
    /* @GetMapping("/mainpage")
    public String populateInfo(Model model) throws Exception {
        String majorName = MajorService.loggedInUser.get(0).getMajorName();
        String name = MajorService.loggedInUser.get(0).getName();
        Major major = MajorService.getMajorById(MajorService.loggedInUser.get(0).getMajorID());
        int studentId = MajorService.getStudentId();
        MajorService.getCourseStatusForStudent(studentId, major);
        model.addAttribute("information", new Major(name, majorName));
        model.addAttribute("coreRequirements", major.getRequiredCourses());
        model.addAttribute("electives", major.MajorElectiveGroups);

        return "mainpage";
    } */
}
    
