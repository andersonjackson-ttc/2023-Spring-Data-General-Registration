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

<<<<<<< Updated upstream:major-populate/src/main/java/com/majors/majorpopulate/SqlCaller.java
=======
import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.MajorPopulateApplication;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.student.Student;
>>>>>>> Stashed changes:major-populate/src/main/java/com/majors/majorpopulate/repository/SqlCaller.java
import com.majors.majorpopulate.Major.MajorElectiveGroup;
import com.majors.majorpopulate.student.Student;

//@Component
public class SqlCaller {
    
   
    Statement sqlSt;
    Connection dbConnect;
    
    
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
    public List<Major> getAllMajors()throws Exception{
        sqlSt = dbConnect.createStatement();
        List<Major> majorList = new ArrayList<>();
        String SQL = "SELECT major_id FROM tbl_majors";
        try{
            ResultSet result = sqlSt.executeQuery(SQL);
            while(result.next() != false) {
                Major major = GetMajorById(result.getString("major_id"));
                majorList.add(major);
            }         

        }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        
        return majorList;
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

    public Major GetMajorById(String majorid) throws Exception{
        sqlSt = dbConnect.createStatement();
        Major major = new Major();
        String query = String.format("SELECT * FROM tbl_majors WHERE major_id = '%s'", majorid);
        ResultSet result = sqlSt.executeQuery(query);
        while(result.next() != false) {
            major.setMajorName(result.getString("major_name"));
            major.setMajorId(result.getString("major_id"));
            major.setMajorElectiveGroups(GetElectiveGroupsByMajor(result.getString("major_id")));
            major.setRequiredCourses(GetRequiredCoursesByMajorId(result.getString("major_id")));
        }        
       return major;                        
    }

    private List<Course> GetRequiredCoursesByMajorId(String MajorId) throws Exception{
        List<Course> RequiredCourseList = new ArrayList<>();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT course_id FROM tbl_grad_requirement WHERE major_id = %s",MajorId);
        ResultSet result = sqlSt.executeQuery(query);
        while(result.next()){
            Course course = GetCourseById(result.getString("course_id"));
            RequiredCourseList.add(course);
        }
        return RequiredCourseList;
    }

<<<<<<< Updated upstream:major-populate/src/main/java/com/majors/majorpopulate/SqlCaller.java
=======
    /*
     * ADDED BY STEPHEN 
     * make sure credentails for login match a registered student in database
     */
     public boolean matchCredentials(String name, String password) throws Exception{
        boolean studentInDatabase = false;
        sqlSt = dbConnect.createStatement();
        String query = String.format( "SELECT count(id) FROM tbl_student where name = '" + name + "' and password = '" + password + "'");
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next() != false){
            if (result.getString("count(id)").equals("1")) {
                studentInDatabase = true;
            }
        }
        return studentInDatabase;
    }

     /*  
     * ADDED BY STEPHEN 
     * gets majorID from first getting major name from user if users credentials match. 
     */
     public String getMajorNameFromStudent(String name, String password) throws Exception{
        sqlSt = dbConnect.createStatement();
        String query = "SELECT major_name FROM tbl_student where name = '" + name + "' and password = '" + password + "'";
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next() != false){
         return result.getString("major_name");
        }
        return "could not find major name";
     }

      /*
      * STEPHEN 
      * A CONTINUATION of getting the major id from the name _ FROM user trying to log in
      */
     public String getMajorId(String majorName) throws Exception{
        sqlSt = dbConnect.createStatement();
        String query = "SELECT major_id FROM tbl_majors where major_name = '" + majorName +"'";
        ResultSet result = sqlSt.executeQuery(query);
        while(result.next() != false) {
            return result.getString("major_id");
        }
        return "could not find major ID";
     }
 
    /*
     * ADDED BY STEPHEN 
     * Gets required "Core Sections" (classes) just there to populate the main page with something
     */
    public List<String> getRequiredCoreClasses(String majorId) throws Exception{
        List<String> requiredCoreCourses = new ArrayList<>();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT course_id FROM tbl_grad_requirement WHERE major_id = %s", majorId);
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            requiredCoreCourses.add(result.getString("course_id"));
        }
        return requiredCoreCourses;
    }
    /*
     * END OF STEPHENS ADDITIONS
     */

