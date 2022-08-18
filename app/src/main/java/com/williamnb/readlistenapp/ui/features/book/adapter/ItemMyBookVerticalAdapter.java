package com.williamnb.readlistenapp.ui.features.book.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.book.Book;
import com.williamnb.readlistenapp.databinding.ItemMyBookVerticalBinding;
import com.williamnb.readlistenapp.ui.features.book.BookMainFragment;
import com.williamnb.readlistenapp.utilities.callback.BookCallBack;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class ItemMyBookVerticalAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Book> books;
    private final BookCallBack callBack;

    public ItemMyBookVerticalAdapter(List<Book> books, BookCallBack callBack) {
        this.books = books;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMyBookVerticalBinding binding = ItemMyBookVerticalBinding.inflate(inflater, parent, false);
        return new ItemMyBookVerticalAdapter.MyBookHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (this.books != null) {
            return this.books.size();
        }
        return 0;
    }

    class MyBookHolder extends BaseViewHolder {
        private final ItemMyBookVerticalBinding binding;

        public MyBookHolder(@NonNull ItemMyBookVerticalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBind(int position) {
            super.onBind(position);
            Book item = books.get(position);

            Picasso.get()
                    .load(item.getBookThumbnail())
                    .placeholder(R.drawable.img_book_muc_tieu)
                    .error(R.drawable.img_book_muc_tieu)
                    .into(this.binding.imvBook);

            this.binding.bookName.setText(item.getBookName());
            this.binding.optionRead.setText(item.getOptionRead());
            this.binding.progressBar.setProgress(item.getProgress());
            this.binding.progressValue.setText(item.getProgress() + "%");
            this.binding.btnRemoveBook.setOnClickListener(v -> {
                callBack.removeBook(getAdapterPosition());
                Log.d(BookMainFragment.class.getName(), "Delete book: " + item.getBookName());
            });
            this.binding.btnClickDetail.setOnClickListener(v -> {
                callBack.openBookDetail(item);
                Log.d(BookMainFragment.class.getName(), "Click book: " + item.getBookName());
            });
        }

        @Override
        protected void clear() {
            Log.d(BookMainFragment.class.getName(), "cleared");
        }
    }
}