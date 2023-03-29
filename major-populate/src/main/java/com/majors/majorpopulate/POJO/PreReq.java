package com.majors.majorpopulate.POJO;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_pre_reqs")
public class PreReq {
    
    @Column
    private String course_id;
    @Column
    private String pre_req;
    @Column
    private String min_grade;

    @ManyToMany
    private List<Courses> Courses;
}
