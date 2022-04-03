package com.williamnb.readlistenapp.features.bookstore;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentBookStoreBinding;

public class BookStoreFragment extends BaseFragment<FragmentBookStoreBinding, BookStoreViewModel> {
    @Override
    public FragmentBookStoreBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBookStoreBinding.inflate(inflater, container, false);
    }

    @Override
    public BookStoreViewModel createViewModel() {
        return new ViewModelProvider(this).get(BookStoreViewModel.class);
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
