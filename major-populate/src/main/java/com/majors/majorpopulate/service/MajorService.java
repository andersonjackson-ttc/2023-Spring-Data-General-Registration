package com.majors.majorpopulate.service;

//import com.majors.majorpopulate.Course;
//import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.POJO.CourseOffers;
import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.Section;
//import com.majors.majorpopulate.Major.MajorElectiveGroup;
// import com.majors.majorpopulate.POJO.RegisteredSection;
import com.majors.majorpopulate.repository.SqlCaller;
import com.majors.majorpopulate.student.Login;
import com.majors.majorpopulate.student.Student;

//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MajorService {
    
    public static SqlCaller sql = new SqlCaller();

    public static List<Login> loggedInUser = new ArrayList<>();

    public static ArrayList<String> termListOrder = new ArrayList<String>(
        Arrays.asList("2023 Spring Full",
            "2023 Spring 1", "2023 Spring 2", "2023 Summer Full", "2023 Summer 1",
            "2023 Summer 2", "2023 Fall Full", "2023 Fall 1", "2023 Fall 2"));

    public MajorService() {

    }
   
    // Enters new user login credentials into the sql-db
    public static void CreateStudent(Student student) throws Exception {
        sql.CreateStudent(student.getName(), student.getPassword(), student.getMajor());
    }

    // Calls database from SQLcaller to see if there is user
    // if a user "logs out" and someone else logs in it will replace who the user
    // is.
    public static String validateLogin(String name, String password) throws Exception {
        boolean isThereAStudent = sql.matchCredentials(name, password);
        if (isThereAStudent == true) {
            String majorName = sql.getMajorNameFromStudent(name, password);
            String majorId = sql.getMajorId(majorName);
            if (loggedInUser.size() == 1) {
                loggedInUser.set(0, new Login(name, password, majorName, majorId));
                return "1";
            } else {
                loggedInUser.add(new Login(name, password, majorName, majorId));
                return "1";
            }
        }
        return "0";
    }

    // gets sections times and info for the selected course from mainpage.html
    public static List<Section> getSectionTimesByCourseName(String name, String term) throws Exception {
        List<Section> section;
        if (term == null || term == "") {
            section = sql.getSectionTimesByCourseName(name);
        } else {
            String[] termList = {"2023 Spring Full", "2023 Spring 1", "2023 Spring 2", "2023 Summer Full", "2023 Summer 1", "2023 Summer 2", "2023 Fall Full", "2023 Fall 1", "2023 Fall 2"};
            String termListToPass = "";
            for (int i = 0; i < termList.length; i++) {
                if (!term.equals(termList[i])){
                    continue;
                } else if(term.equals(termList[i])){
                    for (int j = i + 1; j < termList.length; j++) {
                        termListToPass += "'" + termList[j] + "', ";
                    }
                    break;
                }
            }
            if(!termListToPass.equals("")){
                termListToPass = termListToPass.substring(0, termListToPass.length() - 2);
            }else termListToPass = null;
            System.out.println(termListToPass);
            section = sql.getCourseNameByTerm(name, termListToPass);
        }
        return section;
    }

    public static List<String> getTerm() throws Exception {
        List<String> terms = sql.getTerm();
        return terms;
    }

    // calls the SqlCaller to add the students registered coruse to their schedule
    public static void createRegisteredSection(String sectionId, String courseId, String term) throws Exception {
        int studentId = getStudentId();
        sql.createRegisteredSection(studentId, loggedInUser.get(0).getMajorID(), courseId, sectionId, term);
    }

    /*
     * takes the last 4 characters off the section name to pass to SqlCaller
     */
    public static String parseCourseId(String courseId) {
        String[] split = courseId.split("-");
        String parsedCourseId = String.format("%s-%s", split[0], split[1]);
        return parsedCourseId;
    }
   
    public static int getStudentId() throws Exception {
        int studentId = sql.getStudentId(loggedInUser.get(0).getName(), loggedInUser.get(0).getPassword());
        return studentId;
    }

    // removes a registered section time for the student.
    public static void deleteSection(String courseId) throws Exception {
        sql.deleteRegisteredSection(getStudentId(), courseId);
    }

    // Gets list of students from database for admin
    public static List<Student> getStudentClasses(String studentName) throws Exception {
        List<Student> studentList;
        studentList = sql.getStudentListByName(studentName);
        return studentList;
    }

    /*
     * By: John Percival
     * returns search courses
     */
    // Gets list of Course from database for admin
    public static List<CourseOffers> getCourses(String nameCourse) throws Exception {
        List<CourseOffers> courseOffers;
        courseOffers = sql.getCourses(nameCourse);
        return courseOffers;
    }

    public static CourseOffers getCoursesById(int id) throws Exception {
        CourseOffers courseOffer;
        courseOffer = sql.getCoursesById(id);
        return courseOffer;
    }

    public static Student getStudentById(int id) throws Exception {
        Student student;
        student = sql.getStudentById(id);
        return student;
    }

    /*
     * Removes a section from a students schedule FROM the admin side
     */
    public static void adminDeleteSection(int studentId, String courseId) throws Exception {
        sql.deleteRegisteredSection(studentId, courseId);
    }


    public static void updateCourse(CourseOffers course) throws Exception {
        sql.updateCourse(course);
    }

    public static void updateStudent(Student student) throws Exception {
        sql.updateStudent(student);

    }

    public static Grade getEng100(Grade eng100, int studentId) {
        eng100.setCourseId("ENG-100");
        eng100.setCourseGrade("A");
        eng100.setCourseStatus("Complete");
        eng100.setStudentId(studentId);
        eng100.setTermId("2023 Spring 1");
        return eng100;
    }

    public static Grade getRwr100(Grade rwr100, int studentId) {
        rwr100.setCourseId("RWR-100");
        rwr100.setCourseGrade("A");
        rwr100.setCourseStatus("Complete");
        rwr100.setStudentId(studentId);
        rwr100.setTermId("2023 Spring 1");
        return rwr100;
    }

    public static Grade getMat033(Grade mat033, int studentId) {
        mat033.setCourseId("MAT-033");
        mat033.setCourseGrade("A");
        mat033.setCourseStatus("Complete");
        mat033.setStudentId(studentId);
        mat033.setTermId("2023 Spring 1");
        return mat033;
    }
}
// Calls getMajorById from sqlCaller and populates the logged in "users" major
    // in the controller
    /* public static Major getMajorById(String majorId) throws Exception {
        Major major = sql.GetMajorById(majorId);
        return major;
    } */

    /* //
    public static List<String> showCoursesByTerm(String term, Major major) {
        List<String> courseList = new ArrayList<>();

        for (Course course : major.RequiredCourses) {
            for (Section eachClass : course.getClasses()) {
                if (eachClass.CourseTerm() == term) {
                    courseList.add(course.getCourseName());
                }
            }
        }
        return courseList;
    } */
    
    /*
     * Gets registered sections from a student id for the admin to edit/add grades/remove 
     */
    // public static List<RegisteredSection> getRegisteredSections(int studentId) throws Exception{
    //     List<RegisteredSection> registeredSections = new ArrayList<>();
    //     registeredSections = sql.getRegisteredSections(studentId);
    //     return registeredSections;
    // }
      /*
     * Curtis
     * sets course grade and status
     */
    /* public static void getCourseStatusForStudent(int student_id, Major major) throws SQLException {
        for (Course course : major.RequiredCourses) {
            if (course != null) {
                var courseStatus = sql.getCourseStatus(student_id, course.getCourseId());
                course.setStatus(courseStatus);
                var courseGrade = sql.getGrade(student_id, course.getCourseId());
                course.setGrade(courseGrade);
            } else {
                continue;
            }
        }
        for (MajorElectiveGroup meg : major.MajorElectiveGroups) {
            for (Course course : meg.CoursesInElectiveGroup()) {
                if (course != null) {
                    var courseStatus = sql.getCourseStatus(student_id, course.getCourseId());
                    course.setStatus(courseStatus);
                    var courseGrade = sql.getGrade(student_id, course.getCourseId());
                    course.setGrade(courseGrade);
                } else {
                    continue;
                }
            }
        }
    }  */
     /*
     * gets registered Sections from SqlCaller
     */
    // public static List<RegisteredSection> getRegisteredSections() throws Exception {
    //     List<RegisteredSection> registeredSections = new ArrayList<>();
    //     registeredSections = sql.getRegisteredSections(
    //             sql.getStudentId(loggedInUser.get(0).getName(), loggedInUser.get(0).getPassword()));
    //     return registeredSections;
    // }
    /* // Gets required classes from SQLcaller Class
    public static List<String> showRequiredCourses(String majorId) throws Exception {
        List<String> coursesList = new ArrayList<>();
        coursesList = sql.getRequiredCoreClasses(majorId);
        return coursesList;
    } */