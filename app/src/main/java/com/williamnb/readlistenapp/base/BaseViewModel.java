package com.williamnb.readlistenapp.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.williamnb.readlistenapp.data.local.preferences.PreferenceManager;
import com.williamnb.readlistenapp.data.remote.ApiClient;
import com.williamnb.readlistenapp.data.remote.ApiServices;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public abstract class BaseViewModel extends AndroidViewModel {

    private final Context context;
    private final PreferenceManager preferenceManager;
    private final CompositeDisposable compositeDisposable;
    private final ApiServices apiService;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        this.preferenceManager = new PreferenceManager(application);
        this.compositeDisposable = new CompositeDisposable();
        this.apiService = ApiClient.getInstance().getApiService();
    }

    public Context getViewModelContext() {
        return this.context;
    }

    public PreferenceManager getPreferenceManager() {
        return this.preferenceManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public ApiServices getApiService() {
        return apiService;
    }
}