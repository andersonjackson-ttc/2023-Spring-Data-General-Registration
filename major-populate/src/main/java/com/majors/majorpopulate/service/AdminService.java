package com.majors.majorpopulate.service;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.majors.majorpopulate.repository.AdminRepository;
import lombok.*;

@Getter
@Setter
public class AdminService {

    private String student;
    private String course;
    AdminRepository adminRepo;

    public AdminService(String student) {
        this.student = student;
        adminRepo = new AdminRepository();
    }

    public AdminService() {
    }

    /*
     * should recieve the data either as a hashtable from the form or recieve the
     * data and repackage it here as a hashtable, to send it
     * to the adminRepo. Not sure which will work better.
     */
    public void updateSection(String CourseTitle, String CourseSection,
            String CourseDays, String CourseTerm, List<Date> CourseTermDates,
            List<LocalTime> CourseTime, String CourseLocation, String CourseBuildingNum,
            String CourseRoomNum, String CourseType, int SeatsTaken, int SeatsAvailable) throws Exception {

        /* adminRepo.updateSection(CourseTitle, CourseSection,
                CourseDays, CourseTerm, CourseTermDates,
                CourseTime, CourseLocation, CourseBuildingNum,
                CourseRoomNum, CourseType, SeatsTaken, SeatsAvailable); */
    }

    public void adminAddStudent(String studentName, String studentPassword, String majorName) throws Exception {
        adminRepo.adminAddStudent(studentName, studentPassword, majorName);
    }

    public void updateGrades(int studentId, String courseId, String grade) throws Exception {
        adminRepo.updateGrades(studentId, courseId, grade);
    }

    public void changeMajor(int studentId, String majorName) throws Exception {
        adminRepo.changeMajor(studentId, majorName);
    }

    /*
     * Admin Service method to set a students course from in progress to completed,
     * with grade
     */
    public void setCourseToCompleted(int studentId, String courseId, String grade) throws Exception {
        adminRepo.setCourseToCompleted(studentId, courseId);
    }

}
