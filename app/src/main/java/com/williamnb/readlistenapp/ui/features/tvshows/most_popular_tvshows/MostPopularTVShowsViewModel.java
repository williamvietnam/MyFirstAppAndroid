package com.williamnb.readlistenapp.ui.features.tvshows.most_popular_tvshows;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.remote.models.responses.TVShowsResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class MostPopularTVShowsViewModel extends BaseViewModel {

    public MostPopularTVShowsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {
        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        this.getCompositeDisposable().add(this.getApiService().getMostPopularTVShows(page)
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
}