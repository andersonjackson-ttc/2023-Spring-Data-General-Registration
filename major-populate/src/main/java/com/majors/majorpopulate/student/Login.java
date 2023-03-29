package com.majors.majorpopulate.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    
    private String name;
    private String password;
    private String majorName;
    private String majorID;

    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
