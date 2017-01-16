package com.example.security;

import com.example.dao.UserDAO;
import com.example.entities.User;

import javax.servlet.http.HttpSession;

/**
 * Created by Amir Shams on 1/15/2017.
 */
public class AccessHandler {


    public static boolean hasAccess(String token,boolean requiredAdmin,HttpSession session)
    {
        if(token == null)
            return false;

        if(session.isNew()) {

            User user = UserDAO.getInstance().getByAccessToken(token);

            if(user != null)
            {
                session.setAttribute("isLoggedIn","true");
                session.setAttribute("token",token);
                session.setAttribute("role",user.getRole());
                session.setAttribute("username",user.getUsername());
            }

            return user != null && (!requiredAdmin || user.getRole().equals("admin"));

        }
        else {
            String isLoggedIn = (String) session.getAttribute("getByAccessToken");
            String sessionToken = (String) session.getAttribute("token");
            if(isLoggedIn != null && isLoggedIn.equals("true") && sessionToken.equals(token)) {

                return !requiredAdmin || session.getAttribute("role").equals("admin");
            }
            else{
                User user = UserDAO.getInstance().getByAccessToken(token);
                if(user != null) {
                    session.setAttribute("isLoggedIn", "true");
                    session.setAttribute("token", token);
                    session.setAttribute("role", user.getRole());
                    session.setAttribute("username",user.getUsername());
                }

                return user != null && (!requiredAdmin || user.getRole().equals("admin"));
            }
        }
    }

    public static void setAccessToken(String accessToken,String role,HttpSession session,String username)
    {
        session.setAttribute("isLoggedIn","true");
        session.setAttribute("token",accessToken);
        session.setAttribute("role",role);
        session.setAttribute("username",username);
    }
}
