package com.williamnb.readlistenapp.common;

import com.williamnb.readlistenapp.remote.models.TVShow;

public interface TVShowsListener {
    void onTVShowClicked(TVShow tvShow);
}
