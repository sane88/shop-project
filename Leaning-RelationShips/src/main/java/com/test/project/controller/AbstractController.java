package com.test.project.controller;

import com.test.project.exception.AuthenticationException;
import com.test.project.exception.BadRequestException;
import com.test.project.service.MainService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Logger;

/**
 * @author Valentin
 */
public abstract class AbstractController {


    @Autowired
    private MainService service;

    protected boolean isAuthenticated(Authentication authentication){
        return authentication.isAuthenticated() && !"anonymousUser".equalsIgnoreCase(authentication.getName());
    }

    protected Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected void checkAuthentication(Authentication authentication){
        if(!isAuthenticated(authentication)){
            throw new AuthenticationException("You must to pass sign in process");
        }
    }

    protected MainService getService(){
        return service;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String handleAuthException(AuthenticationException e){
        LoggerFactory.getLogger(getClass()).warn(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleBadRequestException(BadRequestException e){
        LoggerFactory.getLogger(getClass()).warn(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(Throwable throwable){
        LoggerFactory.getLogger(getClass()).error(throwable.getMessage(), throwable);
        return "Oops something happened";
    }
}
