package com.example.dao;

import com.example.entities.BookOrderAssignment;
import org.hibernate.Criteria;
import org.hibernate.Session;

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

//    public void save(ArrayList<BookOrderAssignment> assignments)
//    {
//        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//        for(BookOrderAssignment assignment : assignments)
//            session.save(assignment);
//        session.getTransaction().commit();
//    }

    public List<BookOrderAssignment> getAll()
    {
        Criteria criteria = createCriteria();

        return criteria.list();
    }

    private Criteria createCriteria()
    {
        Session session = HibernateUtils.getSession();

        return session.createCriteria(BookOrderAssignment.class);
    }
}
