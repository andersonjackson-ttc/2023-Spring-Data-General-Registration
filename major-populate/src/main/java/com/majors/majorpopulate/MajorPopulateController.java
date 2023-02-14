package com.majors.majorpopulate;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;


@Controller
public class MajorPopulateController {
    
    List<Major> majors = new ArrayList<>();
    // String classDescription = "HELLO this is a class description and its going to be what goes on in the class";

    @GetMapping("/form")
    public String getForm(Model model) throws Exception{
        // ConstantsAndStuff.populateMajorChoices();
        // model.addAttribute("majorInfo", new Major());
        // model.addAttribute("majorChoices", ConstantsAndStuff.majorList);
        
        return "form";
    }

    @PostMapping("/submitRegister")
    public String HandlerRegister(@Valid Student student, BindingResult result, Model model) {
        model.addAttribute("majorChoices", ConstantsAndStuff.majorList);
        if (!(student.getPassword().equals(student.getPasswordValidation()))) result.rejectValue("passwordValidation", "", "Passwords Must Match");
        if (result.hasErrors()) return "register";
        ConstantsAndStuff.CreateStudent(student);

        return "redirect:/form";
    }

    @GetMapping("/register")
    public String register(Model model) throws Exception {
        Student student = new Student();
        model.addAttribute("student", student);
        ConstantsAndStuff.populateMajorChoices();
        model.addAttribute("majorChoices", ConstantsAndStuff.majorList);
        return "register";
    }

    //Commented out for now. Main Page not working just yet

    // @PostMapping("/submitMajor")
    // public String handleMajor(Major major) {
    //     majors.add(major);
    //     //ConstantsAndStuff.showMajorRequirements(major.getName());
        
    //     return "redirect:/mainpage";
    // }

    // @GetMapping("/mainpage")
    // public String populateInfo(Model model) {
    //     model.addAttribute("information", majors.get(0));
    //     model.addAttribute("classes", ConstantsAndStuff.majorRequirement);
    //     model.addAttribute("electives", ConstantsAndStuff.majorElectives);
    //     model.addAttribute("description", classDescription);
    //     return "mainpage";
    // }

}
