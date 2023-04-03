package com.majors.majorpopulate.POJO;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_student_transcript")
public class Grade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "term_id")
    private String termId;
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "course_grade")
    private String courseGrade;
    @Column(name = "course_status")
    private String courseStatus;
}
