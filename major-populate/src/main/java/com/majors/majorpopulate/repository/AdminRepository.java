package com.majors.majorpopulate.repository;
import java.util.Hashtable;
import java.util.Map;

public class AdminRepository {

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

                for (Map.Entry<String, String> hash : hashUpdate.entrySet())
                    System.out.println(hash.getKey() + " " + hash.getValue());
                
    }
/*Creates a new Student in tbl_student */

    public void adminAddStudent(String studentName, String studentPword, String majorName) throws Exception {
        SqlCaller sql = new SqlCaller();
        sql.adminNewStudent(studentName, studentPword, majorName);
    }

/* updates grades in tbl_student_transcript based on studentId and courseId. Does not update completion status of tbl_registered */

    public void updateGrades(int studentId, String courseId, String grade) throws Exception {
        SqlCaller sql = new SqlCaller();
        sql.updateGrades(studentId, courseId, grade);
    }
 
/*changes the major associated with a student Id and updates tbl_student, finds the major name based on the major ID provided */

    public void changeMajor(int studentId, String majorId) throws Exception {
        SqlCaller sql = new SqlCaller();
        sql.changeMajor(studentId, majorId);
    }

/* Takes a studentId, the courseId and removes the course from tbl_registered and adds it to tbl_student_transcript, along with the Grade recieved. */
    public void setCourseToCompleted(int studentId, String updatedCourseStatus) throws Exception {
        SqlCaller sql = new SqlCaller();
        sql.setCourseToComplete(studentId, updatedCourseStatus);
    }

}
