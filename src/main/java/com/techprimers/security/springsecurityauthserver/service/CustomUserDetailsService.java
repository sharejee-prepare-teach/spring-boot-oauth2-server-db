package com.techprimers.security.springsecurityauthserver.service;

import com.techprimers.security.springsecurityauthserver.model.MyUserPrincipal;
import com.techprimers.security.springsecurityauthserver.model.Users;
import com.techprimers.security.springsecurityauthserver.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> usersOptional = usersRepository.findByName(username);

        usersOptional
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return usersOptional.map(CustomUserDetails::new).get();
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = usersRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
