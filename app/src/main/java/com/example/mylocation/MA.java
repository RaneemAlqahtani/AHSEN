package com.example.mylocation;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MA extends BaseActivity {
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ma);
        selectedFragment = new AboutUs();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
        selectedFragment.setHasOptionsMenu(true);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         Toolbar toolbar = (Toolbar) findViewById(R.id.MainToolBar);
         setSupportActionBar(toolbar);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_AboutUs:
                    selectedFragment = new AboutUs();
                    break;
                case R.id.navigation_SM:
                    selectedFragment = new NationalID();
                    break;
                case R.id.navigation_FS:
                    selectedFragment = new AccountInfo();
                    break;

                case R.id.navigation_call:
                    selectedFragment = new Call();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
              //  Intent intent= new Intent(MA.this, Home_page.class);
             //   startActivity(intent);
               // finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}