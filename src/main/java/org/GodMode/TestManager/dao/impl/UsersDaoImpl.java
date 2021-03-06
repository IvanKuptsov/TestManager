package org.GodMode.TestManager.dao.impl;

import org.GodMode.TestManager.dao.Dao;
import org.GodMode.TestManager.dao.utils.HibernateUtil;
import org.GodMode.TestManager.entities.Users;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ozinoviev on 5/4/2016.
 */
public class UsersDaoImpl implements Dao<Users, Long> {

    @Autowired
    HibernateUtil hibernateUtil;

    @SuppressWarnings("unchecked")
    public List findAll() {
        Session session = hibernateUtil.getSessionFactory().openSession();
        List<Users> usersList = session.createQuery("FROM Users").list();
        session.close();
        return usersList;
    }

    public Users find(Long id) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Users users = (Users) session.get(Users.class, id);
        session.close();
        return users;
    }

    public void saveOrUpdate(Users entry) {
        if (entry == null) return;
        Session session = hibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entry);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Users entry) {
        if (entry == null) return;
        Session session = hibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entry);
        session.getTransaction().commit();
        session.close();
    }
}
