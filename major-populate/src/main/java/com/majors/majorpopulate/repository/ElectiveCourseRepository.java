package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.POJO.ElectiveCourses;


public interface ElectiveCourseRepository extends JpaRepository<ElectiveCourses, Integer>{

    List<ElectiveCourses> findByElectiveGroupId(int elective_id);
}
