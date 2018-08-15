package com.techprimers.security.springsecurityauthserver.service.impl;

import com.techprimers.security.springsecurityauthserver.model.Users;
import com.techprimers.security.springsecurityauthserver.repository.UsersRepository;
import com.techprimers.security.springsecurityauthserver.service.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rith on 8/15/2018.
 */
@Service
public class UserServiceAPIImpl implements UserServiceAPI {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> list() {
        return (List<Users>) usersRepository.findAll();
    }
}
