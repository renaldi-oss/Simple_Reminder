package com.reynaldifakhripratama.simplereminder.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.*;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAll();

    @Query("SELECT * FROM movies WHERE title LIKE :title ")
    List<Movie>findByTitle(String title);

    @Query("SELECT * FROM movies ORDER BY rank DESC")
    List<Movie> getAllOrderRankDesc();

    @Insert
    void insertAll(List<Movie> movies);
    @Insert
    void insert(Movie movie);

}
