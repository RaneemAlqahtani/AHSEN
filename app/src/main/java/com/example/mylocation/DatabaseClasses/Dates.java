package com.example.mylocation.DatabaseClasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "date",foreignKeys = @ForeignKey(entity = Orphanage.class, parentColumns = "id", childColumns = "orphanage_id", onDelete = CASCADE))

public class Dates {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private String dateArabic;
    @ColumnInfo
    private String time;
    @ColumnInfo
    private boolean availability = true;
    @ColumnInfo
    private long orphanage_id;


    public String getDateArabic() {
        return dateArabic;
    }

    public void setDateArabic(String dateArabic) {
        this.dateArabic = dateArabic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public long getOrphanage_id() {
        return orphanage_id;
    }

    public void setOrphanage_id(long orphanage_id) {
        this.orphanage_id = orphanage_id;
    }
}
