package com.example.demo.services.impl;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String newUser(Model model) {
        User user = (User) model.getAttribute("user");
        if (!userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("user", userRepository.save(user));
            return "successful";
        } else {
            model.addAttribute("error", "User already exists");
            return "createaccount";
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}
