package com.majors.majorpopulate;

import java.sql.Date;
import java.util.Objects;

public record EachClass (
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

                public EachClass{
                        Objects.requireNonNull(CourseTitle);
                        Objects.requireNonNull(CourseSection);
                        Objects.requireNonNull(CourseTerm);
                        Objects.requireNonNull(CourseTermDates);
                        Objects.requireNonNull(CourseLocation);
                        Objects.requireNonNull(CourseType);
                        
                }
        }