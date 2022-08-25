package com.williamnb.readlistenapp.ui.features.bookstore.book_category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.BookStore;
import com.williamnb.readlistenapp.databinding.FragmentBookCategoryBinding;
import com.williamnb.readlistenapp.utilities.Constants;

/**
 * Author: William Giang Nguyen | 15/04/2022
 */
public class BookCategoryFragment extends BaseFragment<
        FragmentBookCategoryBinding,
        BookCategoryViewModel>
        implements BookCategoryAdapter.BookCategoryCallBack {

    private BookCategoryAdapter adapter;

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
        if (getArguments() != null) {
            viewModel.setBookCategory((BookStore.BookCategory) getArguments().getSerializable(Constants.BOOK_STORE_CATEGORY));
        }
        viewBinding.toolbar.setLeftIconResource(R.drawable.ic_back);
        viewBinding.toolbar.setToolbarNameResource(viewModel.getBookCategory().getBookCategoryName());
        viewBinding.toolbar.setRightIconResource(R.drawable.ic_search);
        adapter = new BookCategoryAdapter(viewModel.getBookCategory().getBookList(), this);
    }

    @Override
    public void initializeComponent() {
        viewBinding.rcvBookFollowCategoryList.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        viewBinding.rcvBookFollowCategoryList.setAdapter(adapter);
    }

    @Override
    public void initializeEvents() {
        viewBinding.toolbar.setToolbarLeftCallBack(() -> {
            findNavController().popBackStack();
            Log.d(BookCategoryFragment.class.getName(), "debug: back clicked");
        });
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void openBookDetail(BookStore.Data item) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.BOOK_STORE_DETAIL, item);
        findNavController().navigate(R.id.actionBookCategoryToBookDetail, args);
    }
}