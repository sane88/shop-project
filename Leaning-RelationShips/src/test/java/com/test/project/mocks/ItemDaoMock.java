package com.test.project.mocks;

import com.test.project.dao.DAO;
import com.test.project.domain.Item;

import java.util.Collection;

/**
 * @author Valentin
 */
public class ItemDaoMock implements DAO<Item, Integer> {

    private Collection<Item> items = ItemMock.getItems();

    @Override
    public Item get(Integer parameter) {
        for (Item item : items) {
            if(item.getItemId() == parameter){
                return item;
            }
        }
        return null;
    }

    @Override
    public void add(Item entity) {

    }

    @Override
    public void update(Item entity) {

    }

    @Override
    public Collection<Item> getAll() {
        return items;
    }

//    public void setItems(Collection<Item> items) {
//        this.items = items;
//    }
}
