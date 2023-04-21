package com.majors.majorpopulate.POJO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_registration")
public class RegistrationDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "term")
    private String term;
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "major_id")
    private String majorId;
    @Column(name = "reg_dts")
    private String regDTS;
    @Transient
    private String courseName;
}