package com.williamnb.readlistenapp.domain.models;

public class SliderItem {

    private String id;

    /**
     * Here you can use String variable to store url
     * If you want to load image from the internet
     * **/
    private int resImage;

    public SliderItem(String id, int resImage) {
        this.id = id;
        this.resImage = resImage;
    }

    public SliderItem(int resImage) {
        this.resImage = resImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }
}
