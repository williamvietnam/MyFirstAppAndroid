package com.williamnb.readlistenapp.ui.features.home.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.Game;
import com.williamnb.readlistenapp.databinding.ItemFeaturedGamesBinding;
import com.williamnb.readlistenapp.ui.features.home.HomeFragment;
import com.williamnb.readlistenapp.utilities.callback.HomeCallBack;

import java.util.List;

public class ItemFeaturedGamesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Game> gameList;
    private final HomeCallBack callBack;

    public ItemFeaturedGamesAdapter(List<Game> gameList, HomeCallBack callBack) {
        this.gameList = gameList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFeaturedGamesBinding binding = ItemFeaturedGamesBinding.inflate(inflater, parent, false);
        return new ItemFeaturedGamesAdapter.FeaturedGamesHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (gameList != null) {
            return gameList.size();
        }
        return 0;
    }

    class FeaturedGamesHolder extends BaseViewHolder {
        private final ItemFeaturedGamesBinding binding;

        public FeaturedGamesHolder(@NonNull ItemFeaturedGamesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            Game item = gameList.get(position);
            this.binding.logoGame.setImageResource(item.getLogo());
            this.binding.titleGame.setText(item.getTitle());
            this.binding.getRoot().setOnClickListener(v -> {
                callBack.onFeaturedGamesClicked(item);
                Log.d(HomeFragment.class.getSimpleName(), "FeaturedGamesId: " + item.getId());
            });
        }

        @Override
        protected void clear() {
            Log.d(HomeFragment.class.getSimpleName(), "Cleared");
        }
    }
}