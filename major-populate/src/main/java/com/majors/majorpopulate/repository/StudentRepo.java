package com.majors.majorpopulate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majors.majorpopulate.POJO.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    
    Student findByNameAndPassword(String name, String password);
    // List<Student> findMajorByStudentId(Integer studentId);
}