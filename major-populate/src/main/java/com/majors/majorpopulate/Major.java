package com.majors.majorpopulate;

import lombok.*;
/* import java.util.List; */

@Getter
@Setter
public class Major {
    private String name;
    private String majorName;
    /* private String MajorId; */
    /* public List<Course> RequiredCourses; */
    /* public List<MajorElectiveGroup> MajorElectiveGroups; */

    public Major() {
    }

    public Major(String name, String majorName) {
        this.name = name;
        this.majorName = majorName;
    }

    /* public record MajorElectiveGroup(
        String ElectiveGroupName,
        String ElectiveGroupId,
        int NumRequired,
        List<Course> CoursesInElectiveGroup
    ){} */

    /* public record RequiredCourses(
        List<Course> RequiredCourses
    ){} */    
}
