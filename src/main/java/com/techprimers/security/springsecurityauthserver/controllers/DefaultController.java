package com.techprimers.security.springsecurityauthserver.controllers;

import com.techprimers.security.springsecurityauthserver.model.Users;
import com.techprimers.security.springsecurityauthserver.service.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Rith on 8/8/2018.
 */
@Controller
public class DefaultController {
    @Autowired
    private UserServiceAPI userServiceAPI;

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin(Principal principal) {
        System.out.println("Hello admin");
        System.out.println(principal.getName());
        Optional<Users> users = userServiceAPI.findByName(principal.getName());
        users.ifPresent(user -> {
            System.out.println("User's name = " + user.getName());
        });
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }



}
