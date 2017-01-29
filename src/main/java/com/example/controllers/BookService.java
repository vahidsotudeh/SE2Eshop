package com.example.controllers;

import com.example.dao.BookDAO;
import com.example.dto.BookLightDTO;
import com.example.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
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


    @Autowired
    HttpSession session;

    @GET
    @Path("book-summary")
    @Produces("application/json")
    public Response getBookSummary(
            @DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("len") int len,
            @DefaultValue("bookId") @QueryParam("order") String orderBy) throws IOException {

        List<Book> books = BookDAO.getInstance().getOrderedBooks(start,len,orderBy);
        List<BookLightDTO> result = new ArrayList<>();

        for(Book book : books)
            result.add(BookLightDTO.loadFrom(book));

        return Response.ok().entity(result).build();
    }

}
