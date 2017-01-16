package com.example.controllers;


import com.example.dto.BookFullDTO;
import com.example.dto.BookLightDTO;
import com.example.dao.BookDAO;
import com.example.entities.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Amir Shams on 12/14/2016.
 */

@Path("api/books")
public class BookController {

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getBook(@PathParam("id") String id) throws IOException {

        Book book = BookDAO.getInstance().getById(id);

        if(book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(book).build();
    }

    @POST
    @Path("add")
    @Consumes("application/json")
    public Response addBook(BookFullDTO dto)
    {
//        Book book = new Book();
//        book.setId(dto.getId());
//        book.setTitle(dto.getTitle());
//
//        BookDAO.getInstance().add(book);

        //TODO
        //needs implementation

        return Response.ok().build();
    }

}
