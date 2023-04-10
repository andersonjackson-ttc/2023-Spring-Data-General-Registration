package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.RegistrationDTO;
import com.majors.majorpopulate.repository.RegisitrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegisitrationRepository registerRepo;

    @Override
    public List<RegistrationDTO> findByCourseIdAndStudentId(String courseId, Integer studentId) {
        List<RegistrationDTO> registerStatus; 
        registerStatus = registerRepo.findByCourseIdAndStudentId(courseId, studentId);
        return registerStatus;
    }

    @Override
    public void deleteByCourseIdAndStudentId(String courseId, Integer studentId) {
        registerRepo.deleteByCourseIdAndStudentId(courseId, studentId);
    }

    @Override
    public List<RegistrationDTO> findByStudentId(Integer studentId) {
        List<RegistrationDTO> registeredCourses; 
        registeredCourses = registerRepo.findByStudentId(studentId);
        return registeredCourses;
    }

    @Override
    public void delete(RegistrationDTO course) {
        registerRepo.delete(course);
    }

}
