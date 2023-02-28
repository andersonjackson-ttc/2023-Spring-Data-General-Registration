package com.majors.majorpopulate;

import java.util.Date;

public record Course(
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
}
