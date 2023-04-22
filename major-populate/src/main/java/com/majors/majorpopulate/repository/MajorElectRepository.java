package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.POJO.MajorElectives;

public interface MajorElectRepository extends JpaRepository<MajorElectives, Integer>{

    List<MajorElectives> findByElectiveGroupId(int elective_id);

    List<MajorElectives> findAllByMajorName(String majorName);
}
