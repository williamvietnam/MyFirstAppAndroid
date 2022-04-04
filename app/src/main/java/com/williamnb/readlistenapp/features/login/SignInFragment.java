package com.williamnb.readlistenapp.features.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentSignInBinding;
import com.williamnb.readlistenapp.features.MainActivity;

public class SignInFragment extends BaseFragment<FragmentSignInBinding, SignInViewModel> {

    @Override
    public FragmentSignInBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSignInBinding.inflate(inflater, container, false);
    }

    @Override
    public SignInViewModel createViewModel() {
        return new ViewModelProvider(this).get(SignInViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
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
