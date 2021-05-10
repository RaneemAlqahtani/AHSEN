package com.example.mylocation.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.mylocation.DatabaseClasses.Meeting;



@Dao

public interface MeetingDao {

    @Insert
    long insert(Meeting meeting);

    @Delete
    void delete(Meeting meeting);

    @Update
    void update(Meeting meeting);
}
