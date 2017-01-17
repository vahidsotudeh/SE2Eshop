package com.example.dao;


import com.example.entities.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

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

        return (Book) criteria.list().get(0);
    }

    public List<Book> getOrderedBooks(int start, int len, String orderBy)
    {
        Criteria criteria = createCriteria();

        criteria.addOrder(Order.desc(orderBy)).setFirstResult(start).setMaxResults(len);

        return criteria.list();
    }

    public void add(Book book)
    {
        Session session = Factory.getSessionCueentSession();

        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();

    }

    public void save(List<Book> books)
    {
        Session session = Factory.getSessionCueentSession();

        session.beginTransaction();
        for(Book book : books)
            session.update(book);
        session.getTransaction().commit();
    }

    public List<Book> getBooks(ArrayList<String> bookIds)
    {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.in("bookId",bookIds));

        return criteria.list();
    }
    private Criteria createCriteria()
    {
        Session session = Factory.getSessionCueentSession();

        return session.createCriteria(Book.class);
    }
}
