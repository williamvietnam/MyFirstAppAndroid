package com.williamnb.readlistenapp.remote.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.williamnb.readlistenapp.remote.models.TVShowDetails;

public class TVShowDetailsResponse {
    @SerializedName("tvShow")
    @Expose
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShow() {
        return tvShowDetails;
    }

    public void setTvShow(TVShowDetails tvShowDetails) {
        this.tvShowDetails = tvShowDetails;
    }
}
