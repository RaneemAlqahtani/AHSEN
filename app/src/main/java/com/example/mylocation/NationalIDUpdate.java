package com.example.mylocation;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.mylocation.DatabaseClasses.User;
import com.example.mylocation.Model.AppDatabase;




public class NationalIDUpdate extends BaseActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private EditText national , password;
    private AppDatabase appDatabase;
   private Button nextBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_national_id_update);

        national = findViewById(R.id.national_id_2);
        password = findViewById(R.id.password_2_update);
        nextBtn = findViewById(R.id.next_button_2);
        toolbar = findViewById(R.id.toolbar_update);
        setTitle(getString(R.string.Update));
        nextBtn.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appDatabase = AppDatabase.getAppDatabase(this);

    }//end onCreate

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.next_button_2:
                if(!national.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    User current = appDatabase.getUserDao().getUser(Long.parseLong(national.getText().toString()));
                    if (current != null) {
                        if (password.getText().toString().equals(current.getPassword())) {
                            Intent i = new Intent(this, PersonalInfoUpdate.class);
                            i.putExtra(Constants.keys.USER_ID, Long.parseLong(national.getText().toString()));
                            startActivity(i);
                            finish();
                        }//end if
                        else{
                            final AlertDialog alertDialog= new AlertDialog.Builder(this,R.style.AlertDialogCustom).create();
                            alertDialog.setMessage(getString(R.string.email_or_password_is_wrong));
                            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }

                            });
                            alertDialog.show();
                    }
                    }//end if(current!=null)
                    else{

                        final AlertDialog alertDialog= new AlertDialog.Builder(this,R.style.AlertDialogCustom).create();
                        alertDialog.setMessage(getString(R.string.email_or_password_is_wrong));
                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                            }

                        });
                        alertDialog.show();                    }
                } else{
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

        }//end switch
    }//end  onClick

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

}//end class NationalIDUpdate
