package com.example.controllers;

import com.example.dao.CommentDAO;
import com.example.dao.OrderDAO;
import com.example.entities.Comment;
import com.example.entities.Order;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Amir Shams on 1/16/2017.
 */
@Path("api")
public class CommentController {

    @GET
    @Path("comments")
    @Produces("application/json")
    public Response getComments() throws IOException {

        List<Comment> comments = CommentDAO.getInstance().getAll();

        return Response.ok().entity(comments).build();
    }

}
