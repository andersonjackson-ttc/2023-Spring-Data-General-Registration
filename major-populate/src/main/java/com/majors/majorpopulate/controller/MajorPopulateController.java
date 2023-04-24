package com.majors.majorpopulate.controller;

import java.util.List;

import com.majors.majorpopulate.POJO.CourseOffers;
import com.majors.majorpopulate.POJO.SearchTerm;
import com.majors.majorpopulate.POJO.Grade;
import com.majors.majorpopulate.POJO.RegistrationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.service.AdminService;
import com.majors.majorpopulate.service.CourseService;
import com.majors.majorpopulate.service.GradeService;
import com.majors.majorpopulate.service.MajorService;
import com.majors.majorpopulate.service.MajorService2;
import com.majors.majorpopulate.service.RegistrationService;
import com.majors.majorpopulate.service.StudentService;
import com.majors.majorpopulate.student.Login;
import com.majors.majorpopulate.student.Student;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MajorPopulateController {

    @Autowired
    private MajorService2 ms2;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private CourseService cs;
    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;

    @GetMapping("/form")
    public String getForm(Model model) throws Exception {
        boolean invalid = false;
        Login login = new Login();
        model.addAttribute("incorrect", invalid);
        model.addAttribute("loginInfo", login);

        return "form";
    }

    @PostMapping("/submitRegister")
    public String HandlerRegister(@Valid Student student, BindingResult result, Model model) throws Exception {
        model.addAttribute("majorChoices", ms2.findAll());
        if (!(student.getPassword().equals(student.getPasswordValidation())))
            result.rejectValue("passwordValidation", "", "Passwords Must Match");
        if (result.hasErrors())
            return "register";
        MajorService.CreateStudent(student);
     
        com.majors.majorpopulate.POJO.Student studentId = studentService.findByNameAndPassword(student.getName(), student.getPassword());
        System.out.println(studentId.getId());
        Grade eng100 = new Grade();
        Grade rwr100 = new Grade();
        Grade mat033 = new Grade();
        eng100 = MajorService.getEng100(eng100, studentId.getId());
        rwr100 = MajorService.getRwr100(rwr100, studentId.getId());
        mat033 = MajorService.getMat033(mat033, studentId.getId());
        gradeService.save(eng100);
        gradeService.save(rwr100);
        gradeService.save(mat033);
        return "redirect:/form";
    }

    @GetMapping("/register")
    public String register(Model model) throws Exception {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("majorChoices", ms2.findAll());
        return "register";
    }

    @PostMapping("/studentLogin")
    public String findStudent(@Valid Login login, Model model) throws Exception {
        String correctCredentials = MajorService.validateLogin(login.getName(), login.getPassword());
        if (correctCredentials.equals("0")) {
            boolean invalid = true;
            model.addAttribute("incorrect", invalid);
            model.addAttribute("loginInfo", login);
            return "form";
        }
        if (login.getName().equalsIgnoreCase("admin") && login.getPassword().equalsIgnoreCase("admin")) {
            return "redirect:/adminMainpage";
        }
        return "redirect:/mainpage";
    }

    @GetMapping("/mainpage")
    public String populateInfo(Model model, @RequestParam(name = "groupId", required = false) Integer groupId)
            throws Exception {
        String majorName = MajorService.loggedInUser.get(0).getMajorName();
        String name = MajorService.loggedInUser.get(0).getName();
        // Major major =
        // MajorService.getMajorById(MajorService.loggedInUser.get(0).getMajorID());
        int studentId = MajorService.getStudentId();
        // MajorService.getCourseStatusForStudent(studentId, major);

        var result = ms2.findAllCoursesByMajorName(majorName, studentId);
        var megs = ms2.findElectGroupsInMajor(majorName,studentId);
        model.addAttribute("information", new Major(name, majorName));
        model.addAttribute("coreRequirements", result);
        model.addAttribute("electiveGroups", megs);

        return "mainpage";
    }

    @GetMapping("/courseSearch")
    public String getCourseSearch(Model model, @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "searchTerm", required = false) String term) throws Exception {
        boolean courseSelected = false;
        if (name != null) {
            courseSelected = true;
        }
        model.addAttribute("showTable", courseSelected);
        List<Section> section = MajorService.getSectionTimesByCourseName(name, term);
        List<String> terms = MajorService.getTerm();
        model.addAttribute("terms", terms);
        model.addAttribute("major", name);
        model.addAttribute("sectionTimes", section);
        model.addAttribute("result", new SearchTerm());
        return "course-search";
    }

    @PostMapping("/courseSearch")
    public String selectTerm(@ModelAttribute(name = "result") SearchTerm searchTerm,
            @RequestParam(value = "name", required = false) String name) {

        return "redirect:/courseSearch?name=" + name + "&searchTerm=" + searchTerm.getDescription();

    }

    @GetMapping("/handleRegistration")
    public String handleRegistration(Model model, String sectionId, String term) throws Exception {
        String courseId = MajorService.parseCourseId(sectionId);
        String registeredStatus = "Registered";
        RegistrationDTO newRegisteredSection = new RegistrationDTO();
        newRegisteredSection.setMajorId(MajorService.loggedInUser.get(0).getMajorID());
        newRegisteredSection.setCourseId(courseId);
        newRegisteredSection.setRegDTS(registeredStatus);
        newRegisteredSection.setStudentId(MajorService.getStudentId());
        newRegisteredSection.setTerm(term);
        ms2.save(newRegisteredSection);
        // MajorService.createRegisteredSection(sectionId, courseId, term);

        return "registration";
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model) throws Exception {
        List<RegistrationDTO> schedule = registrationService.findCoursesByStudentIdOrdered(MajorService.getStudentId());
        
        model.addAttribute("studentInfo", MajorService.loggedInUser.get(0));
        model.addAttribute("registeredSections", schedule);
        
        return "schedule";
    }

    @GetMapping("/removeSection")
    public String removeSection(String courseId) throws Exception {
        registrationService.deleteByCourseIdAndStudentId(courseId, MajorService.getStudentId());
        // MajorService.deleteSection(courseId);
        return "section-remove-confirm";
    }

    /* 
     * 
     */
    @GetMapping("/adminMainpage")
    public String getAdminMainpage() {
        return "admin-mainpage";
    }

    /* 
     * 
     */
    @GetMapping("/studentSearch")
    public String getStudentSearch(Model model) {

        model.addAttribute("studentSearch", new AdminService());
        return "admin-studentSearch";
    }

    @GetMapping("/AdminCourseSearch")
    public String getCourseSearch(Model model) {

        model.addAttribute("courseSearch", new AdminService());
        return "admin-courseSearch";
    }

    @PostMapping("/studentSearchResult")
    public String getStudentSearchResult(@ModelAttribute AdminService studentName, Model model) throws Exception {
        List<Student> studentList = MajorService.getStudentClasses(studentName.getStudent());
        model.addAttribute("studentName", studentName);
        model.addAttribute("studentList", studentList);

        return "student-search-result";
    }

    @PostMapping("/CoursesResult")
    public String getCoursesResult(@ModelAttribute AdminService courseName, Model model) throws Exception {

        model.addAttribute("courseName", courseName);
        return "redirect:/modifyCoursesResult?courseName=" + courseName.getCourse();

    }

    @GetMapping("/modifyCoursesResult")
    public String getModifyCourses(@RequestParam(value = "courseName", required = false) String courseName, Model model)
            throws Exception {
        List<CourseOffers> courseOffers = MajorService.getCourses(courseName);
        model.addAttribute("courseOffers", courseOffers);
        return "admin-modifyCourses";
    }

    @GetMapping("/modifyCourse")
    public String modifyCourse(@RequestParam(value = "Id", required = false) int id, Model model) throws Exception {

        var o = MajorService.getCoursesById(id);
        model.addAttribute("courseOffer", o);
        return "admin-modifyCourses-form";
    }

    @PostMapping("/modifyCourse")
    public String modifyCourseUpdate(@Valid CourseOffers CourseOffer, Model model) throws Exception {

        MajorService.updateCourse(CourseOffer);
        // model.addAttribute("courseOffer", o);
        return "redirect:adminMainpage";
    }

    @GetMapping("studentSearchMainpage")
    public String getStudentSearchMainpage(@RequestParam(value = "Id", required = false) int id, Model model)
            throws Exception {
        var o = MajorService.getStudentById(id);
        model.addAttribute("student", o);
        return "admin-student-mainpage";
    }

    @GetMapping("/adminStudentSchedule")
    public String getAdminStudentSchedule(@RequestParam(value = "Id", required = false) int id, Model model)
            throws Exception {
        List<RegistrationDTO> schedule = registrationService.findByStudentId(id);
        model.addAttribute("studentsSchedule", schedule);
        model.addAttribute("student", MajorService.getStudentById(id));
        return "admin-student-schedule";
    }

    @GetMapping("/adminRemoveSection")
    public String adminRemoveSection(int studentId, String courseId) throws Exception {
        MajorService.adminDeleteSection(studentId, courseId);
        return "redirect:/adminStudentSchedule?Id=" + studentId;
    }

    @GetMapping("/modifyStudent")
    public String getModifyStudent(@RequestParam(value = "Id", required = false) int id, Model model) throws Exception {

        var o = MajorService.getStudentById(id);
        model.addAttribute("student", o);
        model.addAttribute("majorChoices", ms2.findAll());

        return "student-form";
    }

    @PostMapping("/modifyStudent")
    public String modifyStudent(Student student, Model model) throws Exception {

        MajorService.updateStudent(student);
        return "redirect:adminMainpage";
    }

    @GetMapping("/adminGradeSubmitForm")
    public String getAdminGradeSubmitForm(int studentId, String courseId, String term, Model model) {
        Grade grade = new Grade();
        grade.setStudentId(studentId);
        grade.setCourseId(courseId);
        grade.setTermId(term);
        model.addAttribute("addGrade", grade);
        return "admin-grade-submit-form";
    }

    @GetMapping("/homepage")
    public String getHomePage(){
        return "homepage";
    }

}