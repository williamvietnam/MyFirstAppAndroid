package com.williamnb.readlistenapp.ui.features.media.songlist;

import android.app.Application;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.Song;

import java.util.ArrayList;
import java.util.List;

public class SongListViewModel extends BaseViewModel {
    private ArrayList<Song> songList;

    public SongListViewModel(@NonNull Application application) {
        super(application);
        this.songList = new ArrayList<>();
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }
}
