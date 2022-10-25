package com.reynaldifakhripratama.simplereminder.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.*;

import java.util.List;

@Dao
public interface WatchlistDAO {
    @Query("SELECT * FROM movies INNER JOIN watchlists ON movies.movie_id = watchlists.mid WHERE watchlists.username = :username")
    LiveData<List<Movie>> getAll(String username);

    @Insert
    void insert(Watchlist watchlist);
    @Query("DELETE FROM watchlists WHERE mid = :mid")
    void delete(String mid);

    @Delete
    void deletes(Watchlist... watchlists);

    @Query("DELETE FROM watchlists")
    void deleteALL();
}
