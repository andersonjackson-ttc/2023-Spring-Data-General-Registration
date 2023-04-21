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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.majors.majorpopulate.Course;
//import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.MajorPopulateApplication;
import com.majors.majorpopulate.POJO.CourseOffers;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.student.Student;
//import com.majors.majorpopulate.Major.MajorElectiveGroup;
// import com.majors.majorpopulate.POJO.RegisteredSection;

public class SqlCaller {

    Statement sqlSt;
    Connection dbConnect;

    public SqlCaller() {
        try {
            Properties props = new Properties();
            String dbSettingsPropertiesFile = "major-populate\\src\\main\\resources\\application.properties";
            FileReader fr = new FileReader(dbSettingsPropertiesFile);
            props.load(fr);

            Class.forName(props.getProperty("spring.datasource.database"));
            dbConnect = DriverManager.getConnection(props.getProperty("spring.datasource.url"),
                    props.getProperty("spring.datasource.username"), props.getProperty("spring.datasource.password"));
            sqlSt = dbConnect.createStatement();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not Found, Check the JAR");
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Properties File Failed to Load" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("IOException" + ex.getMessage());
        }

    }

    /*
     * ADDED BY STEPHEN
     * make sure credentails for login match a registered student in database
     */
    public boolean matchCredentials(String name, String password) throws Exception {
        boolean studentInDatabase = false;
        sqlSt = dbConnect.createStatement();
        String query = String.format(
                "SELECT count(id) FROM tbl_student where name = '" + name + "' and password = '" + password + "'");
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next() != false) {
            if (result.getString("count(id)").equals("1")) {
                studentInDatabase = true;
            }
        }
        return studentInDatabase;
    }

    /*
     * ADDED BY STEPHEN
     * gets majorID from first getting major name from user if users credentials
     * match.
     */
    public String getMajorNameFromStudent(String name, String password) throws Exception {
        sqlSt = dbConnect.createStatement();
        String query = "SELECT major_name FROM tbl_student where name = '" + name + "' and password = '" + password
                + "'";
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next() != false) {
            return result.getString("major_name");
        }
        return "could not find major name";
    }

    /*
     * STEPHEN
     * A CONTINUATION of getting the major id from the name _ FROM user trying to
     * log in
     */
    public String getMajorId(String majorName) throws Exception {
        sqlSt = dbConnect.createStatement();
        String query = "SELECT major_id FROM tbl_majors where major_name = '" + majorName + "'";
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next() != false) {
            return result.getString("major_id");
        }
        return "could not find major ID";
    }

    /* 
     * ADDED BY STEPHEN
     * Gets required "Core Sections" (classes) just there to populate the main page
     * with something
     */
    /* public List<String> getRequiredCoreClasses(String majorId) throws Exception {
        List<String> requiredCoreCourses = new ArrayList<>();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT course_id FROM tbl_grad_requirement WHERE major_id = %s", majorId);
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            requiredCoreCourses.add(result.getString("course_id"));
        }
        return requiredCoreCourses;
    } */

    /*
     * By: John Percival
     * returns search courses
     */
    public List<CourseOffers> getCourses(String nameCourse) throws SQLException {
        List<CourseOffers> courses = new ArrayList<>();
        sqlSt = dbConnect.createStatement();
        String query = "SELECT * FROM tbl_courses_offered where course_title LIKE '%" + nameCourse + "%'";
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            CourseOffers courseOffer = new CourseOffers();
            courseOffer.setEntry(result.getInt("entry"));
            courseOffer.setTitle(
                    result.getString("course_title").equals("") ? "Not Available" : result.getString("course_title"));
            courseOffer.setSection(result.getString("course_section").equals("") ? "Not Available"
                    : result.getString("course_section"));
            courseOffer.setDays(
                    result.getString("course_days").equals("") ? "To Choose" : result.getString("course_days"));
            courseOffer.setTerm(
                    result.getString("course_term").equals("") ? "Not Available" : result.getString("course_term"));
            courseOffer.setTermDate(result.getString("course_term_dates").equals("") ? "Not Available"
                    : result.getString("course_term_dates"));
            courseOffer.setTime(
                    result.getString("course_time").equals("") ? "TBD" : result.getString("course_time"));
            courseOffer.setLocation(result.getString("course_location").equals("") ? "Not Available"
                    : result.getString("course_location"));
            courseOffer.setBuilding(result.getString("course_building_nbr").equals("") ? "No Apply"
                    : result.getString("course_building_nbr"));
            courseOffer.setRoom(
                    result.getString("course_room").equals("") ? "No Apply" : result.getString("course_room"));
            courseOffer.setType(
                    result.getString("course_type").equals("") ? "Not Available" : result.getString("course_type"));
            courses.add(courseOffer);
        }

        return courses;
    }

    public CourseOffers getCoursesById(int id) throws SQLException {
        CourseOffers course = new CourseOffers();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT * FROM tbl_courses_offered where entry = '" + id + "'");
        ResultSet result = sqlSt.executeQuery(query);

        while (result.next()) {
            course.setEntry(result.getInt("entry"));
            course.setTitle(
                    result.getString("course_title").equals("") ? "no Available" : result.getString("course_title"));
            course.setSection(result.getString("course_section").equals("") ? "no Available"
                    : result.getString("course_section"));
            course.setDays(
                    result.getString("course_days").equals("") ? "To Choose" : result.getString("course_days"));
            course.setTerm(
                    result.getString("course_term").equals("") ? "no Available" : result.getString("course_term"));
            course.setTermDate(result.getString("course_term_dates").equals("") ? "no Available"
                    : result.getString("course_term_dates"));
            course.setTime(
                    result.getString("course_time").equals("") ? "To Choose" : result.getString("course_time"));
            course.setLocation(result.getString("course_location").equals("") ? "no Available"
                    : result.getString("course_location"));
            course.setBuilding(result.getString("course_building_nbr").equals("") ? "No Apply"
                    : result.getString("course_building_nbr"));
            course.setRoom(
                    result.getString("course_room").equals("") ? "No Apply" : result.getString("course_room"));
            course.setType(
                    result.getString("course_type").equals("") ? "no Available" : result.getString("course_type"));
        }

        return course;
    }

    public Student getStudentById(int id) throws SQLException {
        Student student = new Student();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT * FROM tbl_student where Id = '" + id + "'");
        ResultSet result = sqlSt.executeQuery(query);

        while (result.next()) {
            student.setStudentId(result.getInt("id"));
            student.setName(result.getString("name").equals("") ? "no Available" : result.getString("name"));
            student.setPassword(
                    result.getString("password").equals("") ? "no Available" : result.getString("password"));
            student.setMajor(
                    result.getString("major_name").equals("") ? "no Available" : result.getString("major_name"));
        }
        return student;
    }

    public void updateCourse(CourseOffers course) throws SQLException {
        sqlSt = dbConnect.createStatement(); // allows SQL to be executed
        String SQL = "update tbl_courses_offered set course_title= '" + course.getTitle() + "',course_section = '"
                + course.getSection() + "',course_days= '" + course.getDays() + "',course_term= '" + course.getTerm()
                + "',course_term_dates= '" + course.getTermDate() + "',course_time= '" + course.getTime()
                + "',course_location= '" + course.getLocation() + "',course_building_nbr= '" + course.getBuilding()
                + "',course_room= '" + course.getRoom() + "',course_type= '" + course.getType() + "' where entry = '"
                + course.getEntry() + "'";
        try {
            sqlSt.execute(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
    }

    public void updateStudent(Student student) throws SQLException {
        sqlSt = dbConnect.createStatement(); // allows SQL to be executed
        String SQL = "update tbl_student set name= '" + student.getName() + "',major_name = '" + student.getMajor()
                + "',password= '" + student.getPassword() + "' where  Id = '" + student.getStudentId() + "'";
        try {
           sqlSt.execute(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
    }



    /* 
     * 
     */
    public void CreateStudent(String name, String password, String major_name) throws Exception {
        sqlSt = dbConnect.createStatement(); // allows SQL to be executed
        String SQL = "INSERT tbl_student(name,password,major_name) VALUES('" + name + "',+'" + password +
                "','" + major_name + "')";
        try {
            sqlSt.execute(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
    }

    /*
     * ADDED by STEPHEN
     * gets sections information from database from course button on mainpage.html
     */

    ///// duplicate method for getSectionByCourseId////////
    public List<Section> getSectionTimesByCourseName(String courseName) throws Exception {
        sqlSt = dbConnect.createStatement();
        List<Section> classList = new ArrayList<>();
        String query = "";

        query = String.format("Select * "
                + "FROM tbl_courses_offered "
                + "WHERE course_title = '%s'", courseName);

        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            Section eachClass = new Section(
                    result.getString("course_title"),
                    result.getString("course_section"),
                    result.getString("course_days"),
                    result.getString("course_term"),
                    parseDates(result),
                    parseTimes(result),
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

    /* 
     * 
     */
    public List<Section> getCourseNameByTerm(String courseName, String term) throws Exception {
        sqlSt = dbConnect.createStatement();
        List<Section> classList = new ArrayList<>();
        String query = "";

        query = String.format("Select * "
                + "FROM tbl_courses_offered "
                + "WHERE course_title = '%s' and course_term IN (%s)", courseName, term);

        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            Section eachClass = new Section(
                    result.getString("course_title"),
                    result.getString("course_section"),
                    result.getString("course_days"),
                    result.getString("course_term"),
                    parseDates(result),
                    parseTimes(result),
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

    /* 
     * 
     */
    public List<String> getTerm() throws Exception {
        List<String> termList = new ArrayList<>();
        String query = "select  DISTINCT course_term from tbl_courses_offered";
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            termList.add(result.getString(1));
        }
        return termList;
    }

    /*
     * Curtis
     * takes the course_term_dates from ResultSet
     * parses it into 2 seperate Dates, course start date
     * and course end date. into a List<Date>
     */
    private List<Date> parseDates(ResultSet result) throws Exception {
        List<Date> dates = new ArrayList<>();
        String dateString = result.getString("course_term_dates");
        if (dateString.isEmpty()) {
            return dates;
        }
        String[] splitDates = dateString.split("-");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        for (String d : splitDates) {
            var date = format.parse(d.trim());
            dates.add(date);
        }
        return dates;
    }

    /*
     * Curtis
     * takes the course_time from ResultSet
     * parses it into 2 seperate LocalTimes, start time
     * and end time. into a List<LocalTime>
     */
    private List<LocalTime> parseTimes(ResultSet result) throws Exception {
        List<LocalTime> times = new ArrayList<>();
        String timeString = result.getString("course_time");
        if (timeString.isEmpty()) {
            return times;
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String[] splitTimes = timeString.split("-");
        for (String t : splitTimes) {
            LocalTime time = LocalTime.parse((t).trim(), timeFormatter);
            times.add(time);
        }
        return times;
    }

    public int getStudentId(String name, String password) throws Exception {
        sqlSt = dbConnect.createStatement();
        String query = "SELECT id FROM tbl_student WHERE name = '" + name + "' AND password = '" + password + "'";
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            return result.getInt("id");
        }
        return -1000;
    }

    /*
     * Creates a entry for the registered class for a student
     */
    public void createRegisteredSection(int studentId, String majorId, String courseId, String sectionId, String term)
            throws Exception {
        sqlSt = dbConnect.createStatement();
        String query = "INSERT tbl_registration(student_id,major_id,course_id,section_id,term,reg_dts) VALUES("
                + studentId + ",'" + majorId + "','" + courseId + "','" + sectionId + "', '" + term + "', " + null
                + ")";
        sqlSt.execute(query);
    }

    /*
     * // Removes a selected registered section from the schedule page.
     */
    public void deleteRegisteredSection(int studentId, String courseId) throws Exception {
        sqlSt = dbConnect.createStatement();
        String query = "DELETE FROM tbl_registration WHERE student_id = " + studentId + " AND course_id = '" + courseId
                + "'";
        sqlSt.execute(query);
    }

    /* 
     * 
     */
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

    /*
     * stephen
     * Grabs List of Students from database for admin with general search useing
     * LIKE %search entry%
     */
    public List<Student> getStudentListByName(String studentName) throws Exception {
        sqlSt = dbConnect.createStatement();
        String query = "SELECT * FROM tbl_student WHERE name LIKE '%" + studentName + "%'";
        List<Student> sl = new ArrayList<>();
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            Student studentList = new Student(
                    result.getInt("id"),
                    result.getString("major_name"),
                    result.getString("name"),
                    result.getString("password"));
            sl.add(studentList);
        }
        return sl;
    }
       /*Josh allows admin to add a new student */

       public void adminNewStudent(String studentName, String StudentPword, String majorName) throws Exception {
        sqlSt = dbConnect.createStatement(); // allows SQL to be executed
        String SQL = "INSERT tbl_student(name,password,major_name) VALUES('" + studentName + "',+'" + StudentPword +
                "','" + majorName + "')";
        try {
            sqlSt.execute(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
        }
    }

    /* Josh Update a grade in student transcript table, finding student based on student ID and course ID */

    public void updateGrades(int studentId, String courseId, String newGrade) throws Exception {
        sqlSt = dbConnect.createStatement(); // allows SQL to be executed
        String SQL = String.format("UPDATE tbl_student_transcripts SET course_grade = '%s' WHERE student_id = '%d', course_id = '%s'", newGrade,studentId,courseId);
        try {
            sqlSt.execute(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("UpdateGrade Failed!!" + ex.getMessage());
        }
    }

     /*Josh, changes a student major in the student table based on student ID and the new Major ID */
     
    public void changeMajor(int studentId, String majorId) throws Exception {
        String majorName = getMajorId(majorId);
        sqlSt = dbConnect.createStatement(); // allows SQL to be executed
        String SQL = String.format("UPDATE tbl_student SET major_name = '%s' WHERE id = '%d'", majorName, studentId);
        try {
            sqlSt.execute(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Major Change Failed!!" + ex.getMessage());
        }
    }

    /*Josh change student transcript to complete  */

    public void setCourseToComplete(int studentId, String updatedCourseStatus) throws Exception {
        sqlSt = dbConnect.createStatement(); // allows SQL to be executed
        String SQL = String.format("UPDATE tbl_student_transcript SET course_status = '%s' WHERE student_id = '%d'", updatedCourseStatus, studentId);
        try {
            sqlSt.execute(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Completion Change Failed!!" + ex.getMessage());
        }
    }

}

/*
     * curtis
     * returns the grade for the student's course from 'tbl_student_transcript'
     */
    /* public String getGrade(int student_id, String courseId) throws SQLException {
        String grade = "";
        sqlSt = dbConnect.createStatement();
        String query = String.format(
                "SELECT course_grade FROM tbl_student_transcript WHERE course_id = '%s' AND student_id = '%d'",
                courseId, student_id);
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            grade = result.getString("course_grade");
        }
        return grade;
    } */
     /* 
     * 
     */
    // public List<RegisteredSection> getRegisteredSections(int studentId) throws Exception {
    //     sqlSt = dbConnect.createStatement();
    //     List<RegisteredSection> rs = new ArrayList<>();
    //     String query = "SELECT * FROM cpt275_db.tbl_registration WHERE student_id = " + studentId + "";
    //     ResultSet result = sqlSt.executeQuery(query);
    //     while (result.next()) {
    //         RegisteredSection eachSection = new RegisteredSection(
    //                 result.getString("major_id"),
    //                 result.getString("course_id"),
    //                 result.getString("section_id"),
    //                 result.getString("term"));
    //         rs.add(eachSection);
    //     }
    //     return rs;
    // }
    
    /*
     * By:Curtis
     * returns the course status, When course is built, calls to 'tbl_registered'
     * and 'tbl_student_transcript'
     * returns status based on which table the course is/isn't listed in.
     */
    /* public String getCourseStatus(int studentId, String CourseId) throws SQLException {
        Boolean courseStatus;
        String status;
        courseStatus = checkForCourseRegistered(studentId, CourseId.trim());
        if (courseStatus) {
            status = "Registered";
        } else {
            courseStatus = checkForCourseTranscipt(CourseId.trim(), studentId);
            if (courseStatus) {
                status = "Completed";
            } else {
                status = "Not Registered";
            }
        }
        return status;
    } */

    /*
     * curtis
     * checks tbl_registration for entries and returns whether there are or not.
     */
    /* private Boolean checkForCourseRegistered(int student_id, String courseId) throws SQLException {
        Boolean transcript;
        sqlSt = dbConnect.createStatement();

        String query = String.format("SELECT * FROM tbl_registration WHERE course_id = '%s' AND student_id = '%d'",
                courseId.trim(), student_id);
        ResultSet result = sqlSt.executeQuery(query);
        if (!result.next()) {
            transcript = false;
        } else {
            transcript = true;
        }
        return transcript;
    } */

    /*
     * curtis
     * checks tbl_student_transcript for classes and returns true or false.
     */
    /* private Boolean checkForCourseTranscipt(String courseId, int student_id) throws SQLException {
        Boolean transcript;
        sqlSt = dbConnect.createStatement();
        String query = String.format(
                "SELECT * FROM tbl_student_transcript WHERE course_id = '%s' AND student_id = '%d'", courseId.trim(),
                student_id);
        ResultSet result = sqlSt.executeQuery(query);
        if (!result.next()) {
            transcript = false;
        } else {
            transcript = true;
        }
        return transcript;
    } */

    /*
     * curtis
     * returns a list of courses that are Co-requisites for it's sibling course.
     */
    /* public List<Course> GetCoReqCoursesByCourseId(String CourseId) throws Exception {
        List<Course> coReqCourseList = new ArrayList<>();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT course_id_c2 FROM tbl_co_req WHERE course_id_c1 = '%s'", CourseId.trim());
        ResultSet result = sqlSt.executeQuery(query);
        if (!result.next()) {
            return coReqCourseList;
        }
        while (result.next()) {
            Course course = GetCourseById(result.getString("course_id_c2"));
            coReqCourseList.add(course);
        }
        return coReqCourseList;
    } */

    /*
     * curtis
     * returns a list of course that are Pre-Requisites for it's parent course
     */
    /* public List<Course> GetPreReqCoursesByCourseId(String CourseId) throws Exception {
        List<Course> preReqCourseList = new ArrayList<>();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT prereq FROM tbl_pre_reqs WHERE course_id = '%s'", CourseId.trim());
        ResultSet result = sqlSt.executeQuery(query);
        if (!result.next()) {
            return preReqCourseList;
        }
        while (result.next()) {
            Course course = GetCourseById(result.getString("prereq"));
            preReqCourseList.add(course);
        }
        return preReqCourseList;
    } */

    /*
     * curtis
     * returns a list of MajorElectiveGroups that are available for it's Parent
     * Major.
     * tells use the elective group name, elective_group_id, Number of courses
     * required for the major,
     * and a list of courses in each elective group
     */
    /* public List<MajorElectiveGroup> GetElectiveGroupsByMajor(String MajorId) throws Exception {
        MajorElectiveGroup meg;
        List<MajorElectiveGroup> electiveGroupList = new ArrayList<>();
        String query = String.format("SELECT * " +
                "FROM cpt275_db.tbl_major_electives electives " +
                "Join tbl_elective_groups eg " +
                "ON electives.elective_id = eg.elective_id " +
                "WHERE major_id = %s", MajorId.trim());

        sqlSt = dbConnect.createStatement();
        try {
            ResultSet result = sqlSt.executeQuery(query);
            while (result.next()) {
                // get electives in each elective group
                meg = new MajorElectiveGroup(
                        result.getString("elective_name"),
                        result.getString("elective_id"),
                        result.getInt("nbr_required"),
                        GetElectivesByElectiveGroup(result.getString("elective_id")));

                electiveGroupList.add(meg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }

        return electiveGroupList;
        /*
         * curtis
         * returns the list of courses in parent ElectiveGroup
         */
        /* public List<Course> GetElectivesByElectiveGroup(String electiveGroupId) throws Exception {
            sqlSt = dbConnect.createStatement();
            List<Course> electiveCourses = new ArrayList<>();
            String query = String.format("select * from tbl_elective_courses where elective_id = %s", electiveGroupId);
            try {
                ResultSet result = sqlSt.executeQuery(query);
                if (!result.next()) {
                    return electiveCourses;
                }
                while (result.next()) {
                    Course course = GetCourseById(result.getString("course_id").trim());
                    electiveCourses.add(course);
                }
    
            } catch (SQLException ex) {
                Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("SQL IS BAD!!" + ex.getMessage());
                throw new SQLException(ex);
            }
            return electiveCourses;
        } */
        /*
     * curtis
     * builds a course, with all of it's Sections and requisite courses, based on
     * the course_id
     */
    /* public Course GetCourseById(String CourseId) throws Exception {
        Course course;
        sqlSt = dbConnect.createStatement();
        try {
            course = new Course() {
            };
            course.setClasses(GetSectionByCourseId(CourseId));
            course.setCourseName(getCourseNameById(CourseId));
            course.setCourseId(CourseId);
            course.setCoRequisites(GetCoReqCoursesByCourseId(CourseId));
            course.setPreRequisites(GetPreReqCoursesByCourseId(CourseId));
        } catch (Exception ex) {
            return null;
        }
        return course;
    } */

    /*
     * By: Curtis
     * returns the courseName by it's course_id and sets default value if null
     */
    /* public String getCourseNameById(String CourseId) throws SQLException {
        String courseName = "";
        sqlSt = dbConnect.createStatement();
        String query = String.format(
                "SELECT course_title FROM cpt275_db.tbl_courses_offered c where substr(c.course_section,1, 7)  = '%s'",
                CourseId.trim());
        ResultSet result = sqlSt.executeQuery(query);
        if (result.next()) {
            courseName = result.getString("course_title");
        } else {
            courseName = "Course Unavailable";
        }

        return courseName;
    } */
    /*
     * curtis
     * returns a list of All Sections available for a given course.
     */
    /* public List<Section> GetSectionByCourseId(String CourseId) throws Exception {
        sqlSt = dbConnect.createStatement();
        List<Section> classList = new ArrayList<>();
        String query = String.format("Select * "
                + "FROM tbl_courses_offered "
                + "WHERE substr(course_section,1, 7) = '%s'", CourseId.trim());
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            Section eachClass = new Section(
                    result.getString("course_title"),
                    result.getString("course_section"),
                    result.getString("course_days"),
                    result.getString("course_term"),
                    parseDates(result),
                    parseTimes(result),
                    result.getString("course_location"),
                    result.getString("course_building_nbr"),
                    result.getString("course_room"),
                    result.getString("course_type"),
                    result.getInt("total_seats"),
                    result.getInt("seats_taken"));
            classList.add(eachClass);
        }
        return classList;

    } */
    /*
     * curtis
     * returns a list of all major names
     */
    /* public List<String> ShowMajorNames() throws Exception {
        sqlSt = dbConnect.createStatement();
        List<String> majorList = new ArrayList<>();
        String SQL = "SELECT * FROM tbl_majors";
        try {
            ResultSet result = sqlSt.executeQuery(SQL);
            while (result.next() != false) {
                majorList.add(result.getString("major_name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL IS BAD!!" + ex.getMessage());
            throw new SQLException(ex);
        }
        return majorList;
    } */

    /*
     * curtis
     * builds Major, with all courses and sections offered
     */
    /* public Major GetMajorById(String majorId) throws Exception {
        Major major = new Major();
        sqlSt = dbConnect.createStatement();
        try {
            String query = String.format("SELECT * "
                    + "FROM tbl_majors "
                    + "WHERE major_id = %s", majorId);
            ResultSet result = sqlSt.executeQuery(query);
            while (result.next() != false) {
                major.setMajorName(result.getString("major_name"));
                major.setMajorId(majorId);
                major.setMajorId(result.getString("major_id"));
                major.setMajorElectiveGroups(GetElectiveGroupsByMajor(result.getString("major_id")));
                major.setRequiredCourses(GetRequiredCoursesByMajorId(result.getString("major_id")));
            }
        } catch (Exception e) {
            Logger.getLogger(MajorPopulateApplication.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("SQL IS BAD!!" + e.getMessage());
            throw new SQLException(e);
        }
        return major;
    } */

    /*
     * curtis
     * returns a list of courses required for the major, based on the major_id
     */
    /* public List<Course> GetRequiredCoursesByMajorId(String MajorId) throws Exception {
        List<Course> RequiredCourseList = new ArrayList<>();
        sqlSt = dbConnect.createStatement();
        String query = String.format("SELECT course_id FROM tbl_grad_requirement WHERE major_id = %s", MajorId);
        ResultSet result = sqlSt.executeQuery(query);
        while (result.next()) {
            Course course = GetCourseById(result.getString("course_id").substring(1));
            RequiredCourseList.add(course);
        }
        return RequiredCourseList;
    } */