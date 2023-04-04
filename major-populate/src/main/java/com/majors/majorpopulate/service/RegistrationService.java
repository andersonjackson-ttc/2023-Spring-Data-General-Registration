package com.majors.majorpopulate.service;

import java.util.List;

import com.majors.majorpopulate.POJO.RegistrationDTO;

public interface RegistrationService {
    List<RegistrationDTO> findByCourseIdAndStudentId(String courseId, Integer studentId);

}
