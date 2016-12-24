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

    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {

        return this.title;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
