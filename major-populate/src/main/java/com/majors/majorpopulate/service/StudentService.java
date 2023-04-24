package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.Student;
import com.majors.majorpopulate.repository.StudentRepo;
 
@Service
public interface StudentService{

    Student findByNameAndPassword(String name, String password);
    // List<Student> findMajorByStudentId(Integer studentId);
    // void save(Student tblStudent);
} 
