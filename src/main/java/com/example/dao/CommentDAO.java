package com.example.dao;

import com.example.entities.Book;
import com.example.entities.Comment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir Shams on 1/16/2017.
 */
public class CommentDAO {

    private static CommentDAO instance;
    public static CommentDAO getInstance()
    {
        if(instance == null)
            instance = new CommentDAO();
        return instance;
    }
    private CommentDAO()
    {

    }

    public List<Comment> getByBookId(String bookId)
    {
        Criteria criteria = createCriteria();

        criteria.createAlias("comment.book","book");

        criteria.add(Restrictions.eq("book.id",bookId));

        return criteria.list();
    }

    private Criteria createCriteria()
    {
        SessionFactory sessionFactory = Factory.getSessionFactory();
        Session session = sessionFactory.openSession();

        return session.createCriteria(Comment.class);
    }
}
