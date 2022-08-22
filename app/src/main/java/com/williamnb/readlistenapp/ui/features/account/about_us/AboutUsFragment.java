package com.williamnb.readlistenapp.ui.features.account.about_us;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentAboutUsBinding;

public class AboutUsFragment extends BaseFragment<FragmentAboutUsBinding, AboutUsViewModel> {
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
