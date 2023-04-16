package com.majors.majorpopulate.service;

import java.util.List;

import com.majors.majorpopulate.POJO.Student;

public interface StudentService{
    List<Student> findMajorByStudentId(Integer studentId);
    void save(Student tblStudent);
}