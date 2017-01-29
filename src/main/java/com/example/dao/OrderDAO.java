package com.example.dao;

import com.example.entities.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Amir Shams on 1/17/2017.
 */
public class OrderDAO {

    private static OrderDAO instance;
    public static OrderDAO getInstance()
    {
        if(instance == null)
            instance = new OrderDAO();
        return instance;
    }
    private OrderDAO()
    {

    }

    public List<Order> getAll()
    {
        Criteria criteria = createCriteria();

        return criteria.list();
    }

    public Order orderExistForUser(String username)
    {
        Criteria criteria = createCriteria();
        criteria.createAlias("user","user");
        criteria.add(Restrictions.eq("user.username",username));
        criteria.add(Restrictions.eq("status","new"));
        List<Order> orders = criteria.list();


        if(orders.isEmpty())
            return null;
        else
            return orders.get(0);
    }

    private Criteria createCriteria()
    {
        Session session = HibernateUtils.getSession();

        return session.createCriteria(Order.class);
    }
}
