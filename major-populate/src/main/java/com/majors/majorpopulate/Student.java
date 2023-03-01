package com.majors.majorpopulate;

import com.majors.majorpopulate.Major;

import com.majors.majorpopulate.repository.SqlCaller;

import jakarta.validation.constraints.NotBlank;


public class Student {
    @NotBlank (message = "Cannot Be Blank")
    private String name;
    @NotBlank (message = "Cannot Be Blank")
    private String password;
    @NotBlank (message = "Cannot Be Blank")
    private String passwordValidation;
    @NotBlank (message = "Cannot Be Blank")
    private Major major;
     
    public Student()
    {}
    public Student(String name, String password, String passwordValidation, Major major) {
        this.name = name;
        this.password = password;
        this.passwordValidation = passwordValidation;
        this.major = major;
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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void setPasswordValidation(String passwordValidation) {
        this.passwordValidation = passwordValidation;
    }

    public String getPasswordValidation() {
        return passwordValidation;
    }
<<<<<<< Updated upstream:major-populate/src/main/java/com/majors/majorpopulate/Student.java
    
    public String getMajorId(){
        return major.getMajorId();
=======
    public String getMajorId() throws Exception{
        SqlCaller sql = new SqlCaller();
        String majorId = sql.getMajorId(major);
        return majorId;
>>>>>>> Stashed changes:major-populate/src/main/java/com/majors/majorpopulate/student/Student.java
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
    ///completed courses student->many courses
    ///registered courses student->many courses

}
