package com.majors.majorpopulate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
   @Autowired
    AdminRepository adminRepository;
}
