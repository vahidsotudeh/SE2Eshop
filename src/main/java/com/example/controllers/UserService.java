package com.example.controllers;

import com.example.dao.UserDAO;

import com.example.entities.User;
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

        UserDAO.getInstance().setTokenByUsername(username,accessToken);

//        AccessHandler.setAccessToken(accessToken,user.getRole(),session,user.getUsername());

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
    public Response logout(@QueryParam("token") String token)
    {
        UserDAO.getInstance().setToken(token,"");

        return Response.ok().build();
    }

    @GET
    @Path("register")
    public Response register(@QueryParam("username")String username,
                             @QueryParam("password")String password,
                             @QueryParam("nameFa")String nameFa,
                             @QueryParam("phoneNumber")String phoneNumber,
                             @QueryParam("address")String address)
    {
        User user = UserDAO.getInstance().findByUserName(username);

        if(user != null)
            return Response.status(Response.Status.FORBIDDEN).build();

        User newUser = new User();

        newUser.setPhoneNumber(phoneNumber);
        newUser.setAddress(address);
        newUser.setNameFa(nameFa);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("user");

        UserDAO.getInstance().add(newUser);

        String accessToken = new BigInteger(130, new SecureRandom()).toString(32);

        UserDAO.getInstance().setTokenByUsername(username,accessToken);

//        AccessHandler.setAccessToken(accessToken,user.getRole(),session,user.getUsername());

        return Response.ok(accessToken).build();
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
