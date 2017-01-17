package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.ws.rs.Consumes;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amir Shams on 1/16/2017.
 */
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long orderId;

    @Column
    private Date orderDate;

    @Column
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<BookOrderAssignment> getBookOrderAssignments() {
        return bookOrderAssignments;
    }

    public void setBookOrderAssignments(Set<BookOrderAssignment> bookOrderAssignments) {
        this.bookOrderAssignments = bookOrderAssignments;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
    private Set<BookOrderAssignment> bookOrderAssignments = new HashSet<>();

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
