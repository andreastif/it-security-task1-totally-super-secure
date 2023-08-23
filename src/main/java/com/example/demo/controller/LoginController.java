package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@Slf4j
public class LoginController {

    UserServiceImpl userServiceImpl;

    @Autowired
    public LoginController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "loginpage";
    }


    @PostMapping("/login")
    public String postLogin(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("Bindingresult: " + bindingResult);
            model.addAttribute("error", "Something went wrong");
            return "loginpage";
        }

        //anropa service, persistera user
        model.addAttribute("user", user);
        return "successful";
    }

    @GetMapping("/test")
    public String getUsers2(Model model) {
        model.addAttribute("users", userServiceImpl.findAll());
        return "test";
    }

    @GetMapping("/create")
    public String getCreate() {
        return "createaccount";
    }

    @PostMapping("/create")
    public String createAccount(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("Bindingresult: " + bindingResult);
            model.addAttribute("error", "Something went wrong");
            return "createaccount";
        }
        return userServiceImpl.newUser(model);
    }
}


