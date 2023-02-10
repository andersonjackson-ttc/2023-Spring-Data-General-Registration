package com.majors.majorpopulate;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class ConstantsAndStuff {

    public static List<String> majorList = new ArrayList<>();
    public static List<MajorRequirements> majorRequirement = new ArrayList<>();
    public static List<MajorElectives> majorElectives = new ArrayList<>();

    //adds all the majors to a list to add to the dropdown Select option on form.html
    public static void populateMajorChoices(){ 
        java.sql.Statement sqlSt; //runs sql
        
        ResultSet result; //holds the output from the sql
            String SQL = "SELECT * FROM tbl_majors";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://127.0.0.1:3306/cpt275_db";
            Connection dbConnect = DriverManager.getConnection(dbURL, "root", "");
            sqlSt = dbConnect.createStatement(); //allows SQL to be executed
            result = sqlSt.executeQuery(SQL);
            while(result.next() != false) {
                majorList.add(result.getString("major_name"));
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
    }

    //adds the major requirements and course id to the mainpage.html
    public static void showMajorRequirements(String nameOfMajor) {
        java.sql.Statement sqlSt; //runs sql
        
        ResultSet result; //holds the output from the sql
            String SQLMajors = "SELECT * FROM tbl_grad_requirement WHERE major_name = '" + nameOfMajor + "'";
            String SQLMajorElectives = "SELECT * FROM tbl_major_electives WHERE major_name = '" + nameOfMajor + "'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://127.0.0.1:3306/cpt275_db";
            Connection dbConnect = DriverManager.getConnection(dbURL, "root", "");
            sqlSt = dbConnect.createStatement(); //allows SQL to be executed
            result = sqlSt.executeQuery(SQLMajors);
            while(result.next() != false) {
               majorRequirement.add(new MajorRequirements(result.getString("major_name"), result.getString("req_type"), result.getString("course_id")));
            }

            sqlSt = dbConnect.createStatement(); //allows SQL to be executed
            result = sqlSt.executeQuery(SQLMajorElectives);
            while (result.next() != false) {
                majorElectives.add(new MajorElectives(result.getString("major_name"), result.getString("elective_group"), result.getString("nbr_required")));
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

       
    }

    public static void CreateStudent(Student student){
        java.sql.Statement sqlSt; //runs sql

        String SQL = "INSERT tbl_student(name,password,majorName) VALUES('"+student.getName()+"',+'"+student.getPassword()+
                "','"+student.getMajor()+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://127.0.0.1:3306/cpt275_db";
            Connection dbConnect = DriverManager.getConnection(dbURL, "root", "");
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

    
}


