package com.majors.majorpopulate;

public class MajorRequirements {
    private String name;
    private String reqType;
    private String courseId;
    private String courseName;


    public MajorRequirements(String name, String reqType, String courseId, String courseName) {
        this.name = name;
        this.reqType = reqType;
        this.courseId = courseId;
        this.courseName = courseName;
    }


    public MajorRequirements() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReqType() {
        return this.reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseName() {
        return courseName;
    }

}
