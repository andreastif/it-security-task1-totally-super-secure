package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class LoginController {

    UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String getLogin(){
        return "loginpage";
    }

    @PostMapping("/login")
    public String postLogin(@Valid User user, BindingResult bindingResult, Model model){
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
    public String getUsers2(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "test";
    }


}
