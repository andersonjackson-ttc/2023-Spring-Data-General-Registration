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

public class SqlCaller {

    private Statement sqlSt;
    private Course course;

    public SqlCaller() {
        try {
            Properties props = new Properties();
            String dbSettingsPropertiesFile = "major-populate\\src\\main\\resources\\application.properties";
            FileReader fr = new FileReader(dbSettingsPropertiesFile);
            props.load(fr);

            Class.forName(props.getProperty("spring.datasource.database"));
            Connection dbConnect = DriverManager.getConnection(props.getProperty("spring.datasource.url"),
                    props.getProperty("spring.datasource.username"), props.getProperty("spring.datasource.password"));
            sqlSt = dbConnect.createStatement();
            var t = 4;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not Found, Check the JAR");
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Properties File Failed to Load" + ex.getMessage());
        } catch (Exception exception) {
            var l = 4;
        }

    }

    public List<String> ShowMajorNames() throws Exception {
        List<String> majorList = new ArrayList<>();
        String SQL = "SELECT * FROM tbl_majors";
        try {
            ResultSet result = this.sqlSt.executeQuery(SQL);
            while (result.next() != false) {
                majorList.add(result.getString("major_name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return majorList;
    }

    public Major GetMajorById(String majorId) throws Exception {
        Major major = new Major();
        String query = String.format("SELECT major_name"
                + "FROM tbl_majors "
                + "WHERE major_id = %s", majorId);
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next() != false) {
            major.setMajorName(result.getString("major_name"));
            major.setMajorId(majorId);
        }
        return major;
    }

    public Course GetCourseById(String CourseId) throws Exception {

        String query = String.format("Select * "
                + "FROM tbl_courses_offered"
                + "WHERE substr(course_section,1, 7) = %s", CourseId);
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            course = new Course(
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
                    result.getInt("idk_seats_available"),
                    result.getInt("idk_seats_waitlist"));
        }
        return course;
    }

    public List<String> GetElectiveGroupsByMajor(String MajorId) throws Exception {
        List<Course> electiveGroupList = new ArrayList<>();
        String query = String.format("SELECT major_name, elective_group, nbr_required, elective_id " +
                "FROM cpt275_db.tbl_major_electives " +
                "where major_id = %s", MajorId);

        try {
            ResultSet result = sqlSt.executeQuery(query);
            while (result.next()) {
                // get electives in each elective group
                String electiveGroupId = result.getString("elective_id");
                GetElectivesByElectiveGroup(electiveGroupId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return null; // This was blank Just put null to get rid of error.
    }

    public void GetElectivesByElectiveGroup(String electiveGroupId) throws Exception {
        String query = String.format("select * from tbl_elective_courses where elective_id = %s", electiveGroupId);
        try {
            ResultSet result = sqlSt.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
    }

    // public List<Course> GetPreReqsByCourseId(String CourseId){}

    public List<MajorRequirements> ShowMajorRequirementSet() throws Exception {

        List<MajorRequirements> majorRequirements = new ArrayList<>();
        List<MajorElectives> majorElectives = new ArrayList<>();
        String SQLMajors = "select distinct g.major_id  'Major Id', " +
                "g.major_name as 'Major Name', " +
                "g.req_type as 'Requirment type', " +
                "g.course_id as 'Course ID', " +
                " a.course_title as 'Course Name'" +
                "from tbl_grad_requirement g " +
                "join (select distinct substr(c.course_section,1, 7) as course_id, c.course_title from tbl_courses_offered c) a "
                +
                "on trim(a.course_id) = trim(g.course_id)";

        try {
            ResultSet result = sqlSt.executeQuery(SQLMajors);

            while (result.next()) {
                majorRequirements
                        .add(new MajorRequirements(result.getString("Major Name"), result.getString("Requirment type"),
                                result.getString("Course ID"), result.getString("course_title")));
                majorElectives.add(new MajorElectives(result.getString("major_name"),
                        result.getString("elective_group"), result.getString("nbr_required")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return majorRequirements;
    }

    public void CreateStudent(Student student) throws Exception {

        try {
            String SQL = "INSERT tbl_student(name,password,major_name,passwordValidation) VALUES('" + student.getName()
                    + "',+'" + student.getPassword() +
                    "','" + student.getMajor() + "','" + student.getPasswordValidation() + "')";

            sqlSt.execute(SQL);
            sqlSt.close();

        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        }
    }

    public int Login(Student student) throws Exception {
        String sql = "select * from tbl_student where name= '" + student.getName() + "' and password ='"
                + student.getPassword() + "'";
        ResultSet result = sqlSt.executeQuery(sql);
        int value = 0;
        while (result.next()) {
            value = result.getInt(1);

        }
        if (value >= 1)
            return value;
        else
            return value;
    }

    public Student GetStudent(int id) throws Exception {
        String sql = "select * from tbl_student where student_id= '" + id + "'";
        ResultSet result = sqlSt.executeQuery(sql);
        Student student = new Student();
        while (result.next()) {
            student.setStudentId(result.getInt(1));
            student.setName(result.getString(2));
            student.setMajor(result.getString(4));

        }
        return student;
    }

    public RequirementsForMajor GetRequirements(String nameOfMajor) {
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
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        }
        return resultData;
    }
}
/*
 * String SQLMajors = "SELECT * FROM tbl_grad_requirement WHERE major_name = '"
 * + nameOfMajor + "'";
 * String SQLMajorElectives =
 * "SELECT * FROM tbl_major_electives WHERE major_name = '" + nameOfMajor + "'";
 */