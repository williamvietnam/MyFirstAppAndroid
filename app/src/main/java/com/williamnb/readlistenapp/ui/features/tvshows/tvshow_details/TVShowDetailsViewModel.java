package com.williamnb.readlistenapp.ui.features.tvshows.tvshow_details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.database.TVShowDatabase;
import com.williamnb.readlistenapp.data.repositories.TVShowDetailsRepository;
import com.williamnb.readlistenapp.data.remote.models.TVShow;
import com.williamnb.readlistenapp.data.remote.models.responses.TVShowDetailsResponse;

import io.reactivex.Completable;

public class TVShowDetailsViewModel extends BaseViewModel {
    private final TVShowDetailsRepository tvShowDetailsRepository;
    private final TVShowDatabase tvShowDatabase;

    public TVShowDetailsViewModel(@NonNull Application application) {
        super(application);
        tvShowDetailsRepository = new TVShowDetailsRepository();
        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
    }

    /**
     * Lấy dữ liệu film từ remote qua api
     * */
    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }

    /**
     * Lưu trữ dữ liệu các bộ phim đã bấm add trong dsach film vào local (RoomDB)
     * */
    public Completable addToWatchList(TVShow tvShow) {
        return tvShowDatabase.tvShowDAO().addToWatchlist(tvShow);
    }
}