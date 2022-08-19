package com.williamnb.readlistenapp.ui.features.bookstore.book_category;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentBookCategoryBinding;

public class BookCategoryFragment extends BaseFragment<FragmentBookCategoryBinding, BookCategoryViewModel> {

    @Override
    public BookCategoryViewModel createViewModel() {
        return new ViewModelProvider(this).get(BookCategoryViewModel.class);
    }

    @Override
    public FragmentBookCategoryBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBookCategoryBinding.inflate(inflater, container, false);
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
