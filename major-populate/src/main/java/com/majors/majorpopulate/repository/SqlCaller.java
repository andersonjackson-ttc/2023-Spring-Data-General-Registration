//This class will handle all of the calls to SQL.  eventually the url/username/password could be added to a settings file. Or taken as input and used for login purposes.
// Place all calls to Join tables to filter SQL results here.

package com.majors.majorpopulate.repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

import com.majors.majorpopulate.Course;
import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.MajorPopulateApplication;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.student.Student;
import com.majors.majorpopulate.Major.MajorElectiveGroup;

public class SqlCaller {
    
    //@Autowired
    //SqlRepository sqlRepo
    Statement sqlSt;
    Connection dbConnect;
    private List<Section> classList;
    
    public SqlCaller(){
        try{
            Properties props = new Properties();
            String dbSettingsPropertiesFile = "major-populate\\src\\main\\resources\\application.properties";
            FileReader fr = new FileReader(dbSettingsPropertiesFile);
            props.load(fr);

            Class.forName(props.getProperty("spring.datasource.database"));
            dbConnect = DriverManager.getConnection(props.getProperty("spring.datasource.url"), 
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
        }catch(IOException ex){
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("IOException" + ex.getMessage());
        }
        
    }

    public List<String> ShowMajorNames() throws Exception{
        List<String> majorList = new ArrayList<>();
        String SQL = "SELECT * FROM tbl_majors";
        try{
            ResultSet result = this.sqlSt.executeQuery(SQL);
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
        try {
            
            String query = String.format("SELECT * "
            +"FROM tbl_majors "
            +"WHERE major_id = %s", majorId);
            ResultSet result = sqlSt.executeQuery(query);
            while(result.next() != false) {
                major.setMajorName(result.getString("major_name"));
                major.setMajorId(majorId);
                major.setMajorId(result.getString("major_id"));
                major.setMajorElectiveGroups(GetElectiveGroupsByMajor(result.getString("major_id")));
                major.setRequiredCourses(GetRequiredCoursesByMajorId(result.getString("major_id")));
            }        
        } catch (Exception e) {
            // TODO: handle exception
        }
            return major;                        
    }

    public List<Course> GetRequiredCoursesByMajorId(String MajorId) throws Exception{
        List<Course> RequiredCourseList = new ArrayList<>();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT course_id FROM tbl_grad_requirement WHERE major_id = %s", MajorId);
        ResultSet result = sqlSt.executeQuery(query);
        while(result.next()){
            Course course = GetCourseById(result.getString("course_id"));
            RequiredCourseList.add(course);
        }
        return RequiredCourseList;
    }

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
                      result.getInt("total_seats"),
                      result.getInt("seats_taken"));
              classList.add(eachClass);
              }
              return classList;
    
        
    }
    public Course GetCourseById(String CourseId) throws Exception{
        Course course;
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
public List<Course> GetCoReqCoursesByCourseId(String CourseId) throws Exception{
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
public List<Course> GetPreReqCoursesByCourseId(String CourseId) throws Exception{
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
    public List<MajorElectiveGroup> GetElectiveGroupsByMajor(String MajorId) throws Exception{
        MajorElectiveGroup meg;
        List<MajorElectiveGroup> electiveGroupList =  new ArrayList<>();
        String query = String.format("SELECT * "+
        "FROM cpt275_db.tbl_major_electives electives "+
        "Join tbl_elective_groups eg "+
        "ON electives.elective_id = eg.elective_id "+
        "WHERE major_id = %s", MajorId);
        
        sqlSt = dbConnect.createStatement();
        try {
            ResultSet result = sqlSt.executeQuery(query);
            while (result.next()){
                //get electives in each elective group
                meg = new MajorElectiveGroup(
                    result.getString("elective_name"),
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
        String query = String.format("select * from tbl_elective_courses where elective_id = %s", electiveGroupId);
        try {
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

    public void CreateStudent(String name, String password, String major_name)throws Exception{
        sqlSt = dbConnect.createStatement(); //allows SQL to be executed
        String SQL = "INSERT tbl_student(name,password,major_name) VALUES('"+name+"',+'"+password+
                "','"+major_name+"')";
        try {
            sqlSt.execute(SQL);
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
    }

    /* public void BuildStudent(String name)throws Exception{
        sqlSt = dbConnect.createStatement();
        String SQL = string.format("Select * FROM tbl_student WHERE name = %s", name)
    } */
/* }
    public void CreateStudent(Student student) throws Exception{

        try {
            String SQL = "INSERT tbl_student(name,password,major_name,passwordValidation) VALUES('" + student.getName() + "',+'" + student.getPassword() +
                    "','" + student.getMajor() + "','" + student.getPasswordValidation() + "')";

            sqlSt.execute(SQL);
            sqlSt.close();

        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        }
    } */
    public int Login(Student student)throws Exception
    {
        String sql = "select * from tbl_student where name= '"+student.getName()+"' and password ='"+student.getPassword()+"'";
        ResultSet result = sqlSt.executeQuery(sql);
        int value = 0;
        while(result.next())
        {
             value = result.getInt(1);
        }
        if(value >= 1)
            return  value;
        else
            return  value;
    }
    /* public Student GetStudent(int id)throws Exception
    {
        String sql = "select * from tbl_student where student_id= '"+id+"'";
        ResultSet result = sqlSt.executeQuery(sql);
        Student student = new Student();
        while(result.next())
        {
            student.setStudentId( result.getInt(1));
            student.setName( result.getString(2));
            student.setMajor( result.getString(4));

        }
        return student;
    } */
    /* public RequirementsForMajor GetRequirements(String nameOfMajor)
    {
        RequirementsForMajor resultData = new RequirementsForMajor();
        ResultSet result;
        String SQLMajors = "SELECT * FROM tbl_grad_requirement WHERE major_name = '" + nameOfMajor + "'";
        String SQLMajorElectives = "SELECT * FROM tbl_major_electives WHERE major_name = '" + nameOfMajor + "'";
        try {
            result = sqlSt.executeQuery(SQLMajors);
            while (result.next() != false) {
                resultData.getMajorRequirement().add(new MajorRequirements(
                        result.getString("major_name"),
                        result.getString("req_type"),
                        result.getString("course_id"),
                        ""));
            }
            result = sqlSt.executeQuery(SQLMajorElectives);
            while (result.next() != false) {
                resultData.getMajorElectives().add(new MajorElectives(
                        result.getString("major_name"),
                        result.getString("elective_group"),
                        result.getString("nbr_required")));
            }

            sqlSt.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        }
        return resultData;
    } */
}
/* String SQLMajors = "SELECT * FROM tbl_grad_requirement WHERE major_name = '" + nameOfMajor + "'";
String SQLMajorElectives = "SELECT * FROM tbl_major_electives WHERE major_name = '" + nameOfMajor + "'"; */

