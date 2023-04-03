package com.majors.majorpopulate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.GradRequirements;
import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.POJO.MajorDTO;
import com.majors.majorpopulate.repository.CourseDTORepository;
import com.majors.majorpopulate.repository.GradReqRepository;
import com.majors.majorpopulate.repository.GradeRepository;
import com.majors.majorpopulate.repository.MajorDTORepository;

@Service
public class MajorServiceImpl implements MajorService2{

    @Autowired
    private MajorDTORepository majorRepo;
    @Autowired
    private GradReqRepository gradReqRepo;
    @Autowired
    private CourseDTORepository courseRepo;
    @Autowired
    private GradeRepository gradeRepo;

    @Override
    public List<MajorDTO> findAll() {
        List<MajorDTO> majors;
        majors = majorRepo.findAll();
        return majors;
    }

    @Override
    public List<CourseDTO> findAllCoursesByMajorName(String majorName, int studentId) {
        List<GradRequirements> requiredCourseList;
        List<CourseDTO> courseList =  new ArrayList<CourseDTO>();
        requiredCourseList = gradReqRepo.findAllByMajorName(majorName);
        for(int i = 0; i < requiredCourseList.size();i++)
        {
            String courseId = requiredCourseList.get(i).getCourseId().trim();
            CourseDTO course = courseRepo.findByCourseId(courseId);
            /* Grade grade = gradeRepo.findStatusByCourseIdAndStudentId(courseId, studentId);
            String status = grade.getCourseStatus();
            if(status == null){
                course.setStatus("");
            }
            course.setStatus(status); */
            courseList.add(course);
        }
        return courseList;
    }
    
}
