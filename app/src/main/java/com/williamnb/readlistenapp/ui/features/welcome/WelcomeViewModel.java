package com.williamnb.readlistenapp.ui.features.welcome;

import android.app.Application;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.Welcome;
import com.williamnb.readlistenapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

public class WelcomeViewModel extends BaseViewModel {

    private final List<Welcome> welcomeList;

    public WelcomeViewModel(@NonNull Application application) {
        super(application);
        this.welcomeList = new ArrayList<>();
    }

    public String handleNextScreen() {
        getPreferenceManager().putBoolean(Constants.KEY_IS_WELCOME_SHOWED, true);
        if (!getPreferenceManager().getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            return Constants.LOGIN_SCREEN;
        } else {
            return Constants.MAIN_SCREEN;
        }
    }

    public List<Welcome> getWelcomeList() {
        this.welcomeList.clear();
        this.welcomeList.add(new Welcome(R.string.welcome_text_1, R.drawable.ic_image_book));
        this.welcomeList.add(new Welcome(R.string.welcome_text_2, R.drawable.ic_image_music));
        this.welcomeList.add(new Welcome(R.string.welcome_text_3, R.drawable.ic_image_news));
        return this.welcomeList;
    }
}