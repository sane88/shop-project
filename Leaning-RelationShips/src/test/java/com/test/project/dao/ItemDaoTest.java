package com.test.project.dao;

import com.test.project.domain.Item;
import com.test.project.domain.User;
import com.test.project.mocks.ItemMock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Valentin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
public class ItemDaoTest {

    @Autowired
    private DAO<Item, Integer> itemDao;
    @Autowired
    private SessionFactory sessionFactory;
    private static Item item1;
    private static Collection<Item> items;


    @After
    public void tearDown() throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.createSQLQuery("DELETE FROM ITEMS").executeUpdate();
        } finally {
            session.close();
        }
    }

    @Before
    public void setUp() throws Exception {
        item1 = ItemMock.getItem1();
        items = ItemMock.getItems();
    }

    @Test
    public void testGet() throws Exception {
        insertItem(item1);
        Item testItem = itemDao.get(item1.getItemId());
        assertEquals(item1, testItem);
    }

    @Test
    public void testGetAll() throws Exception {
        insertItems(items);
        Collection<Item> testItems = itemDao.getAll();
        for (Item item : items) {
            assertTrue(testItems.contains(item));
        }
    }

    @Test
    public void testAdd() throws Exception {
        itemDao.add(item1);
        Item result = getItem(item1.getItemId());
        assertEquals(item1, result);
    }

    @Test
    public void testUpdate() throws Exception {
        insertItem(item1);
        item1.setName("");
        itemDao.update(item1);
        Item result = getItem(item1.getItemId());
        assertEquals(item1, result);
    }

    private Item getItem(Integer id) {
        Session session = sessionFactory.openSession();
        Item result;
        try {
            result = (Item) session.get(Item.class, id);
        } finally {
            session.close();
        }
        return result;
    }

    private void insertItems(Collection<Item> items) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            for (Item item : items) {
                session.save(item);
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    private void insertUser(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.save(user);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    private void insertItem(Item item) {
        Collection<Item> items = new ArrayList<Item>();
        items.add(item);
        insertItems(items);
    }
}
