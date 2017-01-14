package com.example.controllers;

import com.example.dao.BookDAO;
import com.example.dto.BookDTO;
import com.example.dto.BookSummaryDTO;
import com.example.entities.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir Shams on 1/14/2017.
 */
@Path("api")
public class BookService {

    @GET
    @Path("book-summary")
    @Produces("application/json")
    public Response getBookSummary(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("len") int len,
            @DefaultValue("score") @QueryParam("order") String orderBy) throws IOException {

        List<Book> result = BookDAO.getInstance().getOrderedBooks(start,len,orderBy);

        return Response.ok().entity(result).build();
    }
}
