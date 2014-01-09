package com.test.project.mocks;

import com.test.project.domain.User;

/**
 * @author Valentin
 */
public class UserMock {

    public static User getUser1(){
        User user = new User();
        user.setRole("USER");
        user.setUsername("testUser1");
        user.setPassword("testPassword1");
        return user;
    }


    public static User getUser2(){
        User user = new User();
        user.setRole("USER");
        user.setUsername("testUser2");
        user.setPassword("testPassword2");
        return user;
    }
}
