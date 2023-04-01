package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.CoReq;
import com.majors.majorpopulate.POJO.PreReq;
import com.majors.majorpopulate.repository.CoReqRepository;
import com.majors.majorpopulate.repository.CourseDTORepository;
import com.majors.majorpopulate.repository.PreReqRepository;

@Service
public class CourseServiceImpl implements CourseService{
    
    @Autowired
    private PreReqRepository preReqRepo;
    private CoReqRepository coReqRepo;
    private CourseDTORepository courseRepo;

    @Override
    public void saveCourse(Course tblCourseCatalog) {
        this.courseRepo.save(tblCourseCatalog);
    }

    @Override
    public List<PreReq> getPreReqsByCourseId(String course_id) {
        List<PreReq> pre_reqs;
        pre_reqs = preReqRepo.findByCourseId(course_id);
        return pre_reqs;
    }

    @Override
    public List<CoReq> getCoReqsByCourseId(String course_id) {
        List<CoReq> co_reqs;
        co_reqs = coReqRepo.findByCourseId(course_id);
        return co_reqs;
    }
   
    @Override
    public String getNameByCourseId(String course_id) {
        String courseName;
        return courseName = courseRepo.findByCourseId(course_id);
    }

}
