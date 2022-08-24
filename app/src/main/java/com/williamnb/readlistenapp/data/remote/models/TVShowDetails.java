package com.williamnb.readlistenapp.data.remote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public class TVShowDetails {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("permalink")
    @Expose
    private String permalink;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("description_source")
    @Expose
    private String descriptionSource;

    @SerializedName("start_date")
    @Expose
    private String startDate;

    @SerializedName("end_date")
    @Expose
    private Object endDate;

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("runtime")
    @Expose
    private Integer runtime;

    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("youtube_link")
    @Expose
    private Object youtubeLink;

    @SerializedName("image_path")
    @Expose
    private String imagePath;

    @SerializedName("image_thumbnail_path")
    @Expose
    private String imageThumbnailPath;

    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("rating_count")
    @Expose
    private String ratingCount;
    @SerializedName("countdown")
    @Expose
    private Object countdown;

    @SerializedName("genres")
    @Expose
    private String[] genres;

    @SerializedName("pictures")
    @Expose
    private String[] pictures;

    @SerializedName("episodes")
    @Expose
    private List<Episode> episodes;

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getRating() {
        return rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public String[] getPictures() {
        return pictures;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}