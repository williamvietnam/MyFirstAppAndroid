package com.williamnb.readlistenapp.ui.features.account;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentAccountBinding;

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

    }

    @Override
    public void initializeEvents() {

    }

    @Override
    public void initializeData() {

    }
}
