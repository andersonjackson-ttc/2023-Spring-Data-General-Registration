package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.majors.majorpopulate.POJO.RegistrationDTO;
import com.majors.majorpopulate.repository.RegisitrationRepository;

public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegisitrationRepository registerRepo;

    @Override
    public List<RegistrationDTO> findByCourseIdAndStudentId(String courseId, Integer studentId) {
        List<RegistrationDTO> registerStatus; 
        registerStatus = registerRepo.findByCourseIdAndStudentId(courseId, studentId);
        return registerStatus;
    }
}
