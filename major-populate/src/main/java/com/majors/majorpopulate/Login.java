package com.majors.majorpopulate;

public class Login {
    
    private String name;
    private String password;
    
    public Login() {
    }

    public Login(String name, String password) {
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

    public Login name(String name) {
        setName(name);
        return this;
    }

    public Login password(String password) {
        setPassword(password);
        return this;
    }
}
