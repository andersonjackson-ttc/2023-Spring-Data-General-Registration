package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.RegistrationDTO;
import com.majors.majorpopulate.repository.CourseDTORepository;
import com.majors.majorpopulate.repository.RegisitrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegisitrationRepository registerRepo;
    @Autowired
    private CourseDTORepository courseRepo;


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
        for (int i = 0; i < registeredCourses.size(); i++) {
            List<CourseDTO> course = courseRepo.findByCourseId(registeredCourses.get(i).getCourseId());
            registeredCourses.get(i).setCourseName(course.get(0).getCourseTitle());
        }
        return registeredCourses;
    }

    @Override
    public void delete(RegistrationDTO course) {
        registerRepo.delete(course);
    }

    @Override
    public List<RegistrationDTO> findCoursesByStudentIdOrdered(Integer studentId) {
        List<RegistrationDTO> registeredCourses; 
        registeredCourses = registerRepo.findCoursesByStudentIdOrdered(studentId);
        for (int i = 0; i < registeredCourses.size(); i++) {
            List<CourseDTO> course = courseRepo.findByCourseId(registeredCourses.get(i).getCourseId());
            registeredCourses.get(i).setCourseName(course.get(0).getCourseTitle());
        }
        return registeredCourses;
    }

}
