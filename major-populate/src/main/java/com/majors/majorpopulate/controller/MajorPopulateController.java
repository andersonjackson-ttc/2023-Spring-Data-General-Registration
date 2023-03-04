package com.majors.majorpopulate.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.Section;
import com.majors.majorpopulate.service.MajorService;
import com.majors.majorpopulate.student.Login;
import com.majors.majorpopulate.student.Student;

import jakarta.validation.Valid;


@Controller
public class MajorPopulateController {

    @GetMapping("/form")
    public String getForm(Model model) throws Exception{
        boolean invalid = false;
        Login login = new Login();
        model.addAttribute("incorrect", invalid);
        model.addAttribute("loginInfo", login);
        
        return "form";
    }

    @PostMapping("/submitRegister")
    public String HandlerRegister(@Valid Student student, BindingResult result, Model model) throws Exception {
        model.addAttribute("majorChoices", MajorService.populateMajorChoices());
        if (!(student.getPassword().equals(student.getPasswordValidation()))) result.rejectValue("passwordValidation", "", "Passwords Must Match");
        if (result.hasErrors()) return "register";
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
        if(correctCredentials.equals("0")) {
            boolean invalid = true;
            model.addAttribute("incorrect", invalid);
            model.addAttribute("loginInfo", login);
            return "form";
        }
        return "redirect:/mainpage";
    }

    @GetMapping("/mainpage")
    public String populateInfo(Model model) throws Exception {
        String majorName = MajorService.loggedInUser.get(0).getMajorName();
        String name = MajorService.loggedInUser.get(0).getName();
        Major major = MajorService.getMajorById(MajorService.loggedInUser.get(0).getMajorID());
        model.addAttribute("information", new Major(name, majorName));
        model.addAttribute("coreRequirements", major.getRequiredCourses());
        model.addAttribute("electives", major.MajorElectiveGroups);
        model.addAttribute("fakeCourses", MajorService.getFakeCourses());

        return "mainpage";
    }

    @GetMapping("/courseSearch")
    public String getCourseSearch(Model model, String name) throws Exception{
        boolean courseSelected = false;
        if (name != null) {
            courseSelected = true;
        }
        model.addAttribute("showTable", courseSelected);
        List<Section> section = MajorService.getSectionTimesByCourseName(name);
        model.addAttribute("sectionTimes", section);
        return "course-search";
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model, String sectionId) throws Exception{
        String courseId = MajorService.gettingCorrectCourseId(sectionId);    
        System.out.println(sectionId);
        System.out.println(courseId);
        MajorService.createRegisteredSection(sectionId, courseId);
        return "schedule";
    }
}
