package com.williamnb.readlistenapp.data.local.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.williamnb.readlistenapp.data.local.database.entities.TVShowEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TVShowDAO {

    @Query("SELECT * FROM tvshows")
    Flowable<List<TVShowEntity>> getWatchList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchlist(TVShowEntity tvShow);

    @Delete
    void removeFromWatchlist(TVShowEntity tvShow);
}