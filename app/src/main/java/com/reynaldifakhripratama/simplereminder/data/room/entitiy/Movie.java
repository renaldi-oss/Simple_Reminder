package com.reynaldifakhripratama.simplereminder.data.room.entitiy;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "movie_id")
    private String mid;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String image;

    @ColumnInfo
    private String crew;

    @ColumnInfo
    private int rank;

    @ColumnInfo
    private int rating;

    @ColumnInfo(name = "rating_count")
    private int rcount;

    public Movie(String mid, String title, String image, String crew, int rank, int rating, int rcount) {
        this.mid = mid;
        this.title = title;
        this.image = image;
        this.crew = crew;
        this.rank = rank;
        this.rating = rating;
        this.rcount = rcount;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }
}
