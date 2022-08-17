package com.williamnb.readlistenapp.data.local.models;

public class Welcome {
    private int text;
    private int image;

    public Welcome(int text, int image) {
        setText(text);
        setImage(image);
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
