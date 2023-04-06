package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.POJO.GradRequirements;

public interface GradReqRepository extends JpaRepository<GradRequirements, Integer> {

    List<GradRequirements> findByMajorId(int major_id);

    List<GradRequirements> findAllByMajorName(String majorName);
}
