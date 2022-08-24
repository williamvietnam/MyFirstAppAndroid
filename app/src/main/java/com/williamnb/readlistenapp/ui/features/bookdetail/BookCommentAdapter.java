package com.williamnb.readlistenapp.ui.features.bookdetail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.Comment;
import com.williamnb.readlistenapp.databinding.ItemBookCommentBinding;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class BookCommentAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Comment.Data> dataList;

    public BookCommentAdapter(List<Comment.Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBookCommentBinding binding = ItemBookCommentBinding.inflate(inflater, parent, false);
        return new BookCommentAdapter.BookCommentHolder(binding);
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

    class BookCommentHolder extends BaseViewHolder {
        private final ItemBookCommentBinding binding;

        public BookCommentHolder(@NonNull ItemBookCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            Comment.Data item = dataList.get(position);
            Picasso.get()
                    .load(item.getImageAvatar())
                    .placeholder(R.drawable.ava_none)
                    .error(R.drawable.ava_none)
                    .into(this.binding.imvAvatar);
            this.binding.tvUserName.setText(item.getUserName());
            this.binding.tvDate.setText(item.getDate());
            this.binding.tvCommentContent.setText(item.getCommentContent());
        }

        @Override
        protected void clear() {
            this.binding.imvAvatar.setImageDrawable(null);
            this.binding.tvUserName.setText("");
            this.binding.tvDate.setText("");
            this.binding.tvCommentContent.setText("");
            Log.d(BookDetailFragment.class.getName(), "debug: cleared");
        }
    }
}