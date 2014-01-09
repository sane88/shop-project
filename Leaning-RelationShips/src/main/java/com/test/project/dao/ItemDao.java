package com.test.project.dao;

import com.test.project.domain.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Valentin
 */
@Repository("itemDao")
@Transactional
public class ItemDao extends AbstractDao implements DAO<Item, Integer>{

    private static final String GET_ALL_REQUEST = "SELECT * FROM ITEMS";

    @Override
    public Item get(Integer id){
       return (Item) getSession().get(Item.class, id);
    }

    @Override
    public Collection<Item> getAll(){
        return getSession().createSQLQuery(GET_ALL_REQUEST).addEntity(Item.class).list();
    }

    @Override
    public void add(Item item){
        getSession().save(item);
    }

    @Override
    public void update(Item item){
        getSession().update(item);
    }
}
