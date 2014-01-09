package com.test.project.mocks;

import com.test.project.domain.Item;
import com.test.project.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Valentin
 */
public class ItemMock {

    public static Item getItem1(){
        Item item = new Item();
        item.setItemId(1);
        item.setImageUrl("testImageUrl1");
        item.setName("testName1");
        item.setDescription("testDescription1");
        return item;
    }

    public static Item getItem2(){
        Item item = new Item();
        item.setItemId(2);
        item.setImageUrl("testImageUrl2");
        item.setName("testName2");
        item.setDescription("testDescription2");
        return item;
    }

    public static Item getItem3(){
        Item item = new Item();
        item.setItemId(3);
        item.setImageUrl("testImageUrl3");
        item.setName("testName3");
        item.setDescription("testDescription3");
        return item;
    }

    public static Collection<Item> getItems(){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(getItem1());
        items.add(getItem2());
        items.add(getItem3());
        return items;
    }
}
