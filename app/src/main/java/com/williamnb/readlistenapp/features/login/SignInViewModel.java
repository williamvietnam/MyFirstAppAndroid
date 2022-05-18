package com.williamnb.readlistenapp.features.login;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;

public class SignInViewModel extends BaseViewModel {
    public SignInViewModel(@NonNull Application application) {
        super(application);
    }

    public void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
