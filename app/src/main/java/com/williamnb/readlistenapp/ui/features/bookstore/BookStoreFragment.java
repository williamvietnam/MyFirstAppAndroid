package com.williamnb.readlistenapp.ui.features.bookstore;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentBookStoreBinding;
import com.williamnb.readlistenapp.ui.features.book.BookMainFragment;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
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
        viewBinding.btnBack.setOnClickListener(v -> {
            findNavController().popBackStack();
            Log.d(BookStoreFragment.class.getName(), "debug: clicked button back");
        });
    }

    @Override
    public void initializeData() {

    }
}