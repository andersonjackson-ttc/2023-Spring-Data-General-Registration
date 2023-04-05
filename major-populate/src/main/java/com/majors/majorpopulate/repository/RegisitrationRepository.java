package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majors.majorpopulate.POJO.RegistrationDTO;

import jakarta.transaction.Transactional;

@Repository
public interface RegisitrationRepository extends JpaRepository<RegistrationDTO, Integer>{
    @Transactional
    void deleteByCourseIdAndStudentId(String courseId, Integer studentId);
    List<RegistrationDTO> findByCourseIdAndStudentId(String courseId, Integer studentId);
    List<RegistrationDTO> findByStudentId(Integer studentId);
}
