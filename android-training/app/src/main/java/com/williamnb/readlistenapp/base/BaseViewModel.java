package com.williamnb.readlistenapp.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.williamnb.readlistenapp.data.local.preferences.PreferenceManager;

public abstract class BaseViewModel extends AndroidViewModel {

    private final Context context;
    private PreferenceManager preferenceManager;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        this.preferenceManager = new PreferenceManager(application);
    }

    public Context getViewModelContext() {
        return this.context;
    }

    public void setPreferenceManager(PreferenceManager preferenceManager) {
        this.preferenceManager = preferenceManager;
    }

    public PreferenceManager getPreferenceManager(){
        return this.preferenceManager;
    }
}
