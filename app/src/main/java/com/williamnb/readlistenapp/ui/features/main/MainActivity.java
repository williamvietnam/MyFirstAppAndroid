package com.williamnb.readlistenapp.ui.features.main;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseActivity;
import com.williamnb.readlistenapp.databinding.ActivityMainBinding;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public ActivityMainBinding getActivityBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public MainViewModel createViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public void initializeView() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(viewBinding.bottomNav, navController);
    }

    @Override
    public void initializeComponent() {
        hideBottomNavigationView(false);
    }

    @Override
    public void initializeEvents() {
        //TODO
    }

    @Override
    public void initializeData() {
        //TODO
    }

    public void hideBottomNavigationView(boolean isHidden) {
        viewBinding.bottomNav.setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }

    public void getIntentActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
        finish();
    }
}

/*
  - BottomNavigationView + NavigationCompose: --> src:
  * NavHostFragment navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
  * NavController navController = navHostFragment.getNavController();
  * BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
  * NavigationUI.setupWithNavController(bottomNav, navController);
 */