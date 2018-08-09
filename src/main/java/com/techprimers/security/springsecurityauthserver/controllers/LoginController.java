package com.techprimers.security.springsecurityauthserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rith on 8/2/2018.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/loginhome",method = RequestMethod.GET)
    private String loginHomePage(){
        return "loginhome";
    }
}
