package com.williamnb.readlistenapp.ui.features.bookstore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.BookStore;
import com.williamnb.readlistenapp.databinding.FragmentBookStoreBinding;
import com.williamnb.readlistenapp.utilities.Constants;
import com.williamnb.readlistenapp.utilities.callback.BookStoreCallBack;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookStoreFragment extends BaseFragment<FragmentBookStoreBinding, BookStoreViewModel> implements BookStoreCallBack {

    private BookStoreAdapter adapter;

    @Override
    public BookStoreViewModel createViewModel() {
        return new ViewModelProvider(this).get(BookStoreViewModel.class);
    }

    @Override
    public FragmentBookStoreBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBookStoreBinding.inflate(inflater, container, false);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
        adapter = new BookStoreAdapter(
                viewModel.getDataFromAssetsBookStore(),
                viewModel.getAdapterChildList(this),
                this
        );
    }

    @Override
    public void initializeComponent() {
        viewBinding.rcv.setAdapter(adapter);
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

    @Override
    public void onSeeMoreClicked(BookStore.BookCategory item) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.BOOK_STORE_CATEGORY, item);
        findNavController().navigate(R.id.actionBookStoreToBookCategory, args);
    }

    @Override
    public void openBookDetail(@NonNull BookStore.Data item) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.BOOK_STORE_DETAIL, item);
        findNavController().navigate(R.id.actionBookStoreToBookDetail, args);
    }
}