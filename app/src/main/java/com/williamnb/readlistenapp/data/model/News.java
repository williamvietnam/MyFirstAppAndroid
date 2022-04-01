package com.williamnb.readlistenapp.data.model;

import android.widget.ImageView;

public class News {
    private int imvThumbnail;
    private int content;
    private int date;
    private int numberView;

    public News(int imvThumbnail, int content, int date, int numberView) {
        this.imvThumbnail = imvThumbnail;
        this.content = content;
        this.date = date;
        this.numberView = numberView;
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
