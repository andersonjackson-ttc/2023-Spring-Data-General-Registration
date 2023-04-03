package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.POJO.CoReq;
import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.ElectiveCourses;
import com.majors.majorpopulate.POJO.PreReq;

@Service
public interface CourseService {
    void saveCourse(Course tblCourseCatalog);

    List<PreReq> getPreReqsByCourseId(String course_id);
    List<CoReq> getCoReqsByCourseId(String course_id);
    CourseDTO findByCourseId(String course_id);
    List<Section> getSectionByCourseId(String course_id);
    List<ElectiveCourses> getCoursesByElectiveGroupId(int elective_id);
    
}
