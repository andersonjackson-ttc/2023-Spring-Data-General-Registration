package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.PreReq;


public interface PreReqRepository extends JpaRepository<PreReq, Integer>{
    
    void save(Course tblCourseCatalog);

    List<PreReq> findByCourseId(String course_id);
}
