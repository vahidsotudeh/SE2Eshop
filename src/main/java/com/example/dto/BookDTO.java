package com.example.dto;

import com.example.entities.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Amir Shams on 12/14/2016.
 */
public class BookDTO {

    @JsonProperty
    private String title;

    @JsonProperty
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static BookDTO loadFrom(Book book)
    {
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setId(book.getId());

        return dto;
    }

}
