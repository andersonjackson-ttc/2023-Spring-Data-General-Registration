package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.POJO.RegistrationDTO;

import jakarta.transaction.Transactional;

public interface RegistrationRepository extends JpaRepository<RegistrationDTO, Integer>{
    @Transactional
    void deleteByCourseIdAndStudentId(String courseId, Integer studentId);
    List<RegistrationDTO> findByCourseIdAndStudentId(String courseId, Integer studentId);
    List<RegistrationDTO> findByStudentId(Integer studentId);
}
