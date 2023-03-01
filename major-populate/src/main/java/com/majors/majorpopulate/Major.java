package com.majors.majorpopulate;

import java.util.List;

public class Major {
    private String name;
    private String majorName;
    private String MajorId;
    public List<Course> RequiredCourses;
    public List<MajorElectiveGroup> MajorElectiveGroups;

    public Major() {
    }

    public Major(String name2, String majorName2) {
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
        this.MajorId = id;
    }
    public List<MajorElectiveGroup> getMajorElectiveGroups(){
        return MajorElectiveGroups;
    }
    public void setMajorElectiveGroups(List<MajorElectiveGroup> majorElectiveGroups){
        this.MajorElectiveGroups =  majorElectiveGroups;
    }
    public List<Course> getRequiredCourses(){
        return RequiredCourses;
    }
    public void setRequiredCourses(List<Course> requiredCourses){
        this.RequiredCourses = requiredCourses;
    }

    public record MajorElectiveGroup(
        String ElectiveGroupName,
        String ElectiveGroupId,
        int NumRequired,
        List<Course> CoursesInElectiveGroup
    ){}

    public record RequiredCourses(
        List<Course> RequiredCourses
    ){}



    

    
}
