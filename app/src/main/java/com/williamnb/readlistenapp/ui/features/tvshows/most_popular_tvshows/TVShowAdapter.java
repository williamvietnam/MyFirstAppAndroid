package com.williamnb.readlistenapp.ui.features.tvshows.most_popular_tvshows;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.remote.models.TVShow;
import com.williamnb.readlistenapp.databinding.ItemContainerTvShowBinding;
import com.williamnb.readlistenapp.ui.features.tvshows.most_popular_tvshows.MostPopularTVShowsFragment;
import com.williamnb.readlistenapp.utilities.callback.TVShowsCallBack;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class TVShowAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<TVShow> tvShowList;
    private final TVShowsCallBack callBack;

    public TVShowAdapter(List<TVShow> tvShowList, TVShowsCallBack callBack) {
        this.tvShowList = tvShowList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemContainerTvShowBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_container_tv_show, parent, false);
        return new TVShowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (tvShowList != null) {
            return tvShowList.size();
        }
        return 0;
    }

    class TVShowViewHolder extends BaseViewHolder {
        private final ItemContainerTvShowBinding binding;

        public TVShowViewHolder(@NonNull ItemContainerTvShowBinding itemContainerTvShowBinding) {
            super(itemContainerTvShowBinding.getRoot());
            this.binding = itemContainerTvShowBinding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            TVShow item = tvShowList.get(position);
            binding.setTvShow(item);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(view -> {
                callBack.onTVShowClicked(item);
                Log.d(MostPopularTVShowsFragment.class.getSimpleName(), "tvShowsId: " + item.getId());
            });
        }

        @Override
        protected void clear() {
            Log.d(MostPopularTVShowsFragment.class.getSimpleName(), "cleared");
        }
    }
}