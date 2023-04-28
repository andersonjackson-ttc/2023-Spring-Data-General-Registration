package com.majors.majorpopulate.service;

import java.util.List;



import com.majors.majorpopulate.POJO.RegistrationDTO;

public interface RegistrationService {
    List<RegistrationDTO> findByCourseIdAndStudentId(String courseId, Integer studentId);
    void deleteByCourseIdAndStudentId(String courseId, Integer studentId);
    List<RegistrationDTO> findByStudentId(Integer studentId);
    void delete(RegistrationDTO course);

    List<RegistrationDTO> findCoursesByStudentIdOrdered(Integer studentId);



}
