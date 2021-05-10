package com.example.mylocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.example.mylocation.DatabaseClasses.Dates;
import com.example.mylocation.DatabaseClasses.Meeting;
import com.example.mylocation.Model.AppDatabase;



public class Reason extends BaseActivity implements View.OnClickListener {

    AppDatabase appDatabase;
    EditText why;
    Bundle bundle;
    long num=0;
    Button sendBtn ;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason);
        toolbar = findViewById(R.id.toolbar_reason);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appDatabase = AppDatabase.getAppDatabase(this);
        why = findViewById(R.id.why);
        bundle = getIntent().getExtras();

        sendBtn = (Button) findViewById(R.id.send_button);
        sendBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(!why.equals("")){
            Meeting meeting = new Meeting();
            meeting.setDate_id(bundle.getLong("DateID"));
            meeting.setOrphanage_id(MySharedPreference.getLong(this, Constants.keys.ORPHANAGE_ID,num));
            meeting.setUser_id(MySharedPreference.getLong(this, Constants.keys.USER_ID,num));
            meeting.setReason(why.getText().toString());
            appDatabase.getMeetingDao().insert(meeting);
            Dates date = appDatabase.getDatesDao().getDate(bundle.getLong("DateID"));
            date.setAvailability(false);
            appDatabase.getDatesDao().update(date);
            finish();
        }//end if
        else{
            final AlertDialog alertDialog= new AlertDialog.Builder(this,R.style.AlertDialogCustom).create();
            alertDialog.setMessage(getString(R.string.empty_fields));
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                }

            });
            alertDialog.show();
        }

    }//end onClick

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Reason.this,dates.class);
                startActivity(i);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}//end class Reason
