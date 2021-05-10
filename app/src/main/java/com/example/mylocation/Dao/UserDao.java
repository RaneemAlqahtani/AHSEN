package com.example.mylocation.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mylocation.DatabaseClasses.User;



@Dao

public interface UserDao {

    @Insert
    long insert(User u);

    @Update
    void update(User u);

    @Delete
    void delete(User u);

    @Query("SELECT Count(*) FROM User")
    int getCount();

    @Query("SELECT * FROM User where nationalID = :id")
    User getUser(Long id);
}
