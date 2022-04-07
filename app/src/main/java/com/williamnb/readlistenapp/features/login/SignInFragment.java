package com.williamnb.readlistenapp.features.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentSignInBinding;

public class SignInFragment extends BaseFragment<FragmentSignInBinding, SignInViewModel> {

    @Override
    public SignInViewModel createViewModel() {
        return new ViewModelProvider(this).get(SignInViewModel.class);
    }

    @Override
    public FragmentSignInBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSignInBinding.inflate(inflater, container, false);
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
        viewBinding.tvCreateNewAccount.setOnClickListener(view -> findNavController().navigate(R.id.actionSignUp));
        viewBinding.btnBack.setOnClickListener(view -> findNavController().popBackStack());
    }

    @Override
    public void initializeData() {

    }
}
