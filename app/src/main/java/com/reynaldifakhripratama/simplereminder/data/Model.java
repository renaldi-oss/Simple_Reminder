package com.reynaldifakhripratama.simplereminder.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.reynaldifakhripratama.simplereminder.data.room.entitiy.Movie;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.User;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.Watchlist;

import java.util.List;

public class Model extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Movie>> allMovies;
    private User user;
    private LiveData<List<Movie>> allWatchlist;

    public Model(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allMovies = repository.getMovies();
    }

    public void insertUser(User user){
        repository.insertUser(user);
    }
    public void insertMovies(Movie... movie){
        repository.insertMovies(movie);
    }
    public void insertWatchlist(Watchlist watchlist){
        repository.insertWatchlist(watchlist);
    }
    public void deleteWatchlist(Watchlist watchlist){
        repository.deleteWatchlist(watchlist);
    }
    public void deleteAllWatchlist(){
        repository.deleteAllWatchlist();
    }
    public User getUser(User user){
        return repository.getUser(user);
    }
    public LiveData<List<Movie>> getMovies(){
        return repository.getMovies();
    }
    public LiveData<List<Movie>> getWatchlists(User user){
        return repository.getWatchlist(user);
    }


}
