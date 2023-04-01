package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
    
    @Autowired
    private CourseRepository courseRepo;

    @Override
    public void saveCourse(Course tblCourseCatalog) {
        this.courseRepo.save(tblCourseCatalog);
    }

    @Override
    public List<String> getPreReqsByCourseId(String course_id) {
        
        List<String> pre_reqs_name;
        pre_reqs_name = courseRepo.getPreReqsByCourseId(course_id);
        return pre_reqs_name;
    }

}
