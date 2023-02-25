package com.majors.majorpopulate;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



import java.util.ArrayList;
import java.util.List;



public class ConstantsAndStuff {
    
    //make single connection to SQL. SqlCaller Class.
    //@Service 
    public static SqlCaller sql = new SqlCaller();
    public static List<String> majorList;
    public static List<Login> loggedInUser = new ArrayList<>();

    public ConstantsAndStuff(){}
       
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

    //gets credentials from database to see if there is user
    //if a user "logs out" and someone else logs in it will replace who the user is. 
    public static String doesCredentialsMatch(String name, String password) {
        java.sql.Statement sqlSt; //runs sql
        
        ResultSet result; //holds the output from the sql
        
        String SQL = "SELECT count(id) FROM tbl_student where name = '" + name + "' and password = '" + password + "'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://127.0.0.1:3306/cpt275_db";
            Connection dbConnect = DriverManager.getConnection(dbURL, "root", "root");
            sqlSt = dbConnect.createStatement(); //allows SQL to be executed
            result = sqlSt.executeQuery(SQL);
            while(result.next() != false) { 
                if(result.getString("count(id)").equals("1")){
                    if (loggedInUser.size() == 1){
                        loggedInUser.set(0, new Login(name, password));
                    } else {
                        loggedInUser.add(new Login(name, password));
                    }
                }
             return result.getString("count(id)");
            }
            sqlSt.close();
            
        }catch(ClassNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not Found, Check the JAR");
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            
        }
        return "No Course Name";
       
    }

    //gets major from logged in user
    public static String getMajorNameFromLoggedInUser(String name, String password){
        java.sql.Statement sqlSt; //runs sql
        
        ResultSet result; //holds the output from the sql
        
        String SQL = "SELECT major_name FROM tbl_student where name = '" + name + "' and password = '" + password + "'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://127.0.0.1:3306/cpt275_db";
            Connection dbConnect = DriverManager.getConnection(dbURL, "root", "root");
            sqlSt = dbConnect.createStatement(); //allows SQL to be executed
            result = sqlSt.executeQuery(SQL);
            while(result.next() != false) {
             return result.getString("major_name");
            }
            sqlSt.close();
            
        }catch(ClassNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not Found, Check the JAR");
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            
        }
        return "No Course Name";
    }

    //gets the major ID from the name of major
    public static String getMajorIdFromName(String majorName){
        java.sql.Statement sqlSt; //runs sql
        
        ResultSet result; //holds the output from the sql
        
        String SQL = "SELECT major_id FROM tbl_majors where major_name = '" + majorName +"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://127.0.0.1:3306/cpt275_db";
            Connection dbConnect = DriverManager.getConnection(dbURL, "root", "root");
            sqlSt = dbConnect.createStatement(); //allows SQL to be executed
            result = sqlSt.executeQuery(SQL);
            while(result.next() != false) {
             return result.getString("major_id");
            }
            sqlSt.close();
            
        }catch(ClassNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not Found, Check the JAR");
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            
        }
        return "No Course Name";
    }


}  
       



