package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.majors.majorpopulate.Course;

@Service
public interface CourseService {
    void saveCourse(Course tblCourseCatalog);

    List<String> getPreReqsByCourseId(String course_id);
}
