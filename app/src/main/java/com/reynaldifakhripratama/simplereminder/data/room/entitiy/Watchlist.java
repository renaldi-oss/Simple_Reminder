package com.reynaldifakhripratama.simplereminder.data.room.entitiy;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "watchlists",indices = {@Index(value = {"username","mid"},unique = true)})
public class Watchlist {
    @PrimaryKey(autoGenerate = true)
    private int wlist;
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String mid;

    public Watchlist(String username, String mid) {
        this.username = username;
        this.mid = mid;
    }

    public Watchlist() {
    }

    public int getWlist() {
        return wlist;
    }

    public void setWlist(int wlist) {
        this.wlist = wlist;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }


}
