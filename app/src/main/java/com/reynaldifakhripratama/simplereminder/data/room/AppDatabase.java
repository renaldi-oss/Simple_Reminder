package com.reynaldifakhripratama.simplereminder.data.room;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.*;
import com.reynaldifakhripratama.simplereminder.data.room.dao.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Database(entities = {User.class,Movie.class,Watchlist.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    public abstract MovieDAO movieDao();
    public abstract WatchlistDAO watchlistDao();
    private static Context ctx;
    //singleton == satu untuk satu app
    private static volatile AppDatabase INSTANCE;
    //synchronized : hanya 1 thread dapat mengakses method ini agar tidak membuat instace baru
    public static synchronized AppDatabase getInstance(final Context context) {
        ctx = context;
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "simple-reminder.db")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .allowMainThreadQueries()
                        .build();
                Log.d("db", "db_dibuat");
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "onCreate: callback");
            new PopulateDbAsyncTask(INSTANCE).execute();
        }

    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private MovieDAO movieDAO;
        private UserDAO userDAO;
        private WatchlistDAO watchlistDAO;

        public PopulateDbAsyncTask(AppDatabase db) {
            this.movieDAO = db.movieDao();
            this.userDAO = db.userDao();
            this.watchlistDAO = db.watchlistDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDAO.insert(new User("admin","admin"));
            Log.d("db", "akun_REDE");

            //inisiasi request
            RequestQueue queue = Volley.newRequestQueue(ctx);
            //link public api dan kunci
            String url = "https://imdb-api.com/en/API/Top250Movies/k_yy125oc6";
            //perintah request ke url
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    //jika berhasil
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                JSONArray jsonArray = response.getJSONArray("items");

                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject items = jsonArray.getJSONObject(i);
                                    Movie m = new Movie(
                                            items.getString("id"),
                                            items.getString("title"),
                                            items.getString("image"),
                                            items.getString("crew"),
                                            items.getInt("rank"),
                                            items.getInt("imDbRating"),
                                            items.getInt("imDbRatingCount")
                                    );
                                    movieDAO.insert(m);
                                }
                                Log.d(TAG, "onResponse: data saved");

                            } catch (JSONException e) {
                                Log.e("FAIL","json error ???");
                                e.printStackTrace();
                            }
                        }
                    },
                    //jika error
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("FAIL","Fail Fetch ???");
                            error.printStackTrace();
                        }
                    });
            //tambah perintah request dan eksekusi
            queue.add(request);

            //tambah data watchlist MANUAL
            watchlistDAO.insert(new Watchlist("admin","tt0111161"));
            watchlistDAO.insert(new Watchlist("admin","tt0068646"));
            watchlistDAO.insert(new Watchlist("admin","tt0071562"));

            return null;
        }

    }



}
