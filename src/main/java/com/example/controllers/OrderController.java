package com.example.controllers;

import com.example.dao.BookDAO;
import com.example.dao.OrderDAO;
import com.example.dao.UserDAO;
import com.example.dto.OrderDTO;
import com.example.dto.SingleOrderDTO;
import com.example.entities.Book;
import com.example.entities.Order;
import com.example.entities.User;
import com.example.handlers.OrderServiceHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
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

}
