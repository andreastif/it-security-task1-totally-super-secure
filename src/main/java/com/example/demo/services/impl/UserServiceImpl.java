package com.example.demo.services.impl;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String newUser(Model model) {
        User user = (User) model.getAttribute("user");
        if (!userRepository.existsByUsername(user.getUsername())) {

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            User savedUser = userRepository.save(user);

            model.addAttribute("msg", "Created user " + savedUser.getUsername());
            return "response";
        } else {
            model.addAttribute("msg", "User already exists");
            return "createaccount";
        }
    }

    @Override
    public Boolean logInSuccessful(Model model) {
        User user = (User) model.getAttribute("user");
        return passwordEncoder.matches(user.getPassword(), userRepository.findByUsername(user.getUsername()).getPassword());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}
