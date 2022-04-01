package com.williamnb.readlistenapp.features.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.data.model.Game;

import java.util.List;

public class ItemFeaturedGamesAdapter extends RecyclerView.Adapter<ItemFeaturedGamesAdapter.ViewHolder> {
    private List<Game> gameList;

    public ItemFeaturedGamesAdapter(List<Game> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_featured_games, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.logoGame.setImageResource(game.getLogo());
        holder.titleGame.setText(game.getTitle());
    }

    @Override
    public int getItemCount() {
        if (gameList != null) {
            return gameList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView logoGame;
        private final TextView titleGame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            logoGame = itemView.findViewById(R.id.logoGame);
            titleGame = itemView.findViewById(R.id.titleGame);
        }
    }
}
