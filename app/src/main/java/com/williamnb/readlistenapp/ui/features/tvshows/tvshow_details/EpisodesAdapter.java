package com.williamnb.readlistenapp.ui.features.tvshows.tvshow_details;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.remote.models.Episode;
import com.williamnb.readlistenapp.databinding.ItemContainerEpisodeBinding;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class EpisodesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Episode> episodes;

    public EpisodesAdapter(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemContainerEpisodeBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_container_episode, parent, false);
        return new EpisodesAdapter.EpisodesHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (episodes != null) {
            return episodes.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class EpisodesHolder extends BaseViewHolder {
        private final ItemContainerEpisodeBinding binding;

        public EpisodesHolder(@NonNull ItemContainerEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            Episode item = episodes.get(position);

            String season = item.getSeason();
            if (season.length() == 1) {
                season = "0".concat(season);
            }

            String episodeNumber = item.getEpisode();
            if (episodeNumber.length() == 1) {
                episodeNumber = "0".concat(episodeNumber);
            }
            episodeNumber = "E".concat(episodeNumber);

            String title = "S";
            title = title.concat(season).concat(episodeNumber);

            this.binding.setTitle(title);
            this.binding.setName(item.getName());
            this.binding.setAirDate(item.getAirDate());
        }

        @Override
        protected void clear() {
            this.binding.setTitle("");
            this.binding.setName("");
            this.binding.setAirDate("");
        }
    }
}