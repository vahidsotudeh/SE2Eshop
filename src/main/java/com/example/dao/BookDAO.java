package com.example.dao;


import com.example.entities.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Amir Shams on 12/23/2016.
 */
public class BookDAO {

    private static BookDAO instance;
    public static BookDAO getInstance()
    {
        if(instance == null)
            instance = new BookDAO();
        return instance;
    }
    private BookDAO()
    {

    }
    public Book getById(String id)
    {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("id",id));

        return (Book) criteria.list().get(0);
    }
    public void add(Book book)
    {
        SessionFactory sessionFactory = Factory.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();

    }
    private Criteria createCriteria()
    {
        SessionFactory sessionFactory = Factory.getSessionFactory();
        Session session = sessionFactory.openSession();

        return session.createCriteria(Book.class);
    }
}
