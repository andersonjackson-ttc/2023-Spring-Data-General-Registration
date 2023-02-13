package com.majors.majorpopulate;

public class MajorRequirements {
    private String name;
    private String reqType;
    private String courseId;


    public MajorRequirements(String name, String reqType, String courseId) {
        this.name = name;
        this.reqType = reqType;
        this.courseId = courseId;
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

}
