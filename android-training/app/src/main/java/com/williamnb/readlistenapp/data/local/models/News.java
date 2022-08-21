package com.williamnb.readlistenapp.data.local.models;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public class News {
    private String id;
    private int imvThumbnail;
    private int content;
    private int date;
    private int numberView;

    public News(String id, int imvThumbnail, int content, int date, int numberView) {
        this.id = id;
        this.imvThumbnail = imvThumbnail;
        this.content = content;
        this.date = date;
        this.numberView = numberView;
    }

    public News(int imvThumbnail, int content, int date, int numberView) {
        this.imvThumbnail = imvThumbnail;
        this.content = content;
        this.date = date;
        this.numberView = numberView;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImvThumbnail() {
        return imvThumbnail;
    }

    public void setImvThumbnail(int imvThumbnail) {
        this.imvThumbnail = imvThumbnail;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getNumberView() {
        return numberView;
    }

    public void setNumberView(int numberView) {
        this.numberView = numberView;
    }
}
