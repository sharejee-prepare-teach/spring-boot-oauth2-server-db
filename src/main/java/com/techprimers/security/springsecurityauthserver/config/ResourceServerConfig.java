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

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .antMatchers("/admin","/user","/403").mvcMatchers("/admin","/user","/403")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                /*
                -------- can't control role so you must to check at each controller
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER")*/
               ;

       /*http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/about","/oauth/authorize").permitAll()
                .antMatchers("/admin*//**//**").hasAnyRole("ADMIN")
                .antMatchers("/user*//**//**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);*/
       /* http
                .formLogin().loginPage("/login").permitAll()
                .and().httpBasic().and()
                .requestMatchers()
                //specify urls handled
                .antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
                .antMatchers("/fonts*//**", "/js*//**", "/css*//**")
                .and()
                .authorizeRequests()
                .antMatchers("/fonts*//**", "/js*//**", "/css*//**").permitAll()
                .antMatchers("/home", "/about").permitAll()
                .anyRequest().authenticated();*/
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
                        "/js/**", "/images/**","/loginhome","/home","/about","/error/**");
    }
}
