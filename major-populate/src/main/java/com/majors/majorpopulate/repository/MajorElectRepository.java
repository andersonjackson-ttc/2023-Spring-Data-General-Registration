package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majors.majorpopulate.POJO.MajorElectives;

@Repository
public interface MajorElectRepository extends JpaRepository<MajorElectives, Integer>{

    List<MajorElectives> findByElectiveGroupId(int elective_id);
}
