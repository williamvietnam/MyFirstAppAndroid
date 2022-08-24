package com.williamnb.readlistenapp.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public abstract class BaseActivity<VB extends ViewBinding, VM extends BaseViewModel>
        extends AppCompatActivity implements BaseActivityView {

    public VB viewBinding;
    public VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getName(), "onCreate()...");

        onPostOnCreate();

        viewBinding = getActivityBinding();
        setContentView(viewBinding.getRoot());

        initializeView();
        initializeComponent();
        initializeEvents();
        initializeData();
    }

    @Override
    protected void onDestroy() {
        viewBinding = null;
        viewModel.getCompositeDisposable().dispose();
        Log.d(getClass().getName(), "onDestroy()...");
        super.onDestroy();
    }

    public abstract VB getActivityBinding();

    protected void onPostOnCreate() {
        viewModel = createViewModel();
    }

    public abstract VM createViewModel();
}