>>>>>>> Stashed changes:major-populate/src/main/java/com/majors/majorpopulate/repository/SqlCaller.java
    public List<Section> GetClassesByCourseId(String CourseId) throws Exception{
        sqlSt = dbConnect.createStatement();
        List<Section> classList = new ArrayList<>();
        String query = String.format("Select * "
                                    +"FROM tbl_courses_offered "
                                    +"WHERE substr(course_section,1, 7) = '%s'" , CourseId.trim());
        ResultSet result = sqlSt.executeQuery(query);
            while(result.next()) {
                Section eachClass = new Section(
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

    public Course GetCourseById(String CourseId) throws Exception{
        Course course;
        List<Section> classList;
        sqlSt = dbConnect.createStatement();
        try{
        classList = GetClassesByCourseId(CourseId);
        course = new Course(
            classList, 
            classList.get(0).CourseTitle(),
            CourseId, 
            GetPreReqCoursesByCourseId(CourseId), 
            GetCoReqCoursesByCourseId(CourseId));
        }catch (Exception ex){
            return null;
        }
        return course;
    }

private List<Course> GetCoReqCoursesByCourseId(String CourseId) throws Exception{
    List<Course> coReqCourseList = new ArrayList<>();
    sqlSt = dbConnect.createStatement();
    String query = String.format("SELECT course_id_c2 FROM tbl_co_req WHERE course_id_c1 = '%s'",CourseId);
    ResultSet result = sqlSt.executeQuery(query);
    if(!result.next()){
        return coReqCourseList;
    }
    while(result.next()){
        Course course = GetCourseById(result.getString("course_id_c2"));
        coReqCourseList.add(course);
    }
    return coReqCourseList;
}
private List<Course> GetPreReqCoursesByCourseId(String CourseId) throws Exception{
    List<Course> preReqCourseList = new ArrayList<>();
    sqlSt = dbConnect.createStatement();
    String query = String.format("SELECT prereq FROM tbl_pre_reqs WHERE course_id = '%s'",CourseId.trim());
    ResultSet result = sqlSt.executeQuery(query);
    if(!result.next()){
        return preReqCourseList;
    }
    while(result.next()){
        Course course = GetCourseById(result.getString("prereq"));
        preReqCourseList.add(course);
    }
    return preReqCourseList;
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
        List<Course> electiveCourses = new ArrayList<>();
        String query = String.format("SELECT course_id FROM tbl_elective_courses where elective_id = '%s'", electiveGroupId);
        try {
            sqlSt = dbConnect.createStatement();
            ResultSet result = sqlSt.executeQuery(query);
            if(!result.next()){
                return electiveCourses;
            }
            while(result.next()){
               Course course = GetCourseById(result.getString("course_id"));
               electiveCourses.add(course);
            }
            
            
        }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return electiveCourses;
    }

<<<<<<< Updated upstream:major-populate/src/main/java/com/majors/majorpopulate/SqlCaller.java

}
=======
    public void CreateStudent(String name, String password, Major major)throws Exception{
        sqlSt = dbConnect.createStatement(); //allows SQL to be executed
        String SQL = "INSERT tbl_student(name,password,major_name) VALUES('"+name+"',+'"+password+
                "','"+major.getMajorName()+"')";
        try {
            sqlSt.execute(SQL);
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
    }

<<<<<<< Updated upstream:major-populate/src/main/java/com/majors/majorpopulate/SqlCaller.java
    /* public void GetStudent(String name)throws Exception{
        sqlSt = dbConnect.createStatement();
        String SQL = string.format("Select * FROM tbl_student WHERE name = %s", name)
    } */

    public void updateCourseRegistered(Student student, Course course)throws Exception{
        sqlSt = dbConnect.createStatement();
        String SQL = String.format("INSERT INTO tbl_registration (student_id, major_id, course_id, reg_dts)"+
                    "VALUES (%s, %s, %s, %s)",student.getName(),student.getMajorId(),course.CourseId(), null);
        try {
            sqlSt.execute(SQL);
        } catch (Exception ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        } 
    }
}
>>>>>>> Stashed changes:major-populate/src/main/java/com/majors/majorpopulate/repository/SqlCaller.java
=======
    public Student GetStudent(String name)throws Exception{
        Student student = new Student();
        sqlSt = dbConnect.createStatement();
        String SQL = String.format("Select * FROM tbl_student WHERE name = '%s'", name);
        try {
            
            ResultSet result = sqlSt.executeQuery(SQL);
            while(result.next()){
               student = new Student(
                result.getString("name"),
               result.getString("password"),
               null,
               result.getString("major_name")); 
            } 
        }catch(SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return student;
    }
}
>>>>>>> Stashed changes:major-populate/src/main/java/com/majors/majorpopulate/repository/SqlCaller.java
