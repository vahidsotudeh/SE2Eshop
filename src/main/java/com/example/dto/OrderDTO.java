package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Amir Shams on 1/17/2017.
 */
public class OrderDTO {

    @JsonProperty
    private String token;

    @JsonProperty
    private ArrayList<SingleOrderDTO> items;

    @JsonProperty
    private String discountCode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<SingleOrderDTO> getOrders() {
        return items;
    }

    public void setOrders(ArrayList<SingleOrderDTO> orders) {
        this.items = orders;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
