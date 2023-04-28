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
@Table(name = "tbl_co_req")
public class CoReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;
    @Column(name = "course_id_c1")
    private String courseId;
    @Column(name = "course_id_c2")
    private String co_req;
}
