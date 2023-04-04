package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.POJO.RegistrationDTO;

public interface RegisitrationRepository extends JpaRepository<RegistrationDTO, Integer>{

    List<RegistrationDTO> findByCourseIdAndStudentId(String courseId, Integer studentId);
}
