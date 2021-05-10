package com.example.mylocation.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mylocation.Dao.CreditCardDao;
import com.example.mylocation.Dao.DatesDao;
import com.example.mylocation.Dao.MeetingDao;
import com.example.mylocation.Dao.OrphanageDao;
import com.example.mylocation.Dao.UserDao;
import com.example.mylocation.DatabaseClasses.CreditCard;
import com.example.mylocation.DatabaseClasses.Dates;
import com.example.mylocation.DatabaseClasses.Meeting;
import com.example.mylocation.DatabaseClasses.Orphanage;
import com.example.mylocation.DatabaseClasses.User;



@Database(entities = {Orphanage.class, User.class, Dates.class, Meeting.class, CreditCard.class},version = 1)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase
            INSTANCE;
    public abstract OrphanageDao getOrphanageDao();
    public abstract DatesDao getDatesDao();
    public abstract MeetingDao getMeetingDao();
    public abstract UserDao getUserDao();
    public abstract CreditCardDao getCreditcardDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {

            INSTANCE =
                    Room.
                            databaseBuilder(context.getApplicationContext(), AppDatabase.class, "survey-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return
                INSTANCE;
    }//end getAppDatabase
    public static void destroyInstance() {

        INSTANCE = null;
    }//end destroyInstance
}//end class
