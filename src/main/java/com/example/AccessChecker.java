package com.example;

import com.example.dao.UserDAO;
import com.example.entities.User;
import com.example.entities.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Amir Shams on 1/15/2017.
 */
public class AccessChecker {

    public static boolean hasAccessTo(String api)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = UserDAO.getInstance().findByUserName(name);

        //TODO
        //map some feature access to userRole
        //implement access checker if needed

        return false;
    }
}
