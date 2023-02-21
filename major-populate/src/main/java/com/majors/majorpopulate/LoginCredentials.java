package com.majors.majorpopulate;

public class LoginCredentials {
    private String name;
    private String password;


    public LoginCredentials() {
    }

    public LoginCredentials(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
