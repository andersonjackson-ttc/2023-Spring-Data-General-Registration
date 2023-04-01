package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.CourseDTO;

@Repository
public interface CourseRepository extends JpaRepository<CourseDTO, String>{
    
    void save(Course tblCourseCatalog);

    List<String> getPreReqsByCourseId(String course_id);
}
