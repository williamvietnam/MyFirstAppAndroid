package com.williamnb.readlistenapp.features;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseActivity;
import com.williamnb.readlistenapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected ActivityMainBinding getActivityBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public MainViewModel createViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public void initializeView() {

    }

    @Override
    public void initializeComponent() {

    }

    @Override
    public void initializeEvents() {

    }

    @Override
    public void initializeData() {

    }
}
