package com.williamnb.readlistenapp.features.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.data.model.News;

import java.util.List;

public class ItemFeaturedNewsAdapter extends RecyclerView.Adapter<ItemFeaturedNewsAdapter.ViewHolder> {
    private List<News> newsList;

    public ItemFeaturedNewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_featured_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.imvThumbnail.setImageResource(news.getImvThumbnail());
        holder.tvContent.setText(news.getContent());
        holder.tvDate.setText(news.getDate());
        holder.tvNumberView.setText(news.getNumberView());
    }

    @Override
    public int getItemCount() {
        if (newsList != null) {
            return newsList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imvThumbnail;
        private final TextView tvContent;
        private final TextView tvDate;
        private final TextView tvNumberView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumbnail = itemView.findViewById(R.id.imvThumbnail);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvNumberView = itemView.findViewById(R.id.tvNumberViews);
        }
    }
}
