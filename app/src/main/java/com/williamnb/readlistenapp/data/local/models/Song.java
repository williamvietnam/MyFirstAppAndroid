package com.williamnb.readlistenapp.data.local.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public class Song implements Parcelable {
    private long id;
    private String songUrl;
    private String thumbnail;
    private String songName;
    private String displayName;
    private String artist;
    private String songStatus;
    private long duration;

    public Song() {
    }

    public Song(long id, String songUrl, String songName, String displayName, String artist, long duration) {
        this.id = id;
        this.songUrl = songUrl;
        this.songName = songName;
        this.displayName = displayName;
        this.artist = artist;
        this.duration = duration;
    }

    protected Song(Parcel in) {
        id = in.readLong();
        songUrl = in.readString();
        songName = in.readString();
        displayName = in.readString();
        artist = in.readString();
        duration = in.readLong();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", data='" + songUrl + '\'' +
                ", title='" + songName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public String getSongName() {
        return songName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getArtist() {
        return artist;
    }

    public long getDuration() {
        return duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSongStatus() {
        return songStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(songUrl);
        dest.writeString(songName);
        dest.writeString(displayName);
        dest.writeString(artist);
        dest.writeLong(duration);
    }
}