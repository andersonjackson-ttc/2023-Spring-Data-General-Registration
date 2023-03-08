package com.majors.majorpopulate;

import java.time.LocalTime;

import java.util.Date;
import java.util.List;

/* import jakarta.persistence.Id;
import jakarta.persistence.Entity;
 */

//@Entity
public record Section (
       // @Id
        String CourseTitle,
        String CourseSection,
        String CourseDays,
        String CourseTerm,
        Date[] CourseTermDates,
        Date[] CourseTime,
        String CourseLocation,
        String CourseBuildingNum,
        String CourseRoomNum,
        String CourseType,
        int CourseRegistered,
        int CourseAvailable) {

                public Section{
                        Objects.requireNonNull(CourseTitle);
                        Objects.requireNonNull(CourseSection);
                        Objects.requireNonNull(CourseTerm);
                        //Objects.requireNonNull(CourseTermDates);
                        Objects.requireNonNull(CourseLocation);
                        Objects.requireNonNull(CourseType);
                        
                }
        }