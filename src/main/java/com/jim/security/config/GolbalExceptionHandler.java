package com.jim.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GolbalExceptionHandler {
    Logger log = LoggerFactory.getLogger(GolbalExceptionHandler.class);


    //@ExceptionHandler(AccessDeniedException.class)
    public String handleAuthorizationException(AccessDeniedException e)
    {
        log.error(e.getMessage());
        return "没有权限，请联系管理员授权";
    }

}
