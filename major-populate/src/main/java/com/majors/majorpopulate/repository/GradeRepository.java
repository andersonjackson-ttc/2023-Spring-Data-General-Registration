package com.majors.majorpopulate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.POJO.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    
}
