package com.example.mylocation.DatabaseClasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "meeting",foreignKeys = {@ForeignKey(entity = Orphanage.class, parentColumns = "id", childColumns = "orphanage_id", onDelete = CASCADE),
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = CASCADE),
        @ForeignKey(entity = Dates.class, parentColumns = "id", childColumns = "date_id", onDelete = CASCADE)})


public class Meeting {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private long orphanage_id;
    @ColumnInfo
    private long user_id;
    @ColumnInfo
    private long date_id;
    @ColumnInfo
    private String reason;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrphanage_id() {
        return orphanage_id;
    }

    public void setOrphanage_id(long orphanage_id) {
        this.orphanage_id = orphanage_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getDate_id() {
        return date_id;
    }

    public void setDate_id(long date_id) {
        this.date_id = date_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
