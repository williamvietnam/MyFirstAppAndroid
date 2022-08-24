package com.williamnb.readlistenapp.ui.features.bookstore;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.BookStore;
import com.williamnb.readlistenapp.databinding.ItemBookSuggestHorizontalBinding;
import com.williamnb.readlistenapp.ui.features.book.BookMainFragment;
import com.williamnb.readlistenapp.utilities.callback.BookStoreCallBack;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class BookStoreAdapterChild extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<BookStore.Data> dataList;
    private final BookStoreCallBack callBack;

    public BookStoreAdapterChild(List<BookStore.Data> dataList, BookStoreCallBack callBack) {
        this.dataList = dataList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBookSuggestHorizontalBinding binding = ItemBookSuggestHorizontalBinding.inflate(inflater, parent, false);
        return new BookStoreAdapterChild.BookStoreChildHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (dataList != null) {
            return dataList.size();
        }
        return 0;
    }

    class BookStoreChildHolder extends BaseViewHolder {
        private final ItemBookSuggestHorizontalBinding binding;

        public BookStoreChildHolder(@NonNull ItemBookSuggestHorizontalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            BookStore.Data item = dataList.get(position);

            Picasso.get()
                    .load(item.getBookThumbnail())
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
                    .into(this.binding.imvBook);
            this.binding.tvBookName.setText(item.getBookName());
            this.binding.ratingBar.setNumStars(5);
            this.binding.ratingBar.setRating(item.getRating());
            this.binding.tvBookPrice.setText(String.format("Giá: %s VND", item.getBookPrice()));
            binding.getRoot().setOnClickListener(v -> {
                callBack.openBookDetail(item);
                Log.d(BookMainFragment.class.getName(), "Click book suggestion: " + item.getBookName());
            });
        }

        @Override
        protected void clear() {
            this.binding.tvBookName.setText("");
            this.binding.ratingBar.setRating(0);
            this.binding.tvBookName.setText("");
            this.binding.tvBookPrice.setText("");
            Log.d(BookMainFragment.class.getName(), "cleared");
        }
    }
}