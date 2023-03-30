package com.majors.majorpopulate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.majors.majorpopulate.POJO.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    
}
