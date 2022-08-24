package com.williamnb.readlistenapp.data.remote.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.williamnb.readlistenapp.data.remote.models.TVShowDetails;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public class TVShowDetailsResponse {

    @SerializedName("tvShow")
    @Expose
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShowDetails() {
        return this.tvShowDetails;
    }

    public void setTvShowDetails(TVShowDetails tvShowDetails) {
        this.tvShowDetails = tvShowDetails;
    }
}