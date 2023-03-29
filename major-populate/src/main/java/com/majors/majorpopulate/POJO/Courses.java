package com.majors.majorpopulate.POJO;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "tbl_courses")
public class Courses {
    
    @Id @Column 
    private String course_id;
    @Column
    private String course_name;

    @ManyToMany
    private List<PreReq> pre_reqs;
    @ManyToMany
    private List<CoReq> co_reqs;
}
