package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amir Shams on 1/16/2017.
 */
@Entity
@Table(name = "discount_code")
public class DiscountCode {

    @Id
    private String code;

    @Column
    private int amount;

    @Column
    private String isEnabled;

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String isEnabled() {
        return isEnabled;
    }

    public void setEnabled(String enabled) {
        isEnabled = enabled;
    }
}
