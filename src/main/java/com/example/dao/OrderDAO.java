package com.example.dao;

import com.example.entities.Book;
import com.example.entities.Comment;
import com.example.entities.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
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

    private Criteria createCriteria()
    {
        Session session = Factory.getSessionCueentSession();

        return session.createCriteria(Order.class);
    }
}
