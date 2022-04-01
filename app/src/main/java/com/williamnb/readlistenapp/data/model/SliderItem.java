package com.williamnb.readlistenapp.data.model;

public class SliderItem {

    /**
     * Here you can use String variable to store url
     * If you want to load image from the internet
     * **/
    private int resImage;

    public SliderItem(int resImage) {
        this.resImage = resImage;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }
}
