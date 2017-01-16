package com.example.controllers;

import com.example.dao.OrderDAO;
import com.example.dao.PaymentDAO;
import com.example.entities.Order;
import com.example.entities.Payment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Amir Shams on 1/17/2017.
 */
@Path("api")
public class PaymentController {

    @GET
    @Path("payments")
    @Produces("application/json")
    public Response getPayments() throws IOException {

        List<Payment> payments = PaymentDAO.getInstance().getAll();

        return Response.ok().entity(payments).build();
    }
}
