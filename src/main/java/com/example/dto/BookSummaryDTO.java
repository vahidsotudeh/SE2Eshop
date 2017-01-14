package com.example.dto;

import com.example.entities.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir Shams on 1/14/2017.
 */
public class BookSummaryDTO {

    @JsonProperty
    private ArrayList<BookDTO> list;

    public void setList(ArrayList<BookDTO> list) {
        this.list = list;
    }

    public ArrayList<BookDTO> getList() {

        return list;
    }

    public BookSummaryDTO (List<Book> bookList)
    {
        this.list = new ArrayList<>();
        for(Book book : bookList)
            list.add(BookDTO.loadFrom(book));
    }
}
