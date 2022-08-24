package com.williamnb.readlistenapp.data.local.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.williamnb.readlistenapp.data.remote.models.TVShow;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
@Entity(tableName = "tvshows")
public class TVShowEntity {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "permalink")
    private String permalink;

    @ColumnInfo(name = "startDate")
    private String startDate;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "network")
    private String network;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "imageThumbnailPath")
    private String imageThumbnailPath;

    public void setDataTVShowEntity(@NonNull TVShow tvShow) {
        setId(tvShow.getId());
        setName(tvShow.getName());
        setPermalink(tvShow.getPermalink());
        setStartDate(tvShow.getStartDate());
        setCountry(tvShow.getCountry());
        setNetwork(tvShow.getNetwork());
        setStatus(tvShow.getStatus());
        setImageThumbnailPath(tvShow.getImageThumbnailPath());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageThumbnailPath() {
        return imageThumbnailPath;
    }

    public void setImageThumbnailPath(String imageThumbnailPath) {
        this.imageThumbnailPath = imageThumbnailPath;
    }
}