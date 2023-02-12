package com.majors.majorpopulate;

public class Major {

    private String name;
    private String majorName;
    


    public Major(String name, String majorName) {
        this.name = name;
        this.majorName = majorName;
    }

    public Major() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajorName() {
        return this.majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    

    // @Override
    // public String toString() {
    //     return "Hello, " + this.name + "\n Your Major is " + this.majorName + "\n Here is your Required Classes";  
    // }
    
    

}