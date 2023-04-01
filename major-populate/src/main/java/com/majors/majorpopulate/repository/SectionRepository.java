package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.POJO.SectionDTO;

@Repository
public interface SectionRepository extends JpaRepository<SectionDTO, Integer>{
    
    void save(Course tblCourseCatalog);

    List<SectionDTO> findByCourseId(String course_id);
}