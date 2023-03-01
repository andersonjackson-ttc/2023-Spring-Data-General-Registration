package com.majors.majorpopulate;

public class Major {
    private String name;
    private String majorName;
    private String MajorId;
    
    public Major(String majorName) {
        this.majorName = majorName;
    }
    
    public Major() {
    }
    
    public String getMajorName() {
        return this.majorName;
    }
    
    public void setMajorName(String name) {
        this.majorName = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajorId() {
        return this.MajorId;
    }

    public void setMajorId(String id) {
        this.MajorId = MajorId;
    }

}