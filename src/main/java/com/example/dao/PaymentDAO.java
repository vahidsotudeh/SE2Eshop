package com.example.dao;

import com.example.entities.Payment;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Amir Shams on 1/17/2017.
 */
public class PaymentDAO {
    private static PaymentDAO instance;
    public static PaymentDAO getInstance()
    {
        if(instance == null)
            instance = new PaymentDAO();
        return instance;
    }
    private PaymentDAO()
    {

    }

    public List<Payment> getAll()
    {
        Criteria criteria = createCriteria();

        return criteria.list();
    }

    private Criteria createCriteria()
    {
        Session session = HibernateUtils.getSession();

        return session.createCriteria(Payment.class);
    }
}
