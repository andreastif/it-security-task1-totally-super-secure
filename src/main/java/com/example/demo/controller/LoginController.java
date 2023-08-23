package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {


    @RequestMapping("/login")
    public String getLogin() {
        return "loginpage.html";
    }
}

//något sådant här möjligtvis
    /*
    @PostMapping("/response")
    public String showLoginResponse(Model model) {
        if (username and password exists...){
            model.addAttribute("msg", "Login successful.");
        }
        else if (username and password doesnt exist) {
            model.addAttribute("msg", "Registration successful.");

        }
        else (if username or password is null) {
            model.addAttribute("msg", "All fields are required.");
        }

        return "response.html";
    }
}

     */
