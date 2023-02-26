package com.majors.majorpopulate.student;

public class Login {
    
    private String name;
    private String password;
    private String majorName;
    private String majorID;
    
    public Login() {
    }

    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Login(String name, String password, String majorName, String majorID) {
        this.name = name;
        this.password = password;
        this.majorName = majorName;
        this.majorID = majorID;
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

    public String getMajorID() {
        return this.majorID;
    }

    public void setMajorID(String majorID) {
        this.majorID = majorID;
    }

    public String getMajorName() {
        return this.majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
