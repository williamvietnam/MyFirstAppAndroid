package com.williamnb.readlistenapp.features;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.williamnb.readlistenapp.R;
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


/*
 **  BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
 **  NavController navController = Navigation.findNavController(this,  R.id.nav_host_fragment);
 **  NavigationUI.setupWithNavController(bottomNavigationView, navController);
 * */

    /*
    * Hi I know this is a 4 month old comment at the time but I also had this issue (can't click the other icons with this code) and managed to fix it. I thought if anyone else has a problem I will clarify how I fixed it for myself:
You must name your menu items with the EXACT same id as your fragment id. I don't think it's mentioned in the video, but whatever the ids are on your fragments, they must match on the menu items as well or it won't navigate.
Cheers and I hope you found your solution!
*
* https://www.youtube.com/watch?v=Chso6xrJ6aU&ab_channel=Stevdza-San
    * */