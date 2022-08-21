package com.williamnb.readlistenapp.data.local.models;

import androidx.annotation.NonNull;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public class Song {
    private long id;
    private String data;
    private String title;
    private String displayName;
    private String artist;
    private long duration;

    public Song() {
    }

    public Song(long id, String data, String title, String displayName, String artist, long duration) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.displayName = displayName;
        this.artist = artist;
        this.duration = duration;
    }

    @NonNull
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", title='" + title + '\'' +
                ", displayName='" + displayName + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
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
}
