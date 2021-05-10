package com.example.mylocation;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class Language extends BaseActivity implements View.OnClickListener {

    private LinearLayout ElinearLayout, AlinearLayout;
    private ImageView imgE, imgA;
    private String language;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        setTitle(getString(R.string.languages));
        ElinearLayout = (LinearLayout) findViewById(R.id.lin_english);
        ElinearLayout.setOnClickListener(this);

        AlinearLayout = (LinearLayout) findViewById(R.id.lin_arabic);
        AlinearLayout.setOnClickListener(this);

        language = MySharedPreference.getString(this, Constants.keys.APP_LANGUAGE, "en");
        imgE = (ImageView) findViewById(R.id.english);
        imgA = (ImageView) findViewById(R.id.arabic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.SettingsToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        switch (language){
            case "en":
                imgA.setVisibility(View.INVISIBLE);
                imgE.setVisibility(View.VISIBLE);
                break;
            case "ar":
                imgE.setVisibility(imgE.INVISIBLE);
                imgA.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){

            case R.id.lin_english:
                setLanguage("en");

                break;
            case R.id.lin_arabic:
                setLanguage("ar");
        }
    }

    public void setLanguage(String lan){
        MySharedPreference.putString(this, Constants.keys.APP_LANGUAGE, lan);

        Intent intent = new Intent(this, Splash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent= new Intent(this,MapsActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale("ar");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Splash.class);
        startActivity(refresh);
        finish();
    }
}

