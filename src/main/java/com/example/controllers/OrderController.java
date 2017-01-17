package com.example.controllers;

import com.example.dao.BookDAO;
import com.example.dao.Factory;
import com.example.dao.OrderDAO;
import com.example.dao.UserDAO;
import com.example.dto.OrderDTO;
import com.example.dto.SingleOrderDTO;
import com.example.entities.Book;
import com.example.entities.Order;
import com.example.entities.User;
import com.example.handlers.OrderServiceHandler;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir Shams on 1/17/2017.
 */
@Path("api")
public class OrderController {

    @GET
    @Path("orders")
    @Produces("application/json")
    public Response getOrders() throws IOException {

        List<Order> orders = OrderDAO.getInstance().getAll();

        return Response.ok().entity(orders).build();
    }

    @POST
    @Path("orderBooks")
    @Produces("application/json")
    public Response orderBooks(OrderDTO dto)
    {
        return new OrderServiceHandler(dto).handle();
    }

    @GET
    @Path("orderStat")
    @Produces("application/json")
    public Response getStat(@QueryParam("token") String token)
    {
        User user = UserDAO.getInstance().getByAccessToken(token);

        if(user == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        Order order = OrderDAO.getInstance().orderExistForUser(user.getUsername());

        if(order == null)
            return Response.status(Response.Status.NOT_FOUND).build();

//        System.out.println(order.getPrice());
        return Response.ok().entity(order).build();
    }

    @GET
    @Path("getTrack")
    @Produces("application/json")
    public Response getTrackingCode(@QueryParam("token") String token)
    {
        User user = UserDAO.getInstance().getByAccessToken(token);

        if(user == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        Order order = OrderDAO.getInstance().orderExistForUser(user.getUsername());

        if(order == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        String code = new BigInteger(130, new SecureRandom()).toString(32);

        order.setStatus("paid");

        //add payment record to its table

        Session session = Factory.getSessionCueentSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        Factory.closeSession();

        return Response.ok().entity(code).build();
    }

}
