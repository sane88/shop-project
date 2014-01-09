package com.test.project.service;

import com.test.project.dao.DAO;
import com.test.project.domain.Item;
import com.test.project.domain.User;
import com.test.project.exception.BadRequestException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Valentin
 */
@Service("service")
@Transactional
public class MainService {

    @Autowired
    private DAO<User, String> userDao;
    @Autowired
    private DAO<Item, Integer> itemDao;

    public Collection<Item> getAllItems(){
        Collection<Item> all = itemDao.getAll();
        for (Item item : all) {
            Hibernate.initialize(item.getUsersBoughtIt());
            Hibernate.initialize(item.getUsersWishIt());
        }
        return all;
    }

    public User getUser(String username) {
      return userDao.get(username);
    }

    public Item getItem(Integer id){
        return itemDao.get(id);
    }

    public void addUser(User user){
        userDao.add(user);
    }

    public void addPurchasedItem(String username, Integer itemId){
        User user = getUser(username);
        Item item = getItem(itemId);
        Hibernate.initialize(user.getPurchasedItems());
        Hibernate.initialize(user.getWishList());
        Collection<Item> wishList = new ArrayList<Item>(user.getWishList());
        if(wishList.contains(item)){
            user.removeWishedItem(item);
        }
        user.addPurchasedItem(item);
        userDao.update(user);
    }

    public void addWishedItem(String username, Integer itemId) {
        User user = getUser(username);
        Item item = getItem(itemId);
        Hibernate.initialize(user.getWishList());
        Collection<Item> wishList = user.getWishList();
        if(wishList.contains(item)){
            throw new BadRequestException("This Item already present in your wishlist");
        }
        user.addWishedItem(item);
        userDao.update(user);
    }

    public void removeFromWishlist(String username, Integer itemId){
        User user = getUser(username);
        Item item = getItem(itemId);
        Hibernate.initialize(user.getWishList());
        user.removeWishedItem(item);
        userDao.update(user);
    }

    public Collection<Item> getPurchasedItems(String username){
        User user = getUser(username);
        Hibernate.initialize(user.getPurchasedItems());
       return user.getPurchasedItems();
    }

    public Collection<Item> getWishList(String username){
        User user = getUser(username);
        Hibernate.initialize(user.getWishList());
        return user.getWishList();
    }

    public void setUserDao(DAO<User, String> userDao) {
        this.userDao = userDao;
    }

    public void setItemDao(DAO<Item, Integer> itemDao) {
        this.itemDao = itemDao;
    }
}
