package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Amir Shams on 12/23/2016.
 */
@Entity
@Table(name="book")
public class Book {

    private String id;

    @Column
    private String title;

    @Column
    private long price;

    @Column
    private long score;

    @Column
    private String imageAddress;

    @Column
    private String author;

    @Column
    private int saleCount;

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {

        return this.title;
    }
    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
