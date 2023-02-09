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
    // String classDescription = "HELLO this is a class description and its going to be what goes on in the class";

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
        model.addAttribute("information", majors.get(0));
        model.addAttribute("classes", ConstantsAndStuff.majorRequirement);
        model.addAttribute("electives", ConstantsAndStuff.majorElectives);
        // model.addAttribute("description", classDescription);
        return "mainpage";
    }

}
