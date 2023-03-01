package com.majors.majorpopulate;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MajorPopulateController {
    @Autowired
    private ConstantsAndStuff _constantsAndStuff;
    private List<Major> majors = new ArrayList<>();
    // String classDescription = "HELLO this is a class description and its going to be what goes on in the class";
    @GetMapping("/")
    public String getMain(Model model) throws Exception{


        return "redirect:/form";
    }
    @GetMapping("/form")
    public String getForm(Model model, @RequestParam(value = "valid",required = false) String valid) throws Exception{

         model.addAttribute("student", new Student());
         model.addAttribute("valid", valid);
        
        return "form";
    }

    @PostMapping("/submitRegister")
    public String HandlerRegister(@Valid Student student, BindingResult result, Model model) {
        model.addAttribute("majorChoices", _constantsAndStuff.majorList);
        if (!(student.getPassword().equals(student.getPasswordValidation()))) result.rejectValue("passwordValidation", "", "Passwords Must Match");
        if (result.hasErrors()) return "register";
        _constantsAndStuff.CreateStudent(student);

        return "redirect:/form";
    }

    @GetMapping("/register")
    public String register(Model model) throws Exception {
        Student student = new Student();
        model.addAttribute("student", student);
        _constantsAndStuff.populateMajorChoices();
        model.addAttribute("majorChoices", _constantsAndStuff.majorList);
        return "register";
    }

    @PostMapping("/studentLogin")
    public String HandlerLogin(@ModelAttribute Student student, BindingResult result, Model model) {

        int response = _constantsAndStuff.Login(student);
        if(response >0) {

            return "redirect:/mainpage?id="+response;
        }
        else {
            return "redirect:/form?valid=false";
        }
    }

    //Commented out for now. Main Page not working just yet

    // @PostMapping("/submitMajor")
    // public String handleMajor(Major major) {
    //     majors.add(major);
    //     //ConstantsAndStuff.showMajorRequirements(major.getName());
        
    //     return "redirect:/mainpage";
    // }

     @GetMapping("/mainpage")
     public String populateInfo(Model model,@RequestParam(value = "id",required = false) int id) {

         Student student =  _constantsAndStuff.GetStudent(id);
         _constantsAndStuff.MajorRequirements(student.getMajor());
         model.addAttribute("information",student );
         model.addAttribute("classes", _constantsAndStuff.majorRequirement);
         model.addAttribute("electives", _constantsAndStuff.majorElectives);
         model.addAttribute("description", "test");
         return "mainpage";
     }

}
