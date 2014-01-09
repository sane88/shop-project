package com.test.project.dao;

import com.test.project.domain.Item;
import com.test.project.domain.User;
import com.test.project.mocks.ItemMock;
import com.test.project.mocks.UserMock;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Valentin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
public class UserDaoTest {

    @Autowired
    private DAO<User, String> userDao;
    @Autowired
    private SessionFactory sessionFactory;
    private User user1;

    @Before
    public void setUp() throws Exception {
        user1 = UserMock.getUser1();
    }

    @After
    public void tearDown() throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.createSQLQuery("truncate table BOUGHT_ITEMS").executeUpdate();
            session.createSQLQuery("truncate table WISH_LIST").executeUpdate();
            session.createSQLQuery("DELETE FROM USERS").executeUpdate();
        }  catch (Throwable t){
            session.getTransaction().rollback();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Test
    public void testGet() throws Exception {
        insertUser(user1);
        User resultUser = userDao.get(user1.getUsername());
        assertEquals(user1, resultUser);
    }

    @Test
    public void testAdd() throws Exception {
        userDao.add(user1);
        User resultUser = getUser(user1.getUsername());
        assertEquals(user1, resultUser);
    }

    @Test
    public void testUpdate() throws Exception {
        Item item3 = ItemMock.getItem3();
        Item item2 = ItemMock.getItem2();
        insertItem(item3);
        insertItem(item2);
        insertUser(user1);
        user1.addPurchasedItem(item3);
        user1.addWishedItem(item2);
        userDao.update(user1);
        User resultUser = getUser(user1.getUsername());
        assertEquals(user1, resultUser);
        assertTrue(resultUser.getPurchasedItems().contains(item3));
        assertTrue(resultUser.getWishList().contains(item2));

    }

    private User getUser(String username){
        Session session = sessionFactory.openSession();
        User result;
        try {
            result = (User) session.get(User.class, username);
            Hibernate.initialize(result.getPurchasedItems());
            Hibernate.initialize(result.getWishList());
        } finally {
            session.close();
        }
        return result;
    }

    private void insertItem(Item item){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.save(item);
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
        } catch (Throwable throwable){
            session.getTransaction().rollback();
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
