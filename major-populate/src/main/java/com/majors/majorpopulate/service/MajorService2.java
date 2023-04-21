package com.majors.majorpopulate.service;

import java.util.List;

import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.MajorDTO;
import com.majors.majorpopulate.POJO.RegistrationDTO;

public interface MajorService2 {

    List<MajorDTO> findAll();
    List<CourseDTO> findAllCoursesByMajorName(String majorName, int studentId);

    void save(RegistrationDTO newRegisteredSection);
    Object findElectGroupsInMajor(String majorName, int studentId);
   


}
