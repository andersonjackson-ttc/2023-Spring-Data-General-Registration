package com.majors.majorpopulate;

import java.util.List;

public class Major {

    private String name;
    private String majorName;
    private String MajorId;
    private List<MajorElectiveGroup> MajorElectiveGroups;
    
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