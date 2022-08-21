package com.williamnb.readlistenapp.data.remote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class TVShow implements Serializable {

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