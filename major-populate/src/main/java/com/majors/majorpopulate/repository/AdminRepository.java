package com.majors.majorpopulate.repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AdminRepository {

<<<<<<< Updated upstream
/* 
potentially updates all fields for a section. Only updates passed in fields.
This should recieve a HashTable<string,string> where the key: field_name, value:field_value
*/
    public void UpdateSection(String courseTitle, String courseSection, String courseDays, String courseTerm,
            List<Date> courseTermDates, List<LocalTime> courseTime, String courseLocation, String courseBuildingNum,
            String courseRoomNum, String courseType, int seatsTaken, int seatsAvailable) {
=======
    public void updateSection(String courseTitle, String courseSection, String courseDays, String courseTerm,
            String courseTermDates, String courseTime, String courseLocation, String courseBuildingNum,
            String courseRoomNum, String courseType, String seatsTaken, String seatsAvailable) {
                
                Hashtable<String, String> hashUpdate = new Hashtable<>();
                hashUpdate.put("courseTitle", courseTitle);
                hashUpdate.put("courseSection", courseSection);
                hashUpdate.put("courseDays", courseDays);
                hashUpdate.put("courseTerm", courseTerm);
                hashUpdate.put("courseTermDates", courseTermDates);
                hashUpdate.put("courseTime", courseTime);
                hashUpdate.put("courseLocation", courseLocation);
                hashUpdate.put("courseBuildingNum", courseBuildingNum);
                hashUpdate.put("courseRoomNum", courseRoomNum);
                hashUpdate.put("courseType", courseType);
                hashUpdate.put("seatsTaken", seatsTaken);
                hashUpdate.put("seatsAvailable", seatsAvailable);
>>>>>>> Stashed changes

                //TODO
    }
/* 
Creates a new Student in tbl_student
*/
    public void CreateStudent(String studentName, String studentPassword, String majorName) {
        //TODO
    }
/* 
updates grades in tbl_student_transcript based on studentId and courseId. Does not update completion status of tbl_registered
*/
    public void UpdateGrades(int studentId, String courseId, String grade) {
        //TODO
    }
/* 
changes the major associated with a student Id and updates tbl_student
*/
    public void ChangeMajor(int studentId, String majorId) {
        //TODO
    }
/* 
Takes a studentId, the courseId and removes the course from tbl_registered and adds it to tbl_student_transcript, along with the Grade recieved.
*/
public void SetCourseToCompleted(int studentId, String courseId, String grade) {
        //TODO
}

}
