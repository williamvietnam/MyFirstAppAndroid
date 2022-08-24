package com.williamnb.readlistenapp.data.local.models;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class AccountItem {
    private final String itemId;
    private final String textHeadline;
    private final String textDescription;
    private final int icon;

    public AccountItem(String itemId, String textHeadline, String textDescription, int icon) {
        this.itemId = itemId;
        this.textHeadline = textHeadline;
        this.textDescription = textDescription;
        this.icon = icon;
    }

    public String getItemId() {
        return itemId;
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