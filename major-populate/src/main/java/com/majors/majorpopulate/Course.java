package com.majors.majorpopulate;

import java.util.Date;

public class Course {
    
    private String Id;
    private String CourseTitle;
    private String CourseSection;
    private char[] CourseDays;
    private String CourseTerm;
    private Date[] CourseTermDates;
    private Date[] CourseTime;
    private String CourseLocation;
    private String CourseBuildingNum;
    private String CourseRoomNum;
    private String CourseType;
    private int CourseRegistered;
    private int CourseAvailable;
    
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getCourseTitle() {
        return CourseTitle;
    }
    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }
    public String getCourseSection() {
        return CourseSection;
    }
    public void setCourseSection(String courseSection) {
        CourseSection = courseSection;
    }
    public char[] getCourseDays() {
        return CourseDays;
    }
    public void setCourseDays(char[] courseDays) {
        CourseDays = courseDays;
    }
    public String getCourseTerm() {
        return CourseTerm;
    }
    public void setCourseTerm(String courseTerm) {
        CourseTerm = courseTerm;
    }
    public Date[] getCourseTermDates() {
        return CourseTermDates;
    }
    public void setCourseTermDates(Date[] courseTermDates) {
        CourseTermDates = courseTermDates;
    }
    public Date[] getCourseTime() {
        return CourseTime;
    }
    public void setCourseTime(Date[] courseTime) {
        CourseTime = courseTime;
    }
    public String getCourseLocation() {
        return CourseLocation;
    }
    public void setCourseLocation(String courseLocation) {
        CourseLocation = courseLocation;
    }
    public String getCourseBuildingNum() {
        return CourseBuildingNum;
    }
    public void setCourseBuildingNum(String courseBuildingNum) {
        CourseBuildingNum = courseBuildingNum;
    }
    public String getCourseRoomNum() {
        return CourseRoomNum;
    }
    public void setCourseRoomNum(String courseRoomNum) {
        CourseRoomNum = courseRoomNum;
    }
    public String getCourseType() {
        return CourseType;
    }
    public void setCourseType(String courseType) {
        CourseType = courseType;
    }
    public int getCourseRegistered() {
        return CourseRegistered;
    }
    public void setCourseRegistered(int courseRegistered) {
        CourseRegistered = courseRegistered;
    }
    public int getCourseAvailable() {
        return CourseAvailable;
    }
    public void setCourseAvailable(int courseAvailable) {
        CourseAvailable = courseAvailable;
    }
}
