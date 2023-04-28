package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.majors.majorpopulate.POJO.RegistrationDTO;

import jakarta.transaction.Transactional;

public interface RegisitrationRepository extends JpaRepository<RegistrationDTO, Integer>{
    @Transactional
    void deleteByCourseIdAndStudentId(String courseId, Integer studentId);
    List<RegistrationDTO> findByCourseIdAndStudentId(String courseId, Integer studentId);
    List<RegistrationDTO> findByStudentId(Integer studentId);
    
    @Query(value = "SELECT * FROM tbl_registration where student_id = ?1 ORDER BY CASE WHEN term = '2023 Spring Full' THEN 1 WHEN term = '2023 Spring 1' THEN 2 WHEN term = '2023 Spring 2' THEN 3 WHEN term = '2023 Summer Full' THEN 4 WHEN term = '2023 Summer 1' THEN 5 WHEN term = '2023 Summer 2' THEN 6 WHEN term = '2023 Fall Full' THEN 7 WHEN term = '2023 Fall 1' THEN 8 WHEN term = '2023 Fall 2' THEN 9 END", nativeQuery = true)
    List<RegistrationDTO> findCoursesByStudentIdOrdered(Integer studentId);
}
