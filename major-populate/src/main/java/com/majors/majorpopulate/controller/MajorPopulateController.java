package com.majors.majorpopulate.controller;

import java.util.List;

import com.majors.majorpopulate.POJO.Admin;
import com.majors.majorpopulate.POJO.SearchTerm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.service.MajorService;
import com.majors.majorpopulate.student.Login;
import com.majors.majorpopulate.student.Student;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MajorPopulateController {

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
        model.addAttribute("majorChoices", MajorService.populateMajorChoices());
        if (!(student.getPassword().equals(student.getPasswordValidation())))
            result.rejectValue("passwordValidation", "", "Passwords Must Match");
        if (result.hasErrors())
            return "register";
        MajorService.CreateStudent(student);
        return "redirect:/form";
    }

    @GetMapping("/register")
    public String register(Model model) throws Exception {
        Student student = new Student();
        model.addAttribute("student", student);
        MajorService.populateMajorChoices();
        model.addAttribute("majorChoices", MajorService.populateMajorChoices());
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
        if (login.getName().equals("admin") && login.getPassword().equals("admin")) {
            return "redirect:/adminMainpage";
        }  
        return "redirect:/mainpage";
    }

    @GetMapping("/mainpage")
    public String populateInfo(Model model) throws Exception {
        String majorName = MajorService.loggedInUser.get(0).getMajorName();
        String name = MajorService.loggedInUser.get(0).getName();
        Major major = MajorService.getMajorById(MajorService.loggedInUser.get(0).getMajorID());
        //Major major = MajorService.getCourseStatusForStudent(0, major);
        model.addAttribute("information", new Major(name, majorName));
        model.addAttribute("coreRequirements", major.getRequiredCourses());
        model.addAttribute("electives", major.MajorElectiveGroups);


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
        String courseId = MajorService.gettingCorrectCourseId(sectionId);
        System.out.println(sectionId);
        System.out.println(courseId);
        System.out.println(term);
        MajorService.createRegisteredSection(sectionId, courseId, term);

        return "registration";
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model) throws Exception {
        model.addAttribute("registeredSections", MajorService.getRegisteredSections());
        return "schedule";
    }

    @GetMapping("/removeSection")
    public String removeSection(String courseId) throws Exception {
        MajorService.deleteSection(courseId);
        return "section-remove-confirm";
    }

    @GetMapping("/adminMainpage")
    public String getAdminMainpage(){
        return "admin-mainpage";
    }

    @GetMapping("/studentSearch")
    public String getStudentSearch(Model model){
        
        model.addAttribute("studentSearch", new Admin());
        return "admin-studentSearch";
    }

    @PostMapping("/studentSearchResult") 
    public String getStudentSearchResult(@ModelAttribute Admin studentName, Model model) throws Exception {
        List<Student> studentList = MajorService.getStudentClasses(studentName.getStudent());
        model.addAttribute("studentName", studentName);
        model.addAttribute("studentList", studentList);
        
        return "student-search-result";
    }
    
    @GetMapping("/modifyCourses")
    public String getModifyCourses(){
        return "admin-modifyCourses";
    }

}
