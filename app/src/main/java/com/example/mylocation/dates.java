package com.example.mylocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylocation.DatabaseClasses.Dates;
import com.example.mylocation.Model.AppDatabase;

import java.util.ArrayList;
import java.util.List;



public class dates extends AppCompatActivity {

    AppDatabase appDatabase;
    RecyclerView rc;
    List<Dates> datesList;
    DateAdapter adapter;
    long num=0;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dates);
        toolbar = findViewById(R.id.toolbar_date);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        long id = MySharedPreference.getLong(this, Constants.keys.ORPHANAGE_ID,num);
        appDatabase = AppDatabase.getAppDatabase(this);
        rc = findViewById(R.id.dates_recycler);
        datesList = new ArrayList<>();
        datesList.addAll(appDatabase.getDatesDao().getAllDates(id,true));
        adapter = new DateAdapter(this,datesList);
        long orph_id = MySharedPreference.getLong(dates.this, Constants.keys.ORPHANAGE_ID, 0l);
        if(MySharedPreference.getString(dates.this, Constants.keys.APP_LANGUAGE, "en").matches("en"))
            setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getName());
        else
            setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getArabic_name());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(dates.this, 1);
        rc.setLayoutManager(layoutManager);
        rc.setItemAnimator(new DefaultItemAnimator());
        rc.setAdapter(adapter);
        rc.addOnItemTouchListener(new RecyclerTouchListener(this, rc, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Dates date = datesList.get(position);
                Intent intent = new Intent(dates.this, Reason.class);
                intent.putExtra("DateID",date.getId());
                startActivity(intent);
                finish();
            }//end onClick

            @Override
            public void onLongClick(View view, int position) {

            }//end onLongClick
        }));
    }//end onCreate
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}//end class dates
