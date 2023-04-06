package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.CoReq;

public interface CoReqRepository extends JpaRepository<CoReq, Integer> {

    void save(Course tblCourseCatalog);

    List<CoReq> findByCourseId(String course_id);
}
