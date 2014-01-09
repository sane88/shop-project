package com.test.project.mocks;

import com.test.project.dao.DAO;
import com.test.project.domain.User;

import java.util.Collection;

/**
 * @author Valentin
 */
public class UserDaoMock implements DAO<User, String> {

    private User user = UserMock.getUser1();

    public UserDaoMock() {
        user.addWishedItem(ItemMock.getItem1());
        user.addPurchasedItem(ItemMock.getItem2());
    }

    @Override
    public User get(String parameter) {
        if(user.getUsername().equals(parameter))
        return user;
        return null;
    }

    @Override
    public void add(User entity) {
        user = entity;
    }

    @Override
    public void update(User entity) {
        user = entity;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }

    public User getUser(){
        return user;
    }
}
