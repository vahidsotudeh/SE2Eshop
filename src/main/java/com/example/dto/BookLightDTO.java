package com.example.dto;

import com.example.entities.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Amir Shams on 12/14/2016.
 */
public class BookLightDTO {

    @JsonProperty
    private String title;

    @JsonProperty
    private String id;

    @JsonProperty
    private String imageAddress;

    @JsonProperty
    private String author;

    @JsonProperty
    private long price;

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

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

    public static BookLightDTO loadFrom(Book book)
    {
        BookLightDTO result = new BookLightDTO();

        result.setId(book.getId());
        result.setAuthor(book.getAuthor());
        result.setPrice(book.getPrice());
        result.setImageAddress(book.getImageAddress());
        result.setTitle(book.getTitle());

        return result;
    }


}
