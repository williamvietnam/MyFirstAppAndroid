package com.williamnb.readlistenapp.ui.features.book.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.book.Book;
import com.williamnb.readlistenapp.databinding.ItemBookSuggestHorizontalBinding;
import com.williamnb.readlistenapp.ui.features.book.BookMainFragment;
import com.williamnb.readlistenapp.utilities.callback.BookCallBack;

import java.util.List;

public class ItemBookSuggestHorizontalAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Book> bookList;
    private final BookCallBack callBack;

    public ItemBookSuggestHorizontalAdapter(List<Book> bookList, BookCallBack callBack) {
        this.bookList = bookList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBookSuggestHorizontalBinding binding = ItemBookSuggestHorizontalBinding.inflate(inflater, parent, false);
        return new ItemBookSuggestHorizontalAdapter.BookSuggestionHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (bookList != null) {
            return bookList.size();
        }
        return 0;
    }

    class BookSuggestionHolder extends BaseViewHolder {
        private final ItemBookSuggestHorizontalBinding binding;

        public BookSuggestionHolder(@NonNull ItemBookSuggestHorizontalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            Book item = bookList.get(position);

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
            Log.d(BookMainFragment.class.getName(), "cleared");
        }
    }
}