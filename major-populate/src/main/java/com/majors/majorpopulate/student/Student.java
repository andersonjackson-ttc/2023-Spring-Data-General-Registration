package com.majors.majorpopulate.student;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter 
public class Student {


    private int studentId;
    @NotBlank (message = "Cannot Be Blank")
    private String name;
    @NotBlank (message = "Cannot Be Blank")
    private String password;
    @NotBlank (message = "Cannot Be Blank")
    private String passwordValidation;
    @NotBlank (message = "Cannot Be Blank")
    private String major;
     
    public Student()
    {}
    
    public Student (int studentId, String major, String name, String password){
        this.studentId = studentId;
        this.major = major;
        this.name = name;
        this.password = password;
    }

    public Student(String name, String password, String passwordValidation, String major) {
        this.name = name;
        this.password = password;
        this.passwordValidation = passwordValidation;
        this.major = major;
    }

}