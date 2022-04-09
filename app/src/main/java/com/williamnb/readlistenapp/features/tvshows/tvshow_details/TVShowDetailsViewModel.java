package com.williamnb.readlistenapp.features.tvshows.tvshow_details;

import androidx.lifecycle.LiveData;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.domain.repository.TVShowDetailsRepository;
import com.williamnb.readlistenapp.remote.responses.TVShowDetailsResponse;

public class TVShowDetailsViewModel extends BaseViewModel {
    private TVShowDetailsRepository tvShowDetailsRepository;

    public TVShowDetailsViewModel() {
        tvShowDetailsRepository = new TVShowDetailsRepository();
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }
}
