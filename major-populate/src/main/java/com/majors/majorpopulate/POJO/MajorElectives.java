package com.majors.majorpopulate.POJO;

import org.springframework.data.annotation.Transient;

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
@Table(name = "tbl_major_electives")
public class MajorElectives {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entry;
    @Column(name = "major_id")
    private int majorId;
    @Column(name = "major_name")
    private String majorName;
    @Column(name ="elective_id")
    private int electiveGroupId;
    @Column(name = "elective_group")
    private String electiveGroupName;
    @Column (name = "nbr_required")
    private int numRequired;
    @Transient
    public int numCompleted;
    @Transient
    public int numberRegistered;
}
