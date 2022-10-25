package com.reynaldifakhripratama.simplereminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reynaldifakhripratama.simplereminder.data.Model;
import com.reynaldifakhripratama.simplereminder.data.adapter.MovieAdapter;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.Movie;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.User;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.Watchlist;

import java.util.List;

public class WatchlistActivity extends AppCompatActivity {
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);


        rv = findViewById(R.id.recycleView);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        final MovieAdapter adapter = new MovieAdapter();
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        this.model = ViewModelProviders.of(this).get(Model.class);
        model.getWatchlists(new User("admin","admin")).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setMovies(movies);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Movie m = adapter.getMovieAT(viewHolder.getAdapterPosition());
                Watchlist w = new Watchlist("admin",m.getMid());
                model.deleteWatchlist(w);
                Toast.makeText(WatchlistActivity.this, m.getTitle()+" terhapus", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(rv);

        FloatingActionButton btn = findViewById(R.id.btn);
        btn.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WatchlistActivity.this,MovieListActivity.class);
                startActivity(i);
            }
        });

    }

}
