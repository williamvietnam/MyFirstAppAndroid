package com.williamnb.readlistenapp.ui.features.account.about_us;

import android.app.Application;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;

public class AboutUsViewModel extends BaseViewModel {
    private final String linkUrl;

    public AboutUsViewModel(@NonNull Application application) {
        super(application);
        this.linkUrl = "https://github.com/williamvietnam";
    }

    public String getLinkUrl() {
        return linkUrl;
    }
}