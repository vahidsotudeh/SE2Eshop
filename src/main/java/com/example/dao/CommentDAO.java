package com.example.dao;

import com.example.entities.Comment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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

        List<Comment> list = criteria.list();

        return list;
    }

    public List<Comment> getAll()
    {
        Criteria criteria = createCriteria();

        return criteria.list();
    }

    private Criteria createCriteria()
    {
        Session session = HibernateUtils.getSession();

        return session.createCriteria(Comment.class);
    }
}
