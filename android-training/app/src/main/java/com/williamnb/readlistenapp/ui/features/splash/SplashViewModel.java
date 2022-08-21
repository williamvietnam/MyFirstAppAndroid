package com.williamnb.readlistenapp.ui.features.splash;

import android.app.Application;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.utilities.Constants;

public class SplashViewModel extends BaseViewModel {

    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    public String handleNextScreen() {
        if (!getPreferenceManager().getBoolean(Constants.KEY_IS_WELCOME_SHOWED)) {
            return Constants.WELCOME_SCREEN;
        } else if (!getPreferenceManager().getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            return Constants.LOGIN_SCREEN;
        } else {
            return Constants.MAIN_SCREEN;
        }
    }
}