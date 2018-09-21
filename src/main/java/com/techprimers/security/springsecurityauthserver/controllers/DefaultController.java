package com.techprimers.security.springsecurityauthserver.controllers;

import com.techprimers.security.springsecurityauthserver.model.Role;
import com.techprimers.security.springsecurityauthserver.model.Users;
import com.techprimers.security.springsecurityauthserver.service.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;
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
        Users users = userServiceAPI.findByName(principal.getName());
        String role = "";
        Boolean b = false;
        if (users.getRoles() != null) {
            for (Iterator<Role> roleIterator = users.getRoles().iterator(); roleIterator.hasNext(); ) {
                role = roleIterator.next().getRole();
                if (role.equalsIgnoreCase("ADMIN")) {
                    b = true;
                }
            }
            if (b == true) {
                return "/admin";
            } else {
                return "/error/403";
            }
        }
        return null;
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
