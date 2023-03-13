package com.majors.majorpopulate.repository;

import org.springframework.data.repository.CrudRepository;

import com.majors.majorpopulate.student.Student;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    
}
