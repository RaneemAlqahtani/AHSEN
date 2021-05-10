package com.example.mylocation.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mylocation.DatabaseClasses.Dates;

import java.util.List;



@Dao

public interface DatesDao {

    @Insert
    long insert(Dates meeting);

    @Delete
    void delete(Dates meeting);

    @Update
    void update(Dates meeting);

    @Query("SELECT * FROM date where orphanage_id = :id AND availability =:True")
    List<Dates> getAllDates(long id, boolean True);

    @Query("SELECT * from date where id = :id")
    Dates getDate(long id);
}
