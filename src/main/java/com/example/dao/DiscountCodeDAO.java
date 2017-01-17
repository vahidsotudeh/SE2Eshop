package com.example.dao;

import com.example.entities.Book;
import com.example.entities.DiscountCode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Amir Shams on 1/17/2017.
 */
public class DiscountCodeDAO {
    private static DiscountCodeDAO instance;
    public static DiscountCodeDAO getInstance()
    {
        if(instance == null)
            instance = new DiscountCodeDAO();
        return instance;
    }
    private DiscountCodeDAO()
    {

    }

    public List<DiscountCode> getAll()
    {
        Criteria criteria = createCriteria();

        return criteria.list();
    }

    public int getCode(String code)
    {
        Criteria criteria = createCriteria();

        criteria.add(Restrictions.eq("code",code));
        criteria.add(Restrictions.eq("isEnabled","yes"));

        List<DiscountCode> codes = criteria.list();

        if(codes.size() == 0)
            return 0;
        else
            return codes.get(0).getAmount();
    }

    private Criteria createCriteria()
    {
        SessionFactory sessionFactory = Factory.getSessionFactory();
        Session session = sessionFactory.openSession();

        return session.createCriteria(DiscountCode.class);
    }
}
