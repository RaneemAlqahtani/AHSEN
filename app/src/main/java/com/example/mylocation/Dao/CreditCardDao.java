package com.example.mylocation.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mylocation.DatabaseClasses.CreditCard;

import java.util.List;



@Dao
public interface CreditCardDao {

    @Insert
    long insert(CreditCard card);

    @Delete
    void delete(CreditCard card);

    @Update
    void update(CreditCard card);

    @Query("SELECT * FROM creditCard where orphanage_id = :id")
    List<CreditCard> getAllCreditCards(long id);
}
