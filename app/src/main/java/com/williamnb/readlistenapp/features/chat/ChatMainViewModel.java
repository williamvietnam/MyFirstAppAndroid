package com.williamnb.readlistenapp.features.chat;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;

public class ChatMainViewModel extends BaseViewModel {

    public ChatMainViewModel(@NonNull Application application) {
        super(application);
    }

    public void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
