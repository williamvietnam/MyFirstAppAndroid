package com.williamnb.readlistenapp.features;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

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
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(viewBinding.bottomNav, navController);
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

/** - BottomNavigationView + NavigationCompose: --> src:
** NavHostFragment navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
** NavController navController = navHostFragment.getNavController();
** BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
** NavigationUI.setupWithNavController(bottomNav, navController);
**/
