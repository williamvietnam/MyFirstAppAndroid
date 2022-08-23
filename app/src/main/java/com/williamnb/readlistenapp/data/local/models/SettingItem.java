package com.williamnb.readlistenapp.data.local.models;

public class SettingItem {

    private String text;
    private boolean isSwitch = false;

    public SettingItem(String text) {
        this.text = text;
    }

    public SettingItem(String text, boolean isSwitch) {
        this.text = text;
        this.isSwitch = isSwitch;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSwitch() {
        return isSwitch;
    }

    public void setSwitch(boolean aSwitch) {
        isSwitch = aSwitch;
    }

}