package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.majors.majorpopulate.POJO.MajorDTO;

@Repository
public interface MajorDTORepository extends JpaRepository<MajorDTO, Integer>{
    
    List<MajorDTO> findByMajorId(int major_id);
}
