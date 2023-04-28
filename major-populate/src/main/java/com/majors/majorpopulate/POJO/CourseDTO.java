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
@Table(name = "tbl_course_catalog")
public class CourseDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry")
    private int entry;
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "course_title")
    public String courseTitle;
    @Transient
    private String status;
    @Transient
    private String registeredStatus;
    @Transient
    private String grade;
    @Transient
    private boolean preReqCheck;
    @Transient
    private boolean preReqRegistered;
    @Transient
    private String preReqTerm;


}
