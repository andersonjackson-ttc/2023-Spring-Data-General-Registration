package com.majors.majorpopulate.POJO;

public class RegisteredSection {
    private String majorId;
    private String courseId;
    private String sectionId;
    private String term;


    public RegisteredSection() {
    }

    public RegisteredSection(String majorId, String courseId, String sectionId, String term) {
        this.majorId = majorId;
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.term = term;
    }


    public String getMajorId() {
        return this.majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSectionId() {
        return this.sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}
