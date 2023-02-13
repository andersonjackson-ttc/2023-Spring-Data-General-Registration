package com.majors.majorpopulate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MajorPopulateController {
    
    List<Major> majors = new ArrayList<>();

    @GetMapping("/form")
    public String getForm(Model model) {
        ConstantsAndStuff.populateMajorChoices();
        model.addAttribute("majorInfo", new Major());
        model.addAttribute("majorChoices", ConstantsAndStuff.majorList);
        return "form";
    }

    @PostMapping("/submitMajor")
    public String handleMajor(Major major) {
        majors.add(major);
        ConstantsAndStuff.showMajorRequirements(major.getMajorName());
        return "redirect:/mainpage";
    }

    @GetMapping("/mainpage")
    public String populateInfo(Model model) {
        model.addAttribute("information", majors);
        model.addAttribute("classes", ConstantsAndStuff.majorRequirement);
        model.addAttribute("electives", ConstantsAndStuff.majorElectives);
        return "mainpage";
    }
    @PostMapping("/submitRegister")
    public String HandlerRegister(Student student) {
        ConstantsAndStuff.CreateStudent(student);

        return "redirect:/register";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        ConstantsAndStuff.populateMajorChoices();
        model.addAttribute("majorChoices", ConstantsAndStuff.majorList);
        return "register";
    }

}
