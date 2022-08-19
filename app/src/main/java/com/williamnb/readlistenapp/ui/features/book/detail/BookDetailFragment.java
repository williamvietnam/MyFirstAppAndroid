package com.williamnb.readlistenapp.ui.features.book.detail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.book.Book;
import com.williamnb.readlistenapp.databinding.FragmentBookDetailBinding;
import com.williamnb.readlistenapp.utilities.Constants;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookDetailFragment extends BaseFragment<FragmentBookDetailBinding, BookDetailViewModel> {

    private BookCommentAdapter commentAdapter;

    @Override
    public BookDetailViewModel createViewModel() {
        return new ViewModelProvider(this).get(BookDetailViewModel.class);
    }

    @Override
    public FragmentBookDetailBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBookDetailBinding.inflate(inflater, container, false);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
        if (getArguments() != null) {
            viewModel.setBookDetail((Book) getArguments().getSerializable(Constants.BOOK_DETAIL));
        }
        Picasso.get()
                .load(viewModel.getBookDetail().getBookFrontCover())
                .placeholder(R.drawable.ic_book_dev_up)
                .error(R.drawable.ic_book_dev_up)
                .into(viewBinding.bookCoverFront);
        Picasso.get()
                .load(viewModel.getBookDetail().getBookBackCover())
                .placeholder(R.drawable.ic_book_dev_up)
                .error(R.drawable.ic_book_dev_up)
                .into(viewBinding.bookCoverBack);
        viewBinding.tvBookName.setText(String.format("Tên sách: %s", viewModel.getBookDetail().getBookName()));
        viewBinding.tvBookPrice.setText(String.format("Giá sách: %s", viewModel.getBookDetail().getBookPrice()));
        viewBinding.tvBookAuthor.setText(String.format("Tác giả: %s", viewModel.getBookDetail().getBookAuthor()));
        viewBinding.tvBookAuthor.setText(String.format("Thể loại sách: %s", viewModel.getBookDetail().getBookCategory()));
        viewBinding.tvBookCategory.setText(viewModel.getBookDetail().getBookSummaryContent());

        this.commentAdapter = new BookCommentAdapter(viewModel.getCommentList());
        viewBinding.rcvCommentList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        viewBinding.rcvCommentList.setAdapter(this.commentAdapter);
    }

    @Override
    public void initializeComponent() {
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnBack.setOnClickListener(v -> {
            findNavController().popBackStack();
            Log.d(BookDetailFragment.class.getName(), "debug: back clicked");
        });

        viewBinding.btnAddComment.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Đang phát triển", Toast.LENGTH_SHORT).show();
            Log.d(BookDetailFragment.class.getName(), "debug: addCommentClicked");
        });
    }

    @Override
    public void initializeData() {
    }
}