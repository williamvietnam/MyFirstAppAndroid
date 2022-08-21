package com.williamnb.readlistenapp.data.local.models;

public class AccountItem {
    private final String textHeadline;
    private final String textDescription;
    private final int icon;

    public AccountItem(String textHeadline, String textDescription, int icon) {
        this.textHeadline = textHeadline;
        this.textDescription = textDescription;
        this.icon = icon;
    }

    public String getTextHeadline() {
        return textHeadline;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public int getIcon() {
        return icon;
    }
}