package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.MajorDTO;
import com.majors.majorpopulate.repository.MajorDTORepository;

@Service
public class MajorServiceImpl implements MajorService2{

    @Autowired
    private MajorDTORepository majorRepo;

    @Override
    public List<MajorDTO> findAll() {
        List<MajorDTO> majors;
        majors = majorRepo.findAll();
        return majors;
    }
    
}
