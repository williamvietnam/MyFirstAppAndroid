package com.williamnb.readlistenapp.ui.features.account.about_us;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentAboutUsBinding;

public class AboutUsFragment extends BaseFragment<FragmentAboutUsBinding, AboutUsViewModel> {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = () -> {
        Log.d(AboutUsFragment.class.getSimpleName(), "Runnable");
        viewBinding.progressBar.setVisibility(View.GONE);
    };

    @Override
    public FragmentAboutUsBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentAboutUsBinding.inflate(inflater, container, false);
    }

    @Override
    public AboutUsViewModel createViewModel() {
        return new ViewModelProvider(this).get(AboutUsViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
        viewBinding.progressBar.setVisibility(View.VISIBLE);
        viewBinding.webView.getSettings().setJavaScriptEnabled(false);
        viewBinding.webView.loadUrl(viewModel.getLinkUrl());
    }

    @Override
    public void initializeComponent() {
        this.handler.postDelayed(runnable, 2000);
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnBack.setOnClickListener(v -> {
            Log.d(AboutUsFragment.class.getSimpleName(), "back clicked");
            findNavController().popBackStack();
        });
    }

    @Override
    public void initializeData() {
    }
}