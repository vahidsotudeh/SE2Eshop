package com.example.controllers;

import com.example.dao.OrderDAO;
import com.example.dao.UserDAO;
import com.example.dto.LoginDTO;
import com.example.dto.RegistrationDTO;
import com.example.entities.Order;
import com.example.entities.User;
import com.example.security.AccessHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by Amir Shams on 1/15/2017.
 */

@Path("api")
public class UserService {

    @Autowired
    HttpSession session;

    @GET
    @Path("login")
    public Response login(@QueryParam("username")String username , @QueryParam("password")String password)
    {
        User user = UserDAO.getInstance().authorize(username,password);

        if(user == null)
            return Response.status(Response.Status.FORBIDDEN).build();

        String accessToken = new BigInteger(130, new SecureRandom()).toString(32);

        UserDAO.getInstance().setToken(username,accessToken);

        AccessHandler.setAccessToken(accessToken,user.getRole(),session,user.getUsername());

        return Response.ok().entity(accessToken).build();
    }

    @GET
    @Path("users")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getUsers() throws IOException {

        List<User> users = UserDAO.getInstance().getAll();

        return Response.ok().entity(users).build();
    }

    @GET
    @Path("logout")
    public Response logout()
    {
        UserDAO.getInstance().setToken((String) session.getAttribute("username"),"");

        return Response.ok().build();
    }

    @POST
    @Path("register")
    @Produces("application/json")
    @Consumes("application/json")
    public Response register(RegistrationDTO dto)
    {
        User user = UserDAO.getInstance().findByUserName(dto.getUsername());

        if(user != null)
            return Response.ok("username exists").build();

        User newUser = new User();

        newUser.setNameEn(dto.getNameEn());
        newUser.setNameFa(dto.getNameFa());
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(dto.getPassword());
        newUser.setRole("user");

        UserDAO.getInstance().add(newUser);

        return Response.ok("registered successfully").build();
    }

    @GET
    @Path("status")
    @Produces("application/json")
    public Response getStatus(@QueryParam("token") String token)
    {
        if(token == null)
            return Response.ok().build();
        else
        {
            User user = UserDAO.getInstance().getByAccessToken(token);
            return Response.ok(user).build();
        }
    }
}
