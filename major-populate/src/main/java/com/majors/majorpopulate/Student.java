package com.majors.majorpopulate;

import com.majors.majorpopulate.Major;

import com.majors.majorpopulate.repository.SqlCaller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "Student")
@Table(name ="tbl_student")
public class Student {

    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private int studentId;

    @NotBlank (message = "Cannot Be Blank")
    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "TEXT"
        )
    private String name;

    @NotBlank (message = "Cannot Be Blank")
    @Column(
        name = "password",
        nullable = false,
        columnDefinition = "TEXT"
        )
    private String password;

    @NotBlank (message = "Cannot Be Blank")
    @Column(
        name = "passwordValidation",
        nullable = false,
        columnDefinition = "TEXT"
        )
    private String passwordValidation;

    @NotBlank (message = "Cannot Be Blank")
    @Column(
        name = "major",
        columnDefinition = "TEXT"
        )
    private String major;
     
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
    public String getMajorId() throws Exception{
        SqlCaller sql = new SqlCaller();
        String majorId = sql.getMajorId(major);
        return majorId;
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
