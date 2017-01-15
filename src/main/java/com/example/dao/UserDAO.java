package com.example.dao;

import com.example.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDAO{

    private static UserDAO instance;
    public static UserDAO getInstance()
    {
        if(instance == null)
            instance = new UserDAO();
        return instance;
    }

    private UserDAO()
    {

    }


    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {

        Criteria criteria = createCriteria();

        criteria.add(Restrictions.eq("username",username));

        List<User> users = criteria.list();

        System.out.println(users);

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    private Criteria createCriteria()
    {
        SessionFactory sessionFactory = Factory.getSessionFactory();
        Session session = sessionFactory.openSession();

        return session.createCriteria(User.class);
    }
}