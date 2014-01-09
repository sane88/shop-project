package com.test.project.service;

import com.test.project.domain.Item;
import com.test.project.domain.User;
import com.test.project.mocks.ItemDaoMock;
import com.test.project.mocks.ItemMock;
import com.test.project.mocks.UserDaoMock;
import com.test.project.mocks.UserMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Valentin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
public class MainServiceTest {

    @Autowired
    private MainService service;
    private User user;
    private Collection<Item> items;
    private UserDaoMock userDao;
    private ItemDaoMock itemDaoMock;
    private Item item1;
    private Item item2;


    @Before
    public void setUp() throws Exception {
        user = UserMock.getUser1();
        items = ItemMock.getItems();
        item1 = ItemMock.getItem1();
        item2 = ItemMock.getItem2();
        userDao = new UserDaoMock();
        itemDaoMock = new ItemDaoMock();
        service.setUserDao(userDao);
        service.setItemDao(itemDaoMock);
    }

    @Test
    public void testGetAllItems() throws Exception {
        ItemDaoMock itemDaoMock = new ItemDaoMock();
        service.setItemDao(itemDaoMock);
        for (Item item : service.getAllItems()) {
            assertTrue(items.contains(item));
        }
    }

    @Test
    public void testGetUser() throws Exception {
        assertEquals(user, service.getUser(user.getUsername()));
    }

    @Test
    public void testAddUser() throws Exception {
        service.addUser(user);
        assertEquals(user, userDao.getUser());
    }

    @Test
    public void testAddPurchasedItem() throws Exception {
        service.addPurchasedItem(user.getUsername(), item1.getItemId());
        assertTrue(userDao.getUser().getPurchasedItems().contains(item1));
        assertTrue(!userDao.getUser().getWishList().contains(item1));
    }

    @Test
    public void testAddWishedItem() throws Exception {
        service.addWishedItem(user.getUsername(), item2.getItemId());
        assertTrue(userDao.getUser().getWishList().contains(item2));
    }

    @Test
    public void testRemoveFromWishlist() throws Exception {
        service.removeFromWishlist(user.getUsername(), item1.getItemId());
        assertTrue(!userDao.getUser().getWishList().contains(item1));
    }

    @Test
    public void testGetPurchasedItems() throws Exception {
        service.setUserDao(userDao);
        Collection<Item> purchasedItems = service.getPurchasedItems(user.getUsername());
        for (Item purchasedItem : purchasedItems) {
            assertTrue(userDao.getUser().getPurchasedItems().contains(purchasedItem));
        }
    }

    @Test
    public void testGetWishList() throws Exception {
        Collection<Item> wishList = service.getWishList(user.getUsername());
        for (Item item : wishList) {
            assertTrue(userDao.getUser().getWishList().contains(item));
        }
    }
}
