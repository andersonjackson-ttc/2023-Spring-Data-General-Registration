package com.majors.majorpopulate;

import java.time.LocalTime;

import java.util.Date;
import java.util.List;


//import jakarta.persistence.Entity;



public record Section (
        String CourseTitle,
        String CourseSection,
        String CourseDays,
        String CourseTerm,
        List<Date> CourseTermDates,
        List<LocalTime> CourseTime,
        String CourseLocation,
        String CourseBuildingNum,
        String CourseRoomNum,
        String CourseType,
        int SeatsTaken,
        int SeatsAvailable) {


        }