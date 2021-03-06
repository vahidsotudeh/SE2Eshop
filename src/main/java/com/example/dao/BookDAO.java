package com.example.dao;


import com.example.entities.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

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
        criteria.add(Restrictions.eq("bookId",id));

        List<Book> books = criteria.list();

        if(books.size() == 0)
            return null;
        Book book =  books.get(0);
        return book;
    }

    public List<Book> getOrderedBooks(int start, int len, String orderBy)
    {
        Criteria criteria = createCriteria();

        criteria.addOrder(Order.desc(orderBy)).setFirstResult(start).setMaxResults(len);

        List<Book> books = criteria.list();

        return books;
    }
//
//    public void add(Book book)
//    {
//        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//        session.save(book);
//        session.getTransaction().commit();
//
//    }


    public List<Book> getBooks(ArrayList<String> bookIds)
    {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.in("bookId",bookIds));

        List<Book> books = criteria.list();

        return books;
    }
    private Criteria createCriteria()
    {

        Session session = HibernateUtils.getSession();

        return session.createCriteria(Book.class);
    }
}
