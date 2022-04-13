package com.williamnb.readlistenapp.remote.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "tvShows")
public class TVShow implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("permalink")
    @Expose
    private String permalink;

    @SerializedName("start_date")
    @Expose
    private String startDate;

//    @SerializedName("end_date")
//    @Expose
//    private Object endDate;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("network")
    @Expose
    private String network;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("image_thumbnail_path")
    @Expose
    private String imageThumbnailPath;

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

//    public Object getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Object endDate) {
//        this.endDate = endDate;
//    }

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