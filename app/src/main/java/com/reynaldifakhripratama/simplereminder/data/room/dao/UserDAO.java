package com.reynaldifakhripratama.simplereminder.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.reynaldifakhripratama.simplereminder.data.room.entitiy.*;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User getUser(String username,String password);

    @Query("SELECT * FROM users")
    List<User>getAllUser();

    @Insert
    void insert(User user);
    
    @Delete
    void delete(User user);


}
