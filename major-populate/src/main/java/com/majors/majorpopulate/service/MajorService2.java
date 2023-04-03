package com.majors.majorpopulate.service;

import java.util.List;

import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.GradRequirements;
import com.majors.majorpopulate.POJO.MajorDTO;

public interface MajorService2 {

    List<MajorDTO> findAll();
    List<CourseDTO> findAllCoursesByMajorName(String majorName, int studentId);
}
