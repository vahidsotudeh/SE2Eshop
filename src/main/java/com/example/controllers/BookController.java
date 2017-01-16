package com.example.controllers;


import com.example.dto.BookDTO;
import com.example.dao.BookDAO;
import com.example.entities.Book;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public Response addBook(BookDTO dto)
    {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());

        BookDAO.getInstance().add(book);

        return Response.ok().build();
    }

}
