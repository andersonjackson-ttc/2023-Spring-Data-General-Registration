package com.majors.majorpopulate.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.majors.majorpopulate.ConstantsAndStuff;
<<<<<<< HEAD
import com.majors.majorpopulate.Login;
=======
>>>>>>> d7e293ae3eb7dcb4cf16b2832683471041c7df94
import com.majors.majorpopulate.Major;
import com.majors.majorpopulate.Student;

import jakarta.validation.Valid;


@Controller
public class MajorPopulateController {
    //@Autowired
    //ConstantsandStuff cs;
    //SqlRepository sqlRepo = new SqlRepository();

    List<Major> majors = new ArrayList<>();
    // String classDescription = "HELLO this is a class description and its going to be what goes on in the class";

    @GetMapping("/form")
    public String getForm(Model model) throws Exception{
<<<<<<< HEAD
        boolean invalid = false;
        Login login = new Login();
        model.addAttribute("incorrect", invalid);
        model.addAttribute("loginInfo", login);
=======
        /* ConstantsAndStuff.populateMajorChoices();
        model.addAttribute("majorInfo", new Major());
        model.addAttribute("majorChoices", ConstantsAndStuff.majorList); */
>>>>>>> d7e293ae3eb7dcb4cf16b2832683471041c7df94
        
        return "form";
    }

    @PostMapping("/submitRegister")
    public String HandlerRegister(@Valid Student student, BindingResult result, Model model) {
        model.addAttribute("majorChoices", ConstantsAndStuff.majorList);
        if (!(student.getPassword().equals(student.getPasswordValidation()))) result.rejectValue("passwordValidation", "", "Passwords Must Match");
        if (result.hasErrors()) return "register";
        ConstantsAndStuff.CreateStudent(student);
<<<<<<< HEAD
       
=======

>>>>>>> d7e293ae3eb7dcb4cf16b2832683471041c7df94
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

<<<<<<< HEAD
    @PostMapping("/studentLogin")
    public String findStudent(@Valid Login login, Model model) throws Exception {
        String correctCredentials = ConstantsAndStuff.doesCredentialsMatch(login.getName(), login.getPassword());
        if(correctCredentials.equals("0")) {
            boolean invalid = true;
            model.addAttribute("incorrect", invalid);
            model.addAttribute("loginInfo", login);
            return "form";
        }

        return "redirect:/mainpage";
    }

=======
>>>>>>> d7e293ae3eb7dcb4cf16b2832683471041c7df94
    //Commented out for now. Main Page not working just yet

    // @PostMapping("/submitMajor")
    // public String handleMajor(Major major) {
    //     majors.add(major);
    //     //ConstantsAndStuff.showMajorRequirements(major.getName());
        
    //     return "redirect:/mainpage";
    // }

<<<<<<< HEAD
    @GetMapping("/mainpage")
    public String populateInfo(Model model) {
        String majorName = ConstantsAndStuff.getMajorNameFromLoggedInUser(ConstantsAndStuff.loggedInUser.get(0).getName(), ConstantsAndStuff.loggedInUser.get(0).getPassword());
        String name = ConstantsAndStuff.loggedInUser.get(0).getName();
        model.addAttribute("information", new Major(name, majorName));
        System.out.println(name);
        String majorId = ConstantsAndStuff.getMajorIdFromName(majorName);
    

    // model.addAttribute("information", new Major(ConstantsAndStuff.showMajorRequirements(majorId)))
    //     model.addAttribute("classes", ConstantsAndStuff.majorRequirement);
    //     model.addAttribute("electives", ConstantsAndStuff.majorElectives);
    //     model.addAttribute("description", classDescription);
        return "mainpage";
    }
=======
    // @GetMapping("/mainpage")
    // public String populateInfo(Model model) {
    //     model.addAttribute("information", majors.get(0));
    //     model.addAttribute("classes", ConstantsAndStuff.majorRequirement);
    //     model.addAttribute("electives", ConstantsAndStuff.majorElectives);
    //     model.addAttribute("description", classDescription);
    //     return "mainpage";
    // }
>>>>>>> d7e293ae3eb7dcb4cf16b2832683471041c7df94

}
