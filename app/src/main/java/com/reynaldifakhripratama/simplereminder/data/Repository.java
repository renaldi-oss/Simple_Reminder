package com.reynaldifakhripratama.simplereminder.data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.reynaldifakhripratama.simplereminder.data.room.AppDatabase;
import com.reynaldifakhripratama.simplereminder.data.room.dao.*;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.*;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;


public class Repository {
    private MovieDAO movieDAO;
    private UserDAO userDAO;
    private WatchlistDAO watchlistDAO;
    private LiveData<List<Movie>> watchlist;
    private LiveData<List<Movie>> movies;


    public Repository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        movieDAO = db.movieDao();
        userDAO = db.userDao();
        watchlistDAO = db.watchlistDao();
        movies = movieDAO.getAll();
    }
    public void insertMovie(Movie movie){
        new InsertMovieAsyncTask(movieDAO).execute(movie);
    }
    public void insertMovies(Movie... movie){
        new InsertMoviesAsyncTask(movieDAO).execute(movie);
    }
    public void insertUser(User user){
        new InsertUserAsyncTask(userDAO).execute(user);
    }
    public void insertWatchlist(Watchlist watchlist){
        new InsertWatchlistAsyncTask(watchlistDAO).execute(watchlist);
    }
    public void deleteWatchlist(Watchlist watchlist){
        new DeleteWatchlistAsyncTask(watchlistDAO).execute(watchlist);
    }
    public void deletesWatchlist(Watchlist... watchlist){
        new DeletesWatchlistAsyncTask(watchlistDAO).execute(watchlist);
    }
    public void deleteAllWatchlist(){
        new DeleteALLWatchlistAsyncTask(watchlistDAO).execute();
    }

    public LiveData<List<Movie>> getWatchlist(User user) {
        watchlist = watchlistDAO.getAll(user.getUsername());
        return watchlist;
    }
    public LiveData<List<Movie>>getMovies(){
        return movies;
    }
    public User getUser(User user){
        return userDAO.getUser(user.getUsername(),user.getPassword());
    }
    //static agar dapat mencegah memory leak
    private static class InsertMovieAsyncTask extends AsyncTask<Movie,Void,Void>{
        private MovieDAO movieDAO;

        public InsertMovieAsyncTask(MovieDAO movieDAO) {
            this.movieDAO = movieDAO;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            this.movieDAO.insert(movies[0]);
            return null;
        }
    }
    private static class InsertMoviesAsyncTask extends AsyncTask<Movie,Void,Void>{
        private MovieDAO movieDAO;

        public InsertMoviesAsyncTask(MovieDAO movieDAO) {
            this.movieDAO = movieDAO;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            this.movieDAO.insertAll(Arrays.asList(movies));
            return null;
        }
    }
    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDAO userDAO;

        public InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            this.userDAO.insert(users[0]);
            return null;
        }
    }
    private static class InsertWatchlistAsyncTask extends AsyncTask<Watchlist,Void,Void>{
                private WatchlistDAO watchlistDAO;

                public InsertWatchlistAsyncTask(WatchlistDAO watchlistDAO) {
                    this.watchlistDAO = watchlistDAO;
                }

                @Override
                protected Void doInBackground(Watchlist... watchlists) {
                    this.watchlistDAO.insert(watchlists[0]);
            return null;
        }
    }
    private static class DeleteWatchlistAsyncTask extends AsyncTask<Watchlist,Void,Void>{
        private WatchlistDAO watchlistDAO;

        public DeleteWatchlistAsyncTask(WatchlistDAO watchlistDAO) {
            this.watchlistDAO = watchlistDAO;
        }

        @Override
        protected Void doInBackground(Watchlist... watchlists) {
            this.watchlistDAO.delete(watchlists[0].getMid());
            return null;
        }
    }
    private static class DeletesWatchlistAsyncTask extends AsyncTask<Watchlist,Void,Void>{
        private WatchlistDAO watchlistDAO;

        public DeletesWatchlistAsyncTask(WatchlistDAO watchlistDAO) {
            this.watchlistDAO = watchlistDAO;
        }

        @Override
        protected Void doInBackground(Watchlist... watchlists) {
            this.watchlistDAO.deletes(watchlists);
            return null;
        }
    }
    private static class DeleteALLWatchlistAsyncTask extends AsyncTask<Void,Void,Void>{
        private WatchlistDAO watchlistDAO;

        public DeleteALLWatchlistAsyncTask(WatchlistDAO watchlistDAO) {
            this.watchlistDAO = watchlistDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.watchlistDAO.deleteALL();
            return null;
        }
    }
}
