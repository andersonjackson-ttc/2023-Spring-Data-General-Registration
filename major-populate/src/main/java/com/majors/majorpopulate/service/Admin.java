/* package com.majors.majorpopulate.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import com.majors.majorpopulate.repository.AdminRepository;
import lombok.*;

@Getter
@Setter
public class Admin {

    private String student;
    AdminRepository adminRepo;

    public Admin(String student) {
        this.student = student;
        adminRepo = new AdminRepository();
    }

    public Admin() {
    }


    public void UpdateSection(String CourseTitle, String CourseSection,
            String CourseDays, String CourseTerm, List<Date> CourseTermDates,
            List<LocalTime> CourseTime, String CourseLocation, String CourseBuildingNum,
            String CourseRoomNum, String CourseType, int SeatsTaken, int SeatsAvailable) throws Exception {

        adminRepo.updateSection(CourseTitle, CourseSection,
                CourseDays, CourseTerm, CourseTermDates,
                CourseTime, CourseLocation, CourseBuildingNum,
                CourseRoomNum, CourseType, SeatsTaken, SeatsAvailable);
    }

    public void createStudent(String studentName, String studentPassword, String majorName) {
        adminRepo.createStudent(studentName, studentPassword, majorName);
    }

    public void updateGrades(int studentId, String courseId, String grade) {
        adminRepo.updateGrades(studentId, courseId, grade);
    }

    public void changeMajor(int studentId, String majorName) {
        adminRepo.changeMajor(studentId, majorName);
    }

 
    public void setCourseToCompleted(int studentId, String courseId, String grade){
        adminRepo.setCourseToCompleted(studentId,courseId,grade);
    }

}
 */