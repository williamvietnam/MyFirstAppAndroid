package com.williamnb.readlistenapp.ui.features.tvshows.tvshow_search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.remote.models.responses.TVShowsResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TvShowsSearchingViewModel extends BaseViewModel {
    private final MutableLiveData<TVShowsResponse> dataSearchList;

    public TvShowsSearchingViewModel(@NonNull Application application) {
        super(application);
        this.dataSearchList = new MutableLiveData<>();
    }

    public LiveData<TVShowsResponse> getTVShowsSearchingList(String keyword, int page) {
        this.getCompositeDisposable().add(
                getApiService().getSearchTVShowList(keyword, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(tvShowsResponse -> {
                            if (tvShowsResponse != null) {
                                dataSearchList.setValue(tvShowsResponse);
                            }
                        }, throwable -> {
                            dataSearchList.setValue(null);
                        }));
        return this.dataSearchList;
    }
}