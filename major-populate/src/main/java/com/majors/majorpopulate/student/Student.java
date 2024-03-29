package com.majors.majorpopulate.student;

import jakarta.validation.constraints.NotBlank;


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
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPasswordValidation(String passwordValidation) {
        this.passwordValidation = passwordValidation;
    }

    public String getPasswordValidation() {
        return passwordValidation;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
