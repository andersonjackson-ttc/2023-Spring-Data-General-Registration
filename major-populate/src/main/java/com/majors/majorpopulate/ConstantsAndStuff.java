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

    //make single connection to SQL. SqlCaller Class.  
    public static SqlCaller sql = new SqlCaller();
    public static ResultSet result;
    public static Statement sqlSt;

    
    public ConstantsAndStuff(){}
       
    //adds all the majors to a list to add to the dropdown Select option on form.html
    public static void populateMajorChoices(){ 
        try {
            result = sql.GetMajors();
            while(result.next() != false) {
                majorList.add(result.getString("major_name"));
            }         
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
    }

    //adds the major requirements and course id to the mainpage.html
    public static void showMajorRequirements(String nameOfMajor) {
            
        try {
            result = sql.ShowMajorRequirementSet();
            
            while(result.next()) {
               majorRequirement.add(new MajorRequirements(result.getString("Major Name"), result.getString("Requirment type"), result.getString("Course ID"),result.getString("Course Title")));
                //majorElectives.add(new MajorElectives(result.getString("major_name"), result.getString("elective_group"), result.getString("nbr_required")));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }  
    }   
}


