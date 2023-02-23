package com.majors.majorpopulate;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.rsocket.server.ConfigurableRSocketServerFactory;

import com.majors.majorpopulate.Major.MajorElectiveGroup;

import java.util.ArrayList;
import java.util.List;



public class ConstantsAndStuff {
    
    //make single connection to SQL. SqlCaller Class.
    //@Service 
    public static SqlCaller sql = new SqlCaller();
    public static List<String> majorList;
    
    public ConstantsAndStuff(){}
       
    //adds all the majors to a list to add to the dropdown Select option on form.html
    public static void populateMajorChoices() throws Exception{ 
        List<Major> majorList = new ArrayList<>();
        majorList = sql.getAllMajors();
        //majorList = sql.ShowMajorNames();
        System.out.println(majorList.get(0).getMajorName());
        
    }

    //adds the major requirements and course id to the mainpage.html
     public static List<MajorElectiveGroup> showMajorRequirements(String MajorId) throws Exception{
        List<MajorElectiveGroup> megs = new ArrayList<>();
             Major major = sql.GetMajorById(MajorId);
             
            megs = major.getMajorElectiveGroups();
            
            return megs;
                  
    }
    
    public static List<String> showRequiredCourses(Major major){
        Course course;
        List<String> requiredCourses = new ArrayList<>();
        foreach (course: major.getRequiredCourses()){
            requiredCourses.add(course.CourseName());
        }
        return requiredCourses;
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
                    List<Student> loggedInUser;
                    /* if (loggedInUser.size() == 1){
                        loggedInUser.set(0, new Login(name, password));
                    } else {
                        loggedInUser.add(new Login(name, password));
                    } */
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
    public static String getMajorNameFromLoggedInUser(String name, String password) throws Exception{
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
             
             Major major = sql.GetMajorById(result.getString("1001"));
             return major.getMajorName();
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
        
        String SQL = "SELECT major_id FROM tbl.majors where major_name = '" + majorName +"'";
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
       



