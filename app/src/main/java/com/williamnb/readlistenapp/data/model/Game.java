package com.williamnb.readlistenapp.data.model;

import android.widget.ImageView;

public class Game {
    private int logo;
    private int title;

    public Game(int logo, int title) {
        this.logo = logo;
        this.title = title;
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
