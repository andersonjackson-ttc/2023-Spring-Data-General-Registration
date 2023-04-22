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
@Table(name = "tbl_pre_reqs")
public class PreReq {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "prereq")
    private String pre_req;
    @Column(name = "min_grade")
    private String min_grade;
    @Transient
    private String courseTitle;

}
