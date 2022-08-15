package com.williamnb.readlistenapp.data.local.models;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public class Game {
    private String id;
    private int logo;
    private int title;

    public Game(String id, int logo, int title) {
        this.id = id;
        this.logo = logo;
        this.title = title;
    }

    public Game(int logo, int title) {
        this.logo = logo;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
