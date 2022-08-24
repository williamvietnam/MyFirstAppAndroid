package com.williamnb.readlistenapp.ui.features.media;

import android.app.Application;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaMainViewModel extends BaseViewModel {

    private final MediaPlayer mediaPlayer;
    private int songPosition;
    private List<Song> songList;

    public MediaMainViewModel(@NonNull Application application) {
        super(application);
        // initializing media player
        this.mediaPlayer = new MediaPlayer();
        this.songList = new ArrayList<>();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }

    public int getSongPosition() {
        return songPosition;
    }

    public void setSongPosition(int songPosition) {
        this.songPosition = songPosition;
    }

    public void playAudio(int songPosition) {
        String songUrl = songList.get(songPosition).getSongUrl();
        String audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
        // below line is use to set the audio
        // stream type for our media player.
        this.mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(audioUrl);
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // below line is use to display a toast message.
        Toast.makeText(getViewModelContext(), "Audio started playing..", Toast.LENGTH_SHORT).show();
    }
}