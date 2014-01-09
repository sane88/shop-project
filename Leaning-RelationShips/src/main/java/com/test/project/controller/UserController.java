package com.test.project.controller;

import com.test.project.dao.DAO;
import com.test.project.dao.UserDao;
import com.test.project.domain.User;
import com.test.project.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Valentin
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam(required = true) String login,
                           @RequestParam(required = true) String password, HttpServletResponse response) throws IOException {

        User user = getService().getUser(login);
        if (user != null){
            throw new AuthenticationException("User \""+ login + "\" already exists");
        }
        User newUser = new User();
        newUser.setUsername(login);
        newUser.setPassword(password);
        newUser.setRole("USER");
        getService().addUser(newUser);
        response.sendRedirect("/authorize?login=" + login + "&password=" + password);
        return "success";
    }

    @RequestMapping(value = "get")
    @ResponseBody
    public User getUser(@RequestParam(required = true) String name){
        return getService().getUser(name);
    }
}
