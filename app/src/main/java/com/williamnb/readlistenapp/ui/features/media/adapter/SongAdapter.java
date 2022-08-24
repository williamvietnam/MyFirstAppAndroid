package com.williamnb.readlistenapp.ui.features.media.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.Song;
import com.williamnb.readlistenapp.databinding.ItemSongBinding;
import com.williamnb.readlistenapp.ui.features.media.MediaMainFragment;
import com.williamnb.readlistenapp.utilities.callback.MediaCallBack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SongAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Song> songList;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss", Locale.US);
    private final MediaCallBack callBack;

    public SongAdapter(MediaCallBack callBack, List<Song> songList) {
        this.callBack = callBack;
        this.songList = songList;
    }

    public void submitList(List<Song> data) {
        this.songList.clear();
        this.songList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSongBinding binding = ItemSongBinding.inflate(inflater, parent, false);
        return new SongAdapter.SongHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (songList != null) {
            return songList.size();
        }
        return 0;
    }

    class SongHolder extends BaseViewHolder {
        private final ItemSongBinding binding;

        public SongHolder(@NonNull ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            Song item = songList.get(position);
            String durationText = simpleDateFormat.format(new Date(item.getDuration()));
            binding.txtSongDuration.setText(durationText);
            binding.txtSongName.setText(item.getSongName());
            binding.txtSongArtist.setText(item.getArtist());
            binding.getRoot().setOnClickListener(view -> {
                callBack.onItemClicked(getAdapterPosition());
                Log.d(MediaMainFragment.class.getSimpleName(), "SongId: " + item.getId());
            });
        }

        @Override
        protected void clear() {
            Log.d(MediaMainFragment.class.getSimpleName(), "Cleared");
        }
    }
}