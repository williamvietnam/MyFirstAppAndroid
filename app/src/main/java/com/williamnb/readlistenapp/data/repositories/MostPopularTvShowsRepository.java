package com.williamnb.readlistenapp.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.williamnb.readlistenapp.data.remote.ApiClient;
import com.williamnb.readlistenapp.data.remote.ApiServices;
import com.williamnb.readlistenapp.data.remote.models.responses.TVShowsResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public class MostPopularTvShowsRepository {
    private final ApiServices apiServices;
    private final CompositeDisposable compositeDisposable;

    public MostPopularTvShowsRepository() {
        this.apiServices = ApiClient.getInstance().getApiService();
        this.compositeDisposable = new CompositeDisposable();
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {
        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        this.compositeDisposable.add(this.apiServices.getMostPopularTVShows(page)
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
