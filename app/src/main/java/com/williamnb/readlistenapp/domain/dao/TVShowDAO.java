package com.williamnb.readlistenapp.domain.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.williamnb.readlistenapp.remote.models.TVShow;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface TVShowDAO {

//    @Query("SELECT * FROM tvshows")
//    Flowable<List<TVShow>> getWatchList;


}
