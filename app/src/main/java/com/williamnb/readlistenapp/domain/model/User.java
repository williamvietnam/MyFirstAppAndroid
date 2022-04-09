package com.williamnb.readlistenapp.domain.model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String email;
    private String token;
    private String name;
    private String image;

    public User() {

    }

    public User(String id, String email, String token, String name, String image) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.name = name;
        this.image = image;
    }

    public User(String email, String token, String name, String image) {
        this.email = email;
        this.token = token;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
