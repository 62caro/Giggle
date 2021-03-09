package com.carolina.giggle.controller;

import com.carolina.giggle.entity.Role;
import com.carolina.giggle.entity.User;
import com.carolina.giggle.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegController {
    UserRepo userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/registration")
    public String reg(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("password") String password,
                          Model model){

        User user = new User();

        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setActive(true);
        user.setRole(Collections.singleton(new Role(1L, "USER")));
        userRepo.save(user);
        return "redirect:/blog";
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
