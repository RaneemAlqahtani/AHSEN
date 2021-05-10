package com.example.mylocation.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mylocation.DatabaseClasses.CreditCard;
import com.example.mylocation.DatabaseClasses.Orphanage;

import java.util.List;




@Dao

public interface OrphanageDao {

    @Insert
    long insert(Orphanage orphanage);

    @Update
    void update(Orphanage orphanage);

    @Delete
    void delete(Orphanage orphanage);

    @Query("SELECT Count(*) FROM ORPHANAGE")
    int getCount();

    @Query("SELECT * FROM orphanage")
    List<Orphanage> getAllOrphanages();

    @Query("SELECT phone FROM orphanage WHERE id = :id")
    String getOrphanagePhone(long id);

    @Query("SELECT * FROM CREDITCARD WHERE orphanage_id = :id")
    List<CreditCard> getAllCards(long id);

    @Query("SELECT * FROM orphanage WHERE location = :location")
    List<Orphanage> filterOrphanage(String location);

    @Query("SELECT * FROM orphanage WHERE Arabic_location = :location")
    List<Orphanage> filterArabicOrphanage(String location);

    @Query("Select * from orphanage where id =:id")
    Orphanage getOrphanage(long id);

}
