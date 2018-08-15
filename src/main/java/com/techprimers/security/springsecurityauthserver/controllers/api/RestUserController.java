package com.techprimers.security.springsecurityauthserver.controllers.api;

import com.techprimers.security.springsecurityauthserver.model.Users;
import com.techprimers.security.springsecurityauthserver.service.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Rith on 8/15/2018.
 */
@RestController
public class RestUserController {
    @Autowired
    public UserServiceAPI userServiceAPI;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<Users> getAllUsers(){
        return (List<Users>) userServiceAPI.list();
    }
}
