package com.example.mylocation.DatabaseClasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")

public class User {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo
    private String first_name;
    @ColumnInfo
    private String last_name;
    @ColumnInfo
    private String Birth_date;
    @ColumnInfo
    private String gender;
    @ColumnInfo
    private String status;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private long nationalID;
    @ColumnInfo
    private String password;
    @ColumnInfo
    private String email;

    @ColumnInfo
    private String nationality;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNationalID() {
        return nationalID;
    }

    public void setNationalID(long nationalID) {
        this.nationalID = nationalID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return Birth_date;
    }

    public void setBirth_date(String birth_date) {
        Birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
