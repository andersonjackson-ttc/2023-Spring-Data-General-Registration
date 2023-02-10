//This class will handle all of the calls to SQL.  eventually the url/username/password could be added to a settings file. Or taken as input and used for login purposes.
// Place all calls to Join tables to filter SQL results here.

package com.majors.majorpopulate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlCaller {
    
    Statement sqlSt;
    ResultSet result;
    
    public SqlCaller(){
        try{
            Properties props = new Properties();
            String dbSettingsPropertiesFile = "major-populate\\src\\main\\resources\\application.properties";
            FileReader fr = new FileReader(dbSettingsPropertiesFile);
            props.load(fr);

            Class.forName(props.getProperty("spring.datasource.database"));
            Connection dbConnect = DriverManager.getConnection(props.getProperty("spring.datasource.url"), 
            props.getProperty("spring.datasource.username"), props.getProperty("spring.datasource.password"));
            sqlSt = dbConnect.createStatement();
            
        }catch(ClassNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not Found, Check the JAR");
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        }catch (FileNotFoundException ex){
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Properties File Failed to Load" + ex.getMessage());
        }catch (Exception exception) {}
        
    }


    public ResultSet GetMajors(){
        String SQL = "SELECT * FROM tbl_majors";
        try{
            result = sqlSt.executeQuery(SQL);
        }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
        return result;
    }
    public ResultSet ShowMajorRequirementSet(){
        String SQLMajors = "select distinct g.major_id  'Major Id', g.major_name as 'Major Name', "+
        "g.req_type as 'Requirment type', g.course_id as 'Course ID' " +
        ", a.course_title " +
"from tbl_grad_requirement g " +
"join (select distinct substr(c.course_section,1, 7) as course_id, c.course_title from tbl_courses_offered c) a " +
"on trim(a.course_id) = trim(g.course_id)";
        
        try{
            result = sqlSt.executeQuery(SQLMajors);
        }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
        return result;
    }
}
/* String SQLMajors = "SELECT * FROM tbl_grad_requirement WHERE major_name = '" + nameOfMajor + "'";
String SQLMajorElectives = "SELECT * FROM tbl_major_electives WHERE major_name = '" + nameOfMajor + "'"; */