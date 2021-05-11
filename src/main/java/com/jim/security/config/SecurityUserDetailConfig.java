package com.jim.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityUserDetailConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/success.html").permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .and().authorizeRequests()
                .antMatchers("/user/login").permitAll()
                //当前登录用户，只有具有admin权限在能访问
                //.antMatchers("/test/index").hasAuthority("admins")
                //.antMatchers("/test/index").hasAnyAuthority("admins,role")
                .antMatchers("/test/index").hasRole("role")
                .anyRequest().authenticated()
                .and().csrf().disable();

        http.exceptionHandling()
                .accessDeniedPage("/unAuth.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
