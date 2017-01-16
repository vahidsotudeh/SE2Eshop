package com.example.entities;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Amir Shams on 12/23/2016.
 */
@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @Column
    private String age;

    @Column
    private Date publicationDate;

    @Column
    private int count;

    @Column
    private boolean hasCommentary;

    @Column
    private String category;

    @Column
    private String ISBN;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "book")
    private Set<Comment> comments = new HashSet<>();


    public String getISBN() {
        return ISBN;
    }


    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Column
    private String summary;

    @Column
    private String review;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHasCommentary() {
        return hasCommentary;
    }

    public void setHasCommentary(boolean hasCommentary) {
        this.hasCommentary = hasCommentary;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
