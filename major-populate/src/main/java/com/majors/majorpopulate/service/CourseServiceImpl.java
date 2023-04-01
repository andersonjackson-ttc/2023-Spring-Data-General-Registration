package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.POJO.CoReq;
import com.majors.majorpopulate.POJO.ElectiveCourses;
import com.majors.majorpopulate.POJO.PreReq;
import com.majors.majorpopulate.repository.CoReqRepository;
import com.majors.majorpopulate.repository.CourseDTORepository;
import com.majors.majorpopulate.repository.ElectiveCourseRepository;
import com.majors.majorpopulate.repository.PreReqRepository;
import com.majors.majorpopulate.repository.SectionRepository;

@Service
public class CourseServiceImpl implements CourseService{
    
    @Autowired
    private PreReqRepository preReqRepo;
    @Autowired
    private CoReqRepository coReqRepo;
    @Autowired
    private CourseDTORepository courseRepo;
    @Autowired
    private SectionRepository sectionRepo;
    @Autowired
    private ElectiveCourseRepository electiveCourseRepo;

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

    @Override
    public List<Section> getSectionByCourseId(String course_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSectionByCourseId'");
    }

    @Override
    public List<ElectiveCourses> getCoursesByElectiveGroupId(int elective_id) {
        List<ElectiveCourses> courseList;
        courseList = electiveCourseRepo.findByElectiveGroupId(elective_id);
        return courseList;
    }

}
