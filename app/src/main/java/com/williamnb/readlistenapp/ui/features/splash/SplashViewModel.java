package com.williamnb.readlistenapp.ui.features.splash;

import android.app.Application;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.utilities.Constants;

public class SplashViewModel extends BaseViewModel {

    private boolean isLogin = false;

    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    public String decideNextScreen() {
        if (!isLogin) {
            isLogin = true;
            return Constants.LOGIN_SCREEN;
        } else {
            return Constants.MAIN_SCREEN;
        }
    }
}
