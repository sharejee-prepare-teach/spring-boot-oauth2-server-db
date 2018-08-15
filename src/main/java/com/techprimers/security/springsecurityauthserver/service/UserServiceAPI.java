package com.techprimers.security.springsecurityauthserver.service;

import com.techprimers.security.springsecurityauthserver.model.Users;

import java.util.List;

/**
 * Created by Rith on 8/15/2018.
 */
public interface UserServiceAPI {
    List<Users> list();
}
