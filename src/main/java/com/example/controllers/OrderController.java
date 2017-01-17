package com.example.controllers;

import com.example.dao.BookDAO;
import com.example.dao.OrderDAO;
import com.example.dto.BookLightDTO;
import com.example.entities.Book;
import com.example.entities.Order;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
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
}
