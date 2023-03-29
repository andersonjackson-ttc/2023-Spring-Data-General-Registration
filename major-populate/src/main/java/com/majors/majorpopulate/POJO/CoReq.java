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
@Table(name = "tbl_co_reqs")
public class CoReq {
    
    @Column
    private String course_id;
    @Column
    private String co_req;

    @ManyToMany
    private List<Courses> Courses;
}
