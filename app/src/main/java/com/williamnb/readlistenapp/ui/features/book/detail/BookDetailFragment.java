package com.williamnb.readlistenapp.ui.features.book.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.book.Book;
import com.williamnb.readlistenapp.databinding.FragmentBookDetailBinding;
import com.williamnb.readlistenapp.utilities.Constants;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookDetailFragment extends BaseFragment<FragmentBookDetailBinding, BookDetailViewModel> {

    @Override
    public FragmentBookDetailBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBookDetailBinding.inflate(inflater, container, false);
    }

    @Override
    public BookDetailViewModel createViewModel() {
        return new ViewModelProvider(this).get(BookDetailViewModel.class);
    }

    @Override
    public void initializeView() {
        if (getArguments() != null) {
            viewModel.setBookDetail((Book) getArguments().getSerializable(Constants.BOOK_DETAIL));
        }
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