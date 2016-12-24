package com.example.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by Amir Shams on 12/5/2016.
 */
public class Factory {

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
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void shutdown()
    {
        getSessionFactory().close();
    }
}
