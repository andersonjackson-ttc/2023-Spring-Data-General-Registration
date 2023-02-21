package com.majors.majorpopulate;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.rsocket.server.ConfigurableRSocketServerFactory;

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
     public static void showMajorRequirements(String MajorId) throws Exception{
            
             Major major = sql.GetMajorById(MajorId);
                  
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


}  
       



