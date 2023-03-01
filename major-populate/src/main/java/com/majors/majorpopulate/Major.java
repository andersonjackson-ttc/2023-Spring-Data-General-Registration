package com.majors.majorpopulate;

import java.util.List;

public class Major {

    private String name;
    private String majorName;
    private String MajorId;
    private List<MajorElectiveGroup> MajorElectiveGroups;
    private List<Course> requiredCourses;
    
    public Major(String name, String majorName) {
        this.name = name;
        this.majorName = majorName;
    }

    public Major(List<Course> requiredCourses){
        this.requiredCourses = requiredCourses; 
    }


    public Major(String name, String majorName, String MajorId, List<MajorElectiveGroup> MajorElectiveGroups, List<Course> requiredCourses) {
        this.name = name;
        this.majorName = majorName;
        this.MajorId = MajorId;
        this.MajorElectiveGroups = MajorElectiveGroups;
        this.requiredCourses = requiredCourses;
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