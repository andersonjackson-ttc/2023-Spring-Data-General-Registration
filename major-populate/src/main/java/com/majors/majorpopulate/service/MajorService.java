package com.majors.majorpopulate.service;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.MajorPopulateApplication;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.SqlCaller;
import com.majors.majorpopulate.student.Login;
import com.majors.majorpopulate.student.Student;

import java.util.ArrayList;
import java.util.List;



public class MajorService {
    
    //make single connection to SQL. SqlCaller Class.
    //@Service 
    public static SqlCaller sql = new SqlCaller();
    public static List<String> majorList;
    public static List<Login> loggedInUser = new ArrayList<>();

    public MajorService(){}
       
    //adds all the majors to a list to add to the dropdown Select option on form.html
    public static void populateMajorChoices() throws Exception{ 
        //List<Major> totalMajorList = new ArrayList<>();
        //totalMajorList = sql.getAllMajors();                  //This is the total major object builder.
        majorList = sql.ShowMajorNames();
    }

    //Gets required classes from SQLcaller Class
    // public static List<String> showRequiredCourses(Major major){
    //     Course course;
    //     List<String> requiredCourses = new ArrayList<>();
    //     foreach (course: major.getRequiredCourses()){
    //         requiredCourses.add(course.CourseName());
    //     }
    //     return requiredCourses;
    // }

    public static List<String> getRequiredCoursesFromSQLcaller(String majorId) throws Exception {
        List<String> coursesList = new ArrayList<>();
        coursesList = sql.getRequiredCoreClasses(majorId);
        return coursesList;
    }

    

    

    //adds the major requirements and course id to the mainpage.html
     public static Major showMajorRequirements(String MajorId) throws Exception{
            
             Major major = sql.GetMajorById(MajorId);
             return major;
                  
    }
    
      public static void CreateStudent(Student student){
        java.sql.Statement sqlSt; //runs sql

        String SQL = "INSERT tbl_student(name,password,major_name) VALUES('"+student.getName()+"',+'"+student.getPassword()+
                "','"+student.getMajor()+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://127.0.0.1:3306/cpt275_db";
            Connection dbConnect = DriverManager.getConnection(dbURL, "root", "root");
            sqlSt = dbConnect.createStatement(); //allows SQL to be executed
            sqlSt.execute(SQL);
            sqlSt.close();

        }catch(ClassNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not Found, Check the JAR");
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        }
    }

    //Calls database from SQLcaller to see if there is user
    //if a user "logs out" and someone else logs in it will replace who the user is. 
    public static String doesCredentialsMatch(String name, String password) throws Exception {
        boolean isThereAStudent = sql.matchCredentials(name, password);
        if (isThereAStudent == true){
            String majorName = sql.getMajorNameFromStudent(name, password);
            String majorId = sql.getMajorId(majorName);
            if (loggedInUser.size() == 1) { 
                loggedInUser.set(0, new Login(name, password, majorName, majorId)); return "1";
            } else {
                loggedInUser.add(new Login(name, password, majorName, majorId)); return "1";
            } 
        }   
        return "0";  
    }

  
    public static List<String> showCoursesByTerm(String term, Major major){
        List<String> courseList = new ArrayList<>();

        for (Course course: major.getRequiredCourses()) {
           for (Section eachClass : course.Classes()) {
                if (eachClass.CourseTerm() == term){
                    courseList.add(course.CourseName());
                }
           } 
        }
        return courseList;
    }
}  
       



