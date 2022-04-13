package com.williamnb.readlistenapp.features.tvshows.most_popular_tvshows;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.domain.repository.MostPopularTvShowsRepository;
import com.williamnb.readlistenapp.remote.responses.TVShowsResponse;

public class MostPopularTVShowsViewModel extends BaseViewModel {

    private final MostPopularTvShowsRepository mostPopularTvShowsRepository;

    public MostPopularTVShowsViewModel(@NonNull Application application){
        super(application);
        mostPopularTvShowsRepository = new MostPopularTvShowsRepository();
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page){
        return mostPopularTvShowsRepository.getMostPopularTVShows(page);
    }
}
