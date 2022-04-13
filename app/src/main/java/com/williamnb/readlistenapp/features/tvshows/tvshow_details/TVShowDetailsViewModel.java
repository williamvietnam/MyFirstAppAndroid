package com.williamnb.readlistenapp.features.tvshows.tvshow_details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.domain.database.TVShowDatabase;
import com.williamnb.readlistenapp.domain.repository.TVShowDetailsRepository;
import com.williamnb.readlistenapp.remote.models.TVShow;
import com.williamnb.readlistenapp.remote.responses.TVShowDetailsResponse;

import io.reactivex.Completable;

public class TVShowDetailsViewModel extends BaseViewModel {
    private final TVShowDetailsRepository tvShowDetailsRepository;
    private final TVShowDatabase tvShowDatabase;

    public TVShowDetailsViewModel(@NonNull Application application) {
        super(application);
        tvShowDetailsRepository = new TVShowDetailsRepository();
        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }

    public Completable addToWatchList(TVShow tvShow) {
        return tvShowDatabase.tvShowDAO().addToWatchlist(tvShow);
    }
}
