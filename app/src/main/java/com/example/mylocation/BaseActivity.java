package com.example.mylocation;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {//start oncreate
        super.onCreate(savedInstanceState);
        //set lqanguage
        handleDefaultAppLocale();
    }//end oncreate()

    private void handleDefaultAppLocale() {
        setAppLocale(MySharedPreference.getString(BaseActivity.this, Constants.keys.APP_LANGUAGE, getSystemLocalLanguage()));
    }

    private String getSystemLocalLanguage() {//start getSystemlocale
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getSystem().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getSystem().getConfiguration().locale;
        }
        return locale.getLanguage();
    }//close getSystemLocale

    private void setAppLocale(String Language) {

        MySharedPreference.putString(BaseActivity.this, Constants.keys.APP_LANGUAGE, Language);
        Locale locale = new Locale(Language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
    }
}//end class
