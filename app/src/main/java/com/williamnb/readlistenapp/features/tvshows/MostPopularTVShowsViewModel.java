package com.williamnb.readlistenapp.features.tvshows;

import androidx.lifecycle.LiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.domain.repository.MostPopularTvShowsRepository;
import com.williamnb.readlistenapp.remote.responses.TVShowsResponse;

public class MostPopularTVShowsViewModel extends BaseViewModel {

    private final MostPopularTvShowsRepository mostPopularTvShowsRepository;

    public MostPopularTVShowsViewModel(){
        mostPopularTvShowsRepository = new MostPopularTvShowsRepository();
    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page){
        return mostPopularTvShowsRepository.getMostPopularTVShows(page);
    }
}
