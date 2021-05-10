package com.example.mylocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        linearLayout = (LinearLayout) findViewById(R.id.linear_splash);
        //getSupportActionBar().hide();
        linearLayout.setOnClickListener(this);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                    Intent intent = new Intent(Splash.this, Stories.class);
                    startActivity(intent);
            }
        },3000);


    }//End onCreate()

    @Override
    public void onClick(View view) {
            Intent intent = new Intent(Splash.this, Stories.class);
            startActivity(intent);
    }//End method

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }//End method


}//End Class
