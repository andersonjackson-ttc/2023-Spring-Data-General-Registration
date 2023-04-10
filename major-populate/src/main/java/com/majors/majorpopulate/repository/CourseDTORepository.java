package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.CourseDTO;


public interface CourseDTORepository extends JpaRepository<CourseDTO, Integer>{
    
    void save(Course tblCourseCatalog);

    List<CourseDTO> findByCourseId(String course_id);
    

    String findCourseTitleByCourseId(String courseId);
}