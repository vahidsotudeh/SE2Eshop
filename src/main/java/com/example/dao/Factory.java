package com.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by Amir Shams on 12/5/2016.
 */
public class Factory {

    private static Session session = buildSessionFactory().openSession();
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory()
    {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure( "hibernate.cfg.xml" )
                .build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .getMetadataBuilder()
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }
    public static Session   getSessionCueentSession()
    {
        if(session.isOpen())
            return session;
        session = buildSessionFactory().openSession();

        return session;
    }
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void closeSession()
    {
        if(session.isOpen())
            session.close();
    }


}