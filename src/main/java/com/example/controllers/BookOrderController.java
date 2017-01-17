package com.example.controllers;

import com.example.dao.BookOrderAssignmentDAO;
import com.example.dao.CommentDAO;
import com.example.entities.BookOrderAssignment;
import com.example.entities.Comment;

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
public class BookOrderController {

    @GET
    @Path("book-order")
    @Produces("application/json")
    public Response getBookOrders() throws IOException {

        List<BookOrderAssignment> bookOrderAssignments = BookOrderAssignmentDAO.getInstance().getAll();

        return Response.ok().entity(bookOrderAssignments).build();
    }
}
