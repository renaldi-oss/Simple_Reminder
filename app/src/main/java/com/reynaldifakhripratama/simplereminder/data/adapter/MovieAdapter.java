package com.reynaldifakhripratama.simplereminder.data.adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.reynaldifakhripratama.simplereminder.R;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.Movie;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.Watchlist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    //deklarasi variable
    private List<Movie> movies = new ArrayList<>();

    public MovieAdapter(List<Movie> movies){
        this.movies = movies;

        Log.d(TAG, "MovieAdapter: CREATED");
    }
    public MovieAdapter(){

    }
    public MovieAdapter(LiveData<List<Movie>> context){

    }
    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView rank,title,crew,rating,rcount;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            rank = itemView.findViewById(R.id.rank);
            crew = itemView.findViewById(R.id.crew);
            rating = itemView.findViewById(R.id.rating);
            rcount = itemView.findViewById(R.id.r_count);

        }
    }
    public void setMovies(List<Movie> movie){
        this.movies = movie;
        notifyDataSetChanged();
    }

    //deklarasi layout
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout,parent,false);
        MovieViewHolder mvh = new MovieViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);

        //load gambar dari url mengunakan picassco
        Picasso.get()
                .load(currentMovie.getImage())
                .placeholder(R.mipmap.pepe)
                .resize(125,200)
                .into(holder.image);
            holder.rank.setText(Integer.toString(currentMovie.getRank()));
            holder.title.setText(currentMovie.getTitle());
            holder.crew.setText(currentMovie.getCrew());
            holder.rating.setText("Rating : "+String.valueOf(currentMovie.getRating()));
            holder.rcount.setText(Integer.toString(currentMovie.getRcount()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    //dipangil untuk menemukan item di posisi tertentu
    public Watchlist getWatchlist(int position){
        Movie m = movies.get(position);
        Watchlist w = new Watchlist("admin",m.getMid());
        return w;
    }
    public Movie getMovieAT(int position){
        return movies.get(position);
    }

}
