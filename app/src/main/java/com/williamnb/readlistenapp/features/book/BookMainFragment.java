package com.williamnb.readlistenapp.features.book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
