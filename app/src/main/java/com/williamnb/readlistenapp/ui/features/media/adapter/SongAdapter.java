package com.williamnb.readlistenapp.ui.features.media.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.utilities.listeners.OnItemClickListener;
import com.williamnb.readlistenapp.databinding.ItemSongBinding;
import com.williamnb.readlistenapp.data.local.models.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private final ArrayList<Song> data = new ArrayList<>();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss", Locale.US);
    private OnItemClickListener onItemClickListener;

    public SongAdapter() {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(List<Song> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSongBinding binding = ItemSongBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindViews(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // ---------------------------------------------------------------------------------------------
    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSongBinding binding;

        public ViewHolder(ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClicked(getAdapterPosition());
                }
            });
        }

        public void bindViews(int position) {
            Song song = data.get(position);

            binding.txtSongName.setText(song.getTitle());
            binding.txtSongArtist.setText(song.getArtist());

            long duration = song.getDuration();
            Date date = new Date(duration);
            String durationText = simpleDateFormat.format(date);
            binding.txtSongDuration.setText(durationText);
        }
    }

}