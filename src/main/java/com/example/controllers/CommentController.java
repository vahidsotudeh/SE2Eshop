package com.example.controllers;

import com.example.dao.CommentDAO;
import com.example.entities.Comment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Amir Shams on 1/16/2017.
 */
@Path("api/books")
public class CommentController {

    @GET
    @Path("{id}/comments")
    @Produces("application/json")
    public Response getComments(@PathParam("id") String bookId)
    {
        List<Comment> comments = CommentDAO.getInstance().getByBookId(bookId);

        return Response.ok(comments).build();
    }
}
