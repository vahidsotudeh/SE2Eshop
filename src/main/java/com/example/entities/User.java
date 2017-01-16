package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String accessToken;
    @Column
    private String nameFa;
    @Column
    private String nameEn;
    @Column
    private String role;


    public String getNameFa() {
        return nameFa;
    }

    public void setNameFa(String nameFa) {
        this.nameFa = nameFa;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String userRole) {
        this.role = userRole;
    }


    public User() {
    }

    public User(String username, String password, String accessToken, String nameFa, String nameEn, String userRole) {
        this.username = username;
        this.password = password;
        this.accessToken = accessToken;
        this.nameFa = nameFa;
        this.nameEn = nameEn;
        this.role = userRole;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Id
    @Column(name = "username", unique = true,
            nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}