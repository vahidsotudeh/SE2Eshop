package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Amir Shams on 1/16/2017.
 */
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private long orderId;

    @Column
    private int price;

    @Column
    private Date paymentDate;

    @Column
    private String code;

    @Column
    private String type;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Order order;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
