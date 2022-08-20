package com.williamnb.readlistenapp.ui.features.bookstore;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.BookStore;
import com.williamnb.readlistenapp.databinding.LayoutBookStoreBinding;
import com.williamnb.readlistenapp.utilities.callback.BookStoreCallBack;

import java.util.List;

public class BookStoreAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<BookStore.BookCategory> bookCategoryList;
    private final List<BookStoreAdapterChild> adapterList;
    private final BookStoreCallBack callBack;

    public BookStoreAdapter(List<BookStore.BookCategory> bookCategoryList,
                            List<BookStoreAdapterChild> adapterList,
                            BookStoreCallBack callBack) {
        this.bookCategoryList = bookCategoryList;
        this.adapterList = adapterList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutBookStoreBinding binding = LayoutBookStoreBinding.inflate(inflater, parent, false);
        return new BookStoreAdapter.BookStoreHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (bookCategoryList != null) {
            return bookCategoryList.size();
        }
        return 0;
    }

    class BookStoreHolder extends BaseViewHolder {
        private final LayoutBookStoreBinding binding;

        public BookStoreHolder(@NonNull LayoutBookStoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            BookStore.BookCategory item = bookCategoryList.get(position);
            this.binding.tvBookCategory.setText(item.getBookCategoryName());
            BookStoreAdapterChild bookAdapter = adapterList.get(position);
            this.binding.rcvBookList.setAdapter(bookAdapter);
            this.binding.tvSeeMore.setOnClickListener(view -> {
                callBack.onSeeMoreClicked(item.getBookCategoryId());
                Log.d(BookStoreFragment.class.getName(), "debug: click see more " + item.getBookCategoryId());
            });
        }

        @Override
        protected void clear() {
            this.binding.tvBookCategory.setText("");
            this.binding.rcvBookList.setAdapter(null);
            Log.d(BookStoreFragment.class.getName(), "debug: cleared");
        }
    }
}