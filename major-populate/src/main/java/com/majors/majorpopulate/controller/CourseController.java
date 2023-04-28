package com.majors.majorpopulate.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.majors.majorpopulate.POJO.CoReq;
import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.PreReq;
import com.majors.majorpopulate.service.CourseService;
import com.majors.majorpopulate.service.MajorService;

@Controller 
public class CourseController {
    
    @Autowired
    private CourseService cs;

    @GetMapping("/showCoReqs")
    public ResponseEntity<List<CoReq>> getCoReqsByCourseId(@RequestParam String course_id) {
        var response = new ResponseEntity<List<CoReq>>(cs.getCoReqsByCourseId(course_id), HttpStatus.OK);
        return response;
    }
    @GetMapping("/showCoursesInElectiveGroup")
    public String showCoursesByElectiveGroup(Model model, @RequestParam int groupId) throws Exception{
        int studentId = MajorService.getStudentId();
        var response = cs.findByElectiveGroupId(groupId, studentId);
        model.addAttribute("electiveCourses",response);
        return "electivePage";
    }

    @GetMapping("/showPreReqPage")
    public String preReqPage(Model model, @RequestParam String course_id) {
        List<CourseDTO> courseName = cs.getNameByCourseId(course_id);
        if (courseName.size() == 0){
            model.addAttribute("courseName", null);
            model.addAttribute("courseId", course_id);
            return "preReqPage";
        }
        model.addAttribute("courseName", courseName.get(0));
        List<PreReq> preReq = cs.getPreReqsByCourseId(course_id);
        for (int i = 0; i < preReq.size(); i++) {
            List<CourseDTO> preReqCourseTitle = cs.getNameByCourseId(preReq.get(i).getPre_req());
            preReq.get(i).setCourseTitle(preReqCourseTitle.get(0).getCourseTitle());
        }
        model.addAttribute("preReqs", preReq);
        return "preReqPage";
    }

}
    
