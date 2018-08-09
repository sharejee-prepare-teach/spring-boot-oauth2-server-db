package com.techprimers.security.springsecurityauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService customUserDetailsService;

  /*  @Autowired
    private AccessDeniedHandler accessDeniedHandler;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll();

       http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/about","/oauth/authorize").permitAll()
                .antMatchers("/admin*//**").hasAnyRole("ADMIN")
                .antMatchers("/user*//**").hasAnyRole("USER")
                .anyRequest().authenticated();
        /*         .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);*/
        /*http.requestMatchers()
                .antMatchers("/oauth/authorize");*/
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.parentAuthenticationManager(authenticationManager)
                .userDetailsService(customUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**",
                        "/js/**", "/images/**","/loginhome");
    }
}
