package com.williamnb.readlistenapp.ui.features.tvshows.tvshow_details;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.database.TVShowDatabase;
import com.williamnb.readlistenapp.data.local.database.entities.TVShowEntity;
import com.williamnb.readlistenapp.data.remote.models.responses.TVShowDetailsResponse;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class TVShowDetailsViewModel extends BaseViewModel {

    private final TVShowDatabase tvShowDatabase;
    private TVShowEntity tvShowEntity;

    public TVShowDetailsViewModel(@NonNull Application application) {
        super(application);
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
        MutableLiveData<TVShowDetailsResponse> data = new MutableLiveData<>();
        this.getCompositeDisposable().add(this.getApiService().getTVShowDetails(tvShowId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null) {
                        data.setValue(response);
                    }
                }, throwable -> {
                    Log.d("TVShowDetails: api failure", null);
                    data.setValue(null);
                }));
        return data;
    }

    /**
     * Lưu trữ dữ liệu các bộ phim đã bấm add trong dsach film vào local (RoomDB)
     */
    public Completable addToWatchList(TVShowEntity tvShowEntity) {
        return tvShowDatabase.tvShowDAO().addToWatchlist(tvShowEntity);
    }
}