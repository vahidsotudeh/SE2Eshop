package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @JsonIgnore
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    @JsonIgnore
    private String accessToken;
    @Column
    private String nameFa;
    @Column
    private String nameEn;
    @Column
    @JsonIgnore
    private String role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

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