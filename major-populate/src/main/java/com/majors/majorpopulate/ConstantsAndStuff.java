package com.majors.majorpopulate;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConstantsAndStuff {

    // make single connection to SQL. SqlCaller Class.
    private SqlCaller sql;
    public List<String> majorList;
    public static List<MajorRequirements> majorRequirement = new ArrayList<>();
    public static List<MajorElectives> majorElectives = new ArrayList<>();

    public ConstantsAndStuff() {
        majorList = new ArrayList<>();
        sql = new SqlCaller();
    }

    // adds all the majors to a list to add to the dropdown Select option on
    // form.html
    public void populateMajorChoices() throws Exception {

        majorList = sql.ShowMajorNames();

    }

    // adds the major requirements and course id to the mainpage.html
    public void showMajorRequirements(String nameOfMajor) throws Exception {

        List<MajorRequirements> result = sql.ShowMajorRequirementSet();

        for (MajorRequirements majorRequirements : result) {

        }

        // majorElectives.add(new MajorElectives(result.getString("major_name"),
        // result.getString("elective_group"), result.getString("nbr_required")));
    }

    public int Login(Student student) {
        try {
            return sql.Login(student);
        } catch (Exception ex) {
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            return 0;
        }
    }

    public Student GetStudent(int idStudent) {
        try {
            return sql.GetStudent(idStudent);
        } catch (Exception ex) {
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            return null;
        }
    }

    public void CreateStudent(Student student) {
        try {
            sql.CreateStudent(student);
        } catch (Exception ex) {
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        }
        /*
         * java.sql.Statement sqlSt; //runs sql
         * 
         * String SQL =
         * "INSERT tbl_student(name,password,major_name) VALUES('"+student.getName()+
         * "',+'"+student.getPassword()+
         * "','"+student.getMajor()+"')";
         * try {
         * Class.forName("com.mysql.jdbc.Driver");
         * String dbURL = "jdbc:mysql://127.0.0.1:3306/cpt275_db";
         * Connection dbConnect = DriverManager.getConnection(dbURL, "root", "");
         * sqlSt = dbConnect.createStatement(); //allows SQL to be executed
         * sqlSt.execute(SQL);
         * sqlSt.close();
         * 
         * }catch(ClassNotFoundException ex) {
         * Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE,
         * null, ex);
         * System.out.println("Class not Found, Check the JAR");
         * }
         * catch (SQLException ex) {
         * Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE,
         * null, ex);
         * System.out.println("SQL IS BAD!!" + ex.getMessage());
         * 
         * }
         */
    }

    public void MajorRequirements(String nameOfMajor) {
        try {
            var result = sql.GetRequirements(nameOfMajor);
            majorRequirement = result.getMajorRequirement();
            majorElectives = result.getMajorElectives();
        } catch (Exception ex) {
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
    }

}
