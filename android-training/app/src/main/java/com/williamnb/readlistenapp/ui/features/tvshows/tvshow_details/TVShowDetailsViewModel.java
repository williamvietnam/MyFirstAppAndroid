package com.williamnb.readlistenapp.ui.features.tvshows.tvshow_details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.database.TVShowDatabase;
import com.williamnb.readlistenapp.data.local.database.entities.TVShowEntity;
import com.williamnb.readlistenapp.data.remote.models.responses.TVShowDetailsResponse;
import com.williamnb.readlistenapp.data.repositories.TVShowDetailsRepository;

import io.reactivex.Completable;

public class TVShowDetailsViewModel extends BaseViewModel {

    private final TVShowDetailsRepository tvShowDetailsRepository;
    private final TVShowDatabase tvShowDatabase;
    private TVShowEntity tvShowEntity;

    public TVShowDetailsViewModel(@NonNull Application application) {
        super(application);
        tvShowDetailsRepository = new TVShowDetailsRepository();
        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
        this.tvShowEntity = new TVShowEntity();
    }

    public TVShowEntity getTvShowEntity() {
        return tvShowEntity;
    }

    public void setTvShowEntity(TVShowEntity tvShowEntity) {
        this.tvShowEntity = tvShowEntity;
    }

    /**
     * Lấy dữ liệu film từ remote qua api
     */
    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }

    /**
     * Lưu trữ dữ liệu các bộ phim đã bấm add trong dsach film vào local (RoomDB)
     */
    public Completable addToWatchList(TVShowEntity tvShowEntity) {
        return tvShowDatabase.tvShowDAO().addToWatchlist(tvShowEntity);
    }
}