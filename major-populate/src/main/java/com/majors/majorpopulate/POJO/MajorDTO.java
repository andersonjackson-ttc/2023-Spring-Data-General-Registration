package com.majors.majorpopulate.POJO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_majors")
public class MajorDTO {
    
    @Id
    @Column(name = "major_id")
    private String majorId;
    @Column(name = "major_name")
    private String majorName;
    

}
