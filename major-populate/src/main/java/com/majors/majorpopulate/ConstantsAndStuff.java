package com.majors.majorpopulate;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;



public class ConstantsAndStuff {
    
    //make single connection to SQL. SqlCaller Class.  
    public static SqlCaller sql = new SqlCaller();
    public static List<String> majorList;
    
    public ConstantsAndStuff(){}
       
    //adds all the majors to a list to add to the dropdown Select option on form.html
    public static void populateMajorChoices() throws Exception{ 
        majorList = new ArrayList<>();        
        
        majorList = sql.ShowMajorNames();
        
    }

    //adds the major requirements and course id to the mainpage.html
     public static void showMajorRequirements(String nameOfMajor) throws Exception{
            
        
             List<MajorRequirements> result = sql.ShowMajorRequirementSet();
            
             for (MajorRequirements majorRequirements : result) {
                
             }
               
                //majorElectives.add(new MajorElectives(result.getString("major_name"), result.getString("elective_group"), result.getString("nbr_required")));   
    }    
}  
       



