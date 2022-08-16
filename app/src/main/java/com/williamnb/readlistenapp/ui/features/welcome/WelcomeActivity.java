package com.williamnb.readlistenapp.ui.features.welcome;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseActivity;
import com.williamnb.readlistenapp.databinding.ActivityWelcomeBinding;


public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding, WelcomeViewModel> {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    protected ActivityWelcomeBinding getActivityBinding() {
        return ActivityWelcomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public WelcomeViewModel createViewModel() {
        return new ViewModelProvider(this).get(WelcomeViewModel.class);
    }

    @Override
    public void initializeView() {
        handler.postDelayed(runnable, 1500);
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
