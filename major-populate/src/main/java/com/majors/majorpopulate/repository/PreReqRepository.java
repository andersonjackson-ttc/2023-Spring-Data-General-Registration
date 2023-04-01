package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.CourseDTO;
import com.majors.majorpopulate.POJO.PreReq;

@Repository
public interface PreReqRepository extends JpaRepository<PreReq, Integer>{
    
    void save(Course tblCourseCatalog);

    List<PreReq> findByCourseId(String course_id);
}
