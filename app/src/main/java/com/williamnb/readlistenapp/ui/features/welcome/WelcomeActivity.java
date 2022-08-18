package com.williamnb.readlistenapp.ui.features.welcome;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.williamnb.readlistenapp.base.BaseActivity;
import com.williamnb.readlistenapp.databinding.ActivityWelcomeBinding;
import com.williamnb.readlistenapp.ui.features.login.SignInActivity;
import com.williamnb.readlistenapp.ui.features.main.MainActivity;
import com.williamnb.readlistenapp.utilities.Constants;


public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding, WelcomeViewModel> {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = () -> {
        if (viewBinding.viewPager2.getCurrentItem() == (viewModel.getWelcomeList().size() - 1)) {
            viewBinding.viewPager2.setCurrentItem(0);
        } else {
            viewBinding.viewPager2.setCurrentItem(viewBinding.viewPager2.getCurrentItem() + 1);
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
        WelcomeAdapter adapter = new WelcomeAdapter(viewModel.getWelcomeList());
        viewBinding.viewPager2.setAdapter(adapter);
        viewBinding.indicator3.setViewPager(viewBinding.viewPager2);
    }

    @Override
    public void initializeComponent() {
        setupViewpager2();
    }

    @Override
    public void initializeEvents() {
        viewBinding.tvSkip.setOnClickListener(v -> {
            openNextScreen(viewModel.handleNextScreen());
            Log.d(WelcomeActivity.class.getSimpleName(), "Skip welcome screen to next screen");
        });

        viewBinding.btnStart.setOnClickListener(v -> {
            openNextScreen(viewModel.handleNextScreen());
            Log.d(WelcomeActivity.class.getSimpleName(), "Start next screen");
        });
    }

    @Override
    public void initializeData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 1500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private void setupViewpager2() {
        viewBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 1500);
                if (position == viewModel.getWelcomeList().size() - 1) {
                    viewBinding.btnStart.setVisibility(View.VISIBLE);
                    viewBinding.tvSkip.setVisibility(View.INVISIBLE);
                } else {
                    viewBinding.btnStart.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void openNextScreen(@NonNull String screen) {
        if (screen.equals(Constants.MAIN_SCREEN)) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (screen.equals(Constants.LOGIN_SCREEN)) {
            startActivity(new Intent(this, SignInActivity.class));
        }
        finish();
    }
}