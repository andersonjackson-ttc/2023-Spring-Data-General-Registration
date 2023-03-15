package com.majors.majorpopulate.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.majors.majorpopulate.repository.AdminRepository;

public class AdminController {
    
    @Autowired
    AdminRepository adminRepository;
}
