package com.williamnb.readlistenapp.ui.features.account;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentAccountBinding;
import com.williamnb.readlistenapp.ui.features.login.SignInActivity;

public class AccountFragment extends BaseFragment<FragmentAccountBinding, AccountViewModel> {
    @Override
    public FragmentAccountBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentAccountBinding.inflate(inflater, container, false);
    }

    @Override
    public AccountViewModel createViewModel() {
        return new ViewModelProvider(this).get(AccountViewModel.class);
    }

    @Override
    public void initializeView() {

    }

    @Override
    public void initializeComponent() {
        final Observer<Boolean> logoutObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@NonNull Boolean isLogout) {
                if (isLogout) {
                    Intent intent = new Intent(requireActivity(), SignInActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                }
            }
        };
        viewModel.getLogoutLiveData().observe(this, logoutObserver);
    }

    @Override
    public void initializeEvents() {
//        viewBinding.btnLogout.setOnClickListener(view -> {
//            Log.d(AccountFragment.class.getName(), "debug: in progress logout()...");
//            viewModel.logout();
//        });
    }

    @Override
    public void initializeData() {
    }
}