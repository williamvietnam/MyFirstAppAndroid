package com.williamnb.readlistenapp.domain.remote.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.williamnb.readlistenapp.domain.models.TVShow;

import java.util.List;

public class TVShowsResponse {
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("tv_shows")
    @Expose
    private List<TVShow> TVShows;

    public String getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public List<TVShow> getTvShows() {
        return TVShows;
    }
}
