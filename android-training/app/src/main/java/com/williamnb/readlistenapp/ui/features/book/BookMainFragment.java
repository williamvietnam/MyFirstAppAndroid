package com.williamnb.readlistenapp.ui.features.book;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.book.Book;
import com.williamnb.readlistenapp.databinding.FragmentBookMainBinding;
import com.williamnb.readlistenapp.ui.features.book.adapter.ItemBookSuggestHorizontalAdapter;
import com.williamnb.readlistenapp.ui.features.book.adapter.ItemMyBookVerticalAdapter;
import com.williamnb.readlistenapp.utilities.Constants;
import com.williamnb.readlistenapp.utilities.callback.BookCallBack;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookMainFragment extends BaseFragment<FragmentBookMainBinding, BookMainViewModel> implements BookCallBack {

    private ItemBookSuggestHorizontalAdapter bookSuggestAdapter;
    private ItemMyBookVerticalAdapter myBookAdapter;

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
        hideBottomNavigationView(false);

        bookSuggestAdapter = new ItemBookSuggestHorizontalAdapter(viewModel.getDataFromAssetsBookSuggestion(), this);
        viewBinding.rcvBookSuggestion.setAdapter(bookSuggestAdapter);

        myBookAdapter = new ItemMyBookVerticalAdapter(viewModel.getDataFromAssetsMyBook(), this);
        viewBinding.rcvMyBook.setAdapter(myBookAdapter);
    }

    @Override
    public void initializeComponent() {
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnBookStore.setOnClickListener(v -> {
            findNavController().navigate(R.id.actionBookToBookStore);
            Log.d(BookMainFragment.class.getName(), "debug: clicked next book store screen");
        });

        viewBinding.btnSeeMore.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Đang phát triển", Toast.LENGTH_SHORT).show();
            Log.d(BookMainFragment.class.getName(), "debug: clicked button see more");
        });
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void removeBook(int position) {
        viewModel.getMyBookList().remove(position);
        myBookAdapter.notifyItemRemoved(position);
    }

    @Override
    public void openBookDetail(Book item) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.BOOK_DETAIL, item);
        findNavController().navigate(R.id.actionBookToBookDetail, args);
    }
}