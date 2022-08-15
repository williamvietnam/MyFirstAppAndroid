package com.williamnb.readlistenapp.data.remote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public class Episode {
    @SerializedName("season")
    @Expose
    private String season;

    @SerializedName("episode")
    @Expose
    private String episode;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("air_date")
    @Expose
    private String airDate;

    public String getSeason() {
        return season;
    }

    public String getEpisode() {
        return episode;
    }

    public String getName() {
        return name;
    }

    public String getAirDate() {
        return airDate;
    }
}
