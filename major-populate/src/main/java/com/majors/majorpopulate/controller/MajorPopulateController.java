package com.majors.majorpopulate.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.majors.majorpopulate.ConstantsAndStuff;
import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.Student;

import jakarta.validation.Valid;


@Controller
public class MajorPopulateController {
    //@Autowired
    //ConstantsandStuff cs;
    //SqlRepository sqlRepo = new SqlRepository();

    // String classDescription = "HELLO this is a class description and its going to be what goes on in the class";

    @GetMapping("/form")
    public String getForm(Model model) throws Exception{
        model.addAttribute("majorInfo", new Major());
        model.addAttribute("majorChoices", ConstantsAndStuff.populateMajorChoices());
        
        return "form";
    }

    @PostMapping("/submitRegister")
    public String HandlerRegister(@Valid Student student, BindingResult result, Model model) throws Exception {
        model.addAttribute("majorChoices", ConstantsAndStuff.populateMajorChoices());
        if (!(student.getPassword().equals(student.getPasswordValidation()))) result.rejectValue("passwordValidation", "", "Passwords Must Match");
        if (result.hasErrors()) return "register";
        ConstantsAndStuff.CreateStudent(student);

        return "redirect:/form";
    }

    @GetMapping("/register")
    public String register(Model model) throws Exception {
        Student student = new Student();
        model.addAttribute("student", student);
        //ConstantsAndStuff.populateMajorChoices();
        model.addAttribute("majorChoices", ConstantsAndStuff.populateMajorChoices());
        return "register";
    }

    //Commented out for now. Main Page not working just yet

    // @PostMapping("/submitMajor")
    // public String handleMajor(Major major) {
    //     majors.add(major);
    //     //ConstantsAndStuff.showMajorRequirements(major.getName());
        
    //     return "redirect:/mainpage";
    // }

    @GetMapping("/mainpage")
    public String populateInfo(Model model) {
         model.addAttribute("information", major.majorName);
         model.addAttribute("classes", ConstantsAndStuff.showRequiredCourses(MajorId:null));
         model.addAttribute("electives", ConstantsAndStuff.showMajorElectiveCourses(null));
         model.addAttribute("description", major.getDescription);
         return "mainpage";
     }

}
