package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majors.majorpopulate.POJO.GradRequirements;

@Repository
public interface GradReqRepository extends JpaRepository<GradRequirements, Integer>{
    
    List<GradRequirements> findByMajorId(int major_id);
}
