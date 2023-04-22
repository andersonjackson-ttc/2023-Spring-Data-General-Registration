package com.majors.majorpopulate.POJO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_grad_requirement")
public class GradRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;
    @Column(name = "major_id")
    private int majorId;
    @Column(name = "major_name")
    private String majorName;
    @Column(name = "req_type")
    private String reqType;
    @Column(name = "course_id")
    public String courseId;
    
}
