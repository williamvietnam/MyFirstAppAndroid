package com.williamnb.readlistenapp.domain.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.williamnb.readlistenapp.remote.models.TVShow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TVShowDAO {

    @Query("SELECT * FROM tvshows")
    Flowable<List<TVShow>> getWatchList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchlist(TVShow tvShow);

    @Delete
    void removeFromWatchlist(TVShow tvShow);
}
