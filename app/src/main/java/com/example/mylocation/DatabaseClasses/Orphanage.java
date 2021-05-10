package com.example.mylocation.DatabaseClasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "orphanage")

public class Orphanage {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String arabic_name;
    @ColumnInfo
    private String aboutUS;
    @ColumnInfo
    private String aboutUS_arabic;
    @ColumnInfo
    private String location;
    @ColumnInfo
    private String Arabic_location;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;
    @ColumnInfo
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArabic_location() {
        return Arabic_location;
    }

    public void setArabic_location(String arabic_location) {
        Arabic_location = arabic_location;
    }

    public String getAboutUS_arabic() {
        return aboutUS_arabic;
    }

    public void setAboutUS_arabic(String aboutUS_arabic) {
        this.aboutUS_arabic = aboutUS_arabic;
    }

    public String getArabic_name() {
        return arabic_name;
    }

    public void setArabic_name(String arabic_name) {
        this.arabic_name = arabic_name;
    }

    public String getAboutUS() {
        return aboutUS;
    }

    public void setAboutUS(String aboutUS) {
        this.aboutUS = aboutUS;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
