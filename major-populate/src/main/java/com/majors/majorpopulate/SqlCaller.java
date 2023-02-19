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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.majors.majorpopulate.Major.MajorElectiveGroup;

//@Component
public class SqlCaller {
    
    //@Autowired
    //SqlRepository sqlRepo
    Statement sqlSt;
    Connection dbConnect;
    private List<EachClass> classList;
    
    public SqlCaller(){
        try{
            Properties props = new Properties();
            String dbSettingsPropertiesFile = "major-populate\\src\\main\\resources\\application.properties";
            FileReader fr = new FileReader(dbSettingsPropertiesFile);
            props.load(fr);

            Class.forName(props.getProperty("spring.datasource.database"));
            dbConnect = DriverManager.getConnection(props.getProperty("spring.datasource.url"), 
            props.getProperty("spring.datasource.username"), props.getProperty("spring.datasource.password"));
            
            
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

    public List<String> ShowMajorNames() throws Exception{
        sqlSt = dbConnect.createStatement();
        List<String> majorList = new ArrayList<>();
        String SQL = "SELECT * FROM tbl_majors";
        try{
            ResultSet result = sqlSt.executeQuery(SQL);
            while(result.next() != false) {
                majorList.add(result.getString("major_name"));
            }         

        }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return majorList;
    }

    public Major GetMajorById(String majorId) throws Exception{
        sqlSt = dbConnect.createStatement();
        Major major = new Major();
        String query = String.format("SELECT major_name "
                                    +"FROM tbl_majors "
                                    +"WHERE major_id = %s", majorId);
        ResultSet result = sqlSt.executeQuery(query);
        while(result.next() != false) {
            major.setMajorName(result.getString("major_name"));
            major.setMajorId(majorId);
            major.setMajorElectiveGroups(GetElectiveGroupsByMajor(majorId));
        }        
       return major;                        
    }

    public List<EachClass> GetClassesByCourseId(String CourseId) throws Exception{
        sqlSt = dbConnect.createStatement();
        List<EachClass> classList = new ArrayList<>();
        String query = String.format("Select * "
                                    +"FROM tbl_courses_offered "
                                    +"WHERE substr(course_section,1, 7) = '%s'" , CourseId);
        ResultSet result = sqlSt.executeQuery(query);
        while(result.next()) {
          EachClass eachClass = new EachClass(
                result.getString("course_title"),
                result.getString("course_section"),
                result.getString("course_days"),
                result.getString("course_term"),
                null,
                null,
                result.getString("course_location"),
                result.getString("course_building_nbr"),
                result.getString("course_room"),
                result.getString("course_type"),
                result.getInt("idk_seats_avail"),
                result.getInt("idk_seats_waitlist"));
        classList.add(eachClass);
        }
        return classList;
    }
    public Course GetCourseById(String CourseId){
        sqlSt = dbConnect.createStatement();
        classList = GetClassesByCourseId(CourseId);
        prereqList = GetRequisites(CourseId);
        Course course = new Course(
            Classes: classList, 
            MajorName, 
            CourseName,
            CourseId, 
            prereqlist, 
            CoReqClassList);
        return course;
    }

    private List<MajorElectiveGroup> GetElectiveGroupsByMajor(String MajorId) throws Exception{
        sqlSt = dbConnect.createStatement();
        MajorElectiveGroup meg;
        List<MajorElectiveGroup> electiveGroupList =  new ArrayList<>();
        String query = String.format("SELECT * "+
                                        "FROM cpt275_db.tbl_major_electives " +
                                        "where major_id = %s", MajorId);

        try {
            ResultSet result = sqlSt.executeQuery(query);
            while (result.next()){
                //get electives in each elective group
                meg = new MajorElectiveGroup(
                    result.getString("major_id"), 
                    result.getString("major_name"), 
                    result.getString("elective_id"), 
                    result.getInt("nbr_required"),
                    GetElectivesByElectiveGroup(result.getString("elective_id"))
                    );
            
                electiveGroupList.add(meg);
            }
        }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        
        return electiveGroupList;
    }

    public List<Course> GetElectivesByElectiveGroup(String electiveGroupId)throws Exception{
        sqlSt = dbConnect.createStatement();
        List<Course> electiveCourses = new ArrayList<>();
        String query = String.format("SELECT course_id FROM tbl_elective_courses where elective_id = %s", electiveGroupId);
        try {
            ResultSet result = sqlSt.executeQuery(query);
            while(result.next()){
               List<Course> courses = GetCourseById(result.getString("course_id"));
               
            }
            
            
        }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return electiveCourses;
    }

    //public List<Course> GetPreReqsByCourseId(String CourseId){}

    public List<MajorRequirements> ShowMajorRequirementSet() throws Exception{
        sqlSt = dbConnect.createStatement();
        List<MajorRequirements> majorRequirements = new ArrayList<>();
        List<MajorElectives> majorElectives = new ArrayList<>();
        String SQLMajors = "select distinct g.major_id  'Major Id', "+
                                     "g.major_name as 'Major Name', "+
                                  "g.req_type as 'Requirment type', "+
                                      "g.course_id as 'Course ID', " +
                                  " a.course_title as 'Course Name'" +
                    "from tbl_grad_requirement g " +
                    "join (select distinct substr(c.course_section,1, 7) as course_id, c.course_title from tbl_courses_offered c) a " +
                        "on trim(a.course_id) = trim(g.course_id)";
        
        try{
            ResultSet result = sqlSt.executeQuery(SQLMajors);

            while(result.next()) {
            majorRequirements.add(new MajorRequirements(result.getString("Major Name"), result.getString("Requirment type"), result.getString("Course ID"),result.getString("course_title")));
            majorElectives.add(new MajorElectives(result.getString("major_name"), result.getString("elective_group"), result.getString("nbr_required")));
            }
            }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return majorRequirements;
    }
}
/* String SQLMajors = "SELECT * FROM tbl_grad_requirement WHERE major_name = '" + nameOfMajor + "'";
String SQLMajorElectives = "SELECT * FROM tbl_major_electives WHERE major_name = '" + nameOfMajor + "'"; */