package com.majors.majorpopulate;

public class MajorElectives {
    private String majorName;
    private String electiveGroup; 
    private String numbRequired;


    public MajorElectives() {
    }

    public MajorElectives(String majorName, String electiveGroup, String numbRequired) {
        this.majorName = majorName;
        this.electiveGroup = electiveGroup;
        this.numbRequired = numbRequired;
    }

    public String getMajorName() {
        return this.majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getElectiveGroup() {
        return this.electiveGroup;
    }

    public void setElectiveGroup(String electiveGroup) {
        this.electiveGroup = electiveGroup;
    }

    public String getNumbRequired() {
        return this.numbRequired;
    }

    public void setNumbRequired(String numbRequired) {
        this.numbRequired = numbRequired;
    }

  
 
}
