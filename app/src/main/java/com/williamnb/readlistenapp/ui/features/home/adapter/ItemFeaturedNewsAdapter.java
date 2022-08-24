package com.williamnb.readlistenapp.ui.features.home.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.News;
import com.williamnb.readlistenapp.databinding.ItemFeaturedNewsBinding;
import com.williamnb.readlistenapp.ui.features.home.HomeFragment;
import com.williamnb.readlistenapp.utilities.callback.HomeCallBack;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class ItemFeaturedNewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<News> newsList;
    private final HomeCallBack callBack;

    public ItemFeaturedNewsAdapter(List<News> newsList, HomeCallBack callBack) {
        this.newsList = newsList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFeaturedNewsBinding binding = ItemFeaturedNewsBinding.inflate(inflater, parent, false);
        return new ItemFeaturedNewsAdapter.FeaturedNewsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (newsList != null) {
            return newsList.size();
        }
        return 0;
    }

    class FeaturedNewsHolder extends BaseViewHolder {
        private final ItemFeaturedNewsBinding binding;

        public FeaturedNewsHolder(@NonNull ItemFeaturedNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            News item = newsList.get(position);
            this.binding.imvThumbnail.setImageResource(item.getImvThumbnail());
            this.binding.tvContent.setText(item.getContent());
            this.binding.tvDate.setText(item.getDate());
            this.binding.tvNumberViews.setText(item.getNumberView());
            this.binding.getRoot().setOnClickListener(v -> {
                callBack.onFeaturedNewsClicked(item);
                Log.d(HomeFragment.class.getSimpleName(), "FeaturedNewsId: " + item.getId());
            });
        }

        @Override
        protected void clear() {
            Log.d(HomeFragment.class.getSimpleName(), "Cleared");
        }
    }
}