package com.majors.majorpopulate;

public class Major {

    private String majorName;
    private String id;
    
    public Major(String majorName, String id) {
        this.majorName = majorName;
        this.id = id;
    }

    public Major() {
    }

    public String getMajorName() {
        return this.majorName;
    }

    public void setMajorName(String name) {
        this.majorName = name;
    }

    public String getMajorId() {
        return this.id;
    }

    public void setMajorId(String id) {
        this.id = id;
    }

}