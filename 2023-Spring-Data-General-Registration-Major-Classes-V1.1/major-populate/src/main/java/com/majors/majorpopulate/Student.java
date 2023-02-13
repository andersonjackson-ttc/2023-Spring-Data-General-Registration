package com.majors.majorpopulate;

import org.springframework.stereotype.Component;


public class Student {
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", major='" + major + '\'' +
                '}';
    }

    public Student()
    {}
    public Student(String name, String password, String major) {
        this.name = name;
        this.password = password;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    private String name;
    private String password;
    private String major;
}
