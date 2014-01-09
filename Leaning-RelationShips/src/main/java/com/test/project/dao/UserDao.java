package com.test.project.dao;

import com.test.project.domain.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Valentin
 */
@Repository("userDao")
@Transactional
public class UserDao extends AbstractDao implements DAO<User, String>{

    @Override
    public User get(String username){
        return (User) getSession().get(User.class, username);
    }

    @Override
    public void add(User user){
        getSession().save(user);
    }

    @Override
    public void update(User user){
        getSession().update(user);
    }

    @Override
    public Collection<User> getAll() {
        throw  new UnsupportedOperationException();
    }


}
