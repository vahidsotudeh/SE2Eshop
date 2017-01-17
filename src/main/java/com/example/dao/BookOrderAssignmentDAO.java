package com.example.dao;

import com.example.entities.Book;
import com.example.entities.BookOrderAssignment;
import com.example.entities.Comment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir Shams on 1/17/2017.
 */
public class BookOrderAssignmentDAO {
    private static BookOrderAssignmentDAO instance;
    public static BookOrderAssignmentDAO getInstance()
    {
        if(instance == null)
            instance = new BookOrderAssignmentDAO();
        return instance;
    }
    private BookOrderAssignmentDAO()
    {

    }

    public void save(ArrayList<BookOrderAssignment> assignments)
    {
        Session session = Factory.getSessionCueentSession();

        session.beginTransaction();
        for(BookOrderAssignment assignment : assignments)
            session.save(assignment);
        session.getTransaction().commit();
    }

    public List<BookOrderAssignment> getAll()
    {
        Criteria criteria = createCriteria();

        return criteria.list();
    }

    private Criteria createCriteria()
    {
        Session session = Factory.getSessionCueentSession();

        return session.createCriteria(BookOrderAssignment.class);
    }
}
