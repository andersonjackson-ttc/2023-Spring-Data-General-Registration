package com.majors.majorpopulate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majors.majorpopulate.POJO.Student;
import com.majors.majorpopulate.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepo studentRepo;

    // @Override
    // public void save(Student tblStudent) {
    //     studentRepo.save(tblStudent);
    // }

    // @Override
    // public List<Student> findMajorByStudentId(Integer studentId) {
    //     List<Student> studentInfo; 
    //     studentInfo = studentRepo.findMajorByStudentId(studentId);
    //     return studentInfo;
    // }

    @Override
    public Student findByNameAndPassword(String name, String password) {
          return studentRepo.findByNameAndPassword(name, password);
  
    }
    
}