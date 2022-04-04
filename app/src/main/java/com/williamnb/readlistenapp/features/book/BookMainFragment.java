package com.williamnb.readlistenapp.features.book;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentBookMainBinding;

public class BookMainFragment extends BaseFragment<FragmentBookMainBinding, BookMainViewModel> {

    @Override
    public FragmentBookMainBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBookMainBinding.inflate(inflater, container, false);
    }

    @Override
    public BookMainViewModel createViewModel() {
        return new ViewModelProvider(this).get(BookMainViewModel.class);
    }

    @Override
    public void initializeView() {
        viewBinding.btnMenuSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController().navigate(R.id.actionSignIn);
            }
        });
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
