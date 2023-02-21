package com.majors.majorpopulate;

import java.util.List;

public class Major {

    public String name;
    public String majorName;
    public String MajorId;
    public List<MajorElectiveGroup> MajorElectiveGroups;
    public List<Course> requiredCourses;
    
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
        this.MajorId = id;
    }
    public List<MajorElectiveGroup> getMajorElectiveGroups(){
        return MajorElectiveGroups;
    }
    public void setMajorElectiveGroups(List<MajorElectiveGroup> majorElectiveGroups){
        this.MajorElectiveGroups =  majorElectiveGroups;
    }
    public List<Course> getRequiredCourses(){
        return requiredCourses;
    }

    public void setRequiredCourses(List<Course> requiredCourses){
        this.requiredCourses = requiredCourses;
    }
    
    public record MajorElectiveGroup(
        String MajorId,
        String MajorName,
        String ElectiveGroupId,
        int NumRequired,
        List<Course> CoursesInElectiveGroup
    ){}

    public record RequiredCourses(
        List<Course> RequiredCourses
    ){}

    
}