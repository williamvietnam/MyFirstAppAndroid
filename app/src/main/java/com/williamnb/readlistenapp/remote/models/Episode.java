package com.williamnb.readlistenapp.remote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
