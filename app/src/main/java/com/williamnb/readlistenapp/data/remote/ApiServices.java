package com.williamnb.readlistenapp.data.remote;

import com.williamnb.readlistenapp.data.remote.models.responses.TVShowDetailsResponse;
import com.williamnb.readlistenapp.data.remote.models.responses.TVShowsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: William Giang Nguyen | 15/08/2022
 * */
public interface ApiServices {

    @GET(ApiEndpoint.GET_MOST_POPULAR_TV_SHOWS)
    Single<TVShowsResponse> getMostPopularTVShows(@Query("page") int page);

    @GET(ApiEndpoint.GET_TV_SHOWS_DETAIL)
    Single<TVShowDetailsResponse> getTVShowDetails(@Query("q") String tvShowId);

}