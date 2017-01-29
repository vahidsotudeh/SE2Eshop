package com.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {


    private static Session session;
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory getSessionFactory() {
        if(sessionFactory !=null && sessionFactory.isOpen())
            return sessionFactory;
        sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static Session getSession()
    {
        if(session != null && session.isOpen())
            return session;
        System.out.println("session closed !!!!!");

        session = getSessionFactory().openSession();

        return session;
    }

//    public static void shutdown() {
//        // Close caches and connection pools
//        getSessionFactory().close();
//    }
}