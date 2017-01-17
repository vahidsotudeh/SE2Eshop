package com.example.dao;


import com.example.entities.User;
import com.sun.org.apache.bcel.internal.generic.FADD;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


import java.util.List;

public class UserDAO{

    private static UserDAO instance;
    public static UserDAO getInstance()
    {
        if(instance == null)
            instance = new UserDAO();
        return instance;
    }

    private UserDAO()
    {

    }

    public void add(User user)
    {

        Session session = Factory.getSessionCueentSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        session.close();
    }


    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {

        Criteria criteria = createCriteria();

        criteria.add(Restrictions.eq("username",username));

        List<User> users = criteria.list();

        Factory.getSessionCueentSession().close();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    public List<User> getAll()
    {
        Criteria criteria = createCriteria();

        return criteria.list();
    }

    public User getByAccessToken(String accessToken)
    {
        Criteria criteria = createCriteria();

        criteria.add(Restrictions.eq("accessToken",accessToken));

        List<User> users = criteria.list();

        Factory.getSessionCueentSession().close();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }


    public User authorize(String username ,String password)
    {
        Criteria criteria = createCriteria();

        criteria.add(Restrictions.eq("username",username));
        criteria.add(Restrictions.eq("password",password));

        List<User> users = criteria.list();

        Factory.closeSession();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
    public void setTokenByUsername(String username,String newToken)
    {
        Session session = Factory.getSessionCueentSession();

        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.eq("username",username));

        List<User> users = criteria.list();

        User user = users.get(0);

        user.setAccessToken(newToken);

        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        Factory.closeSession();

    }

    public void setToken(String token,String newToken)
    {
        Session session = Factory.getSessionCueentSession();

        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.eq("accessToken",token));

        List<User> users = criteria.list();

        if(users.size() == 0)
            return;

        User user = users.get(0);

        user.setAccessToken(newToken);

        session.beginTransaction();

        session.update(user);
        session.getTransaction().commit();

        Factory.closeSession();

    }

    private Criteria createCriteria()
    {
        Session session = Factory.getSessionCueentSession();
        return session.createCriteria(User.class);
    }
}