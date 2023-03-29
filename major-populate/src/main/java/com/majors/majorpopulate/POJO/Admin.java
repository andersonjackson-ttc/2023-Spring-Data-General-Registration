package com.majors.majorpopulate.POJO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tbl_admin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id", nullable = false) 
    int adminId;
    @Column(name = "user_name", nullable = false) 
    String userName;
    @Column(name = "password", nullable = false) 
    String password;
    @Column(name = "role", nullable = true) 
    String role;
    
    public void CreateSection(){}
    public void UpdateSection(){}
    public void CreateStudent(String studentName, String studentPassword, String majorName){}
    public void UpdateGrades(String studentId, String courseId, String grade){}
    public void ChangeMajor(String studentId, String majorName){}
    public Admin get(int id) {
        return null;
    }
}
