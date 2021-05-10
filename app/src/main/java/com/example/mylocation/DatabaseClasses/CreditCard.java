package com.example.mylocation.DatabaseClasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "creditCard",foreignKeys = @ForeignKey(entity = Orphanage.class, parentColumns = "id", childColumns = "orphanage_id", onDelete = CASCADE))

public class CreditCard {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo
    private String bank;
    @ColumnInfo
    private String Arabic_bank;
    @ColumnInfo
    private String number;
    @ColumnInfo
    private long orphanage_id;

    public String getArabic_bank() {
        return Arabic_bank;
    }

    public void setArabic_bank(String arabic_bank) {
        Arabic_bank = arabic_bank;
    }

    public long getOrphanage_id() {
        return orphanage_id;
    }

    public void setOrphanage_id(long orphanage_id) {
        this.orphanage_id = orphanage_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
