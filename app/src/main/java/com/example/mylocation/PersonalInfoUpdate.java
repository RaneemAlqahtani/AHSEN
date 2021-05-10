package com.example.mylocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.mylocation.DatabaseClasses.User;
import com.example.mylocation.Model.AppDatabase;



public class PersonalInfoUpdate extends BaseActivity implements View.OnClickListener {

   EditText firstName, lastName , email , id , phone_number , nationality , status , birthDate, national_id_editText ;
   String firstName_input , lastName_input , email_input , id_input , phone_number_input , nationality_input , status_input , password_input , birthDate_input ;
   Button updateBtn;
    private AppDatabase appDatabase;
    long national_id;
    User current;
    RadioGroup radioGroup;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_update);

        firstName = (EditText) findViewById(R.id.first_name_update);
        lastName= (EditText) findViewById(R.id.last_name_update);
        email = (EditText) findViewById(R.id.email_update);
        id = (EditText) findViewById(R.id.national_id_update);
        phone_number = (EditText) findViewById(R.id.phone_number_update);
        nationality = (EditText) findViewById(R.id.nationality_update);
        status = (EditText) findViewById(R.id.status_update);
        national_id_editText = findViewById(R.id.national_id_update);
        birthDate = (EditText) findViewById(R.id.birth_date_update);
        toolbar = findViewById(R.id.toolbar_update_info);

        setTitle(getString(R.string.Update));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateBtn= findViewById(R.id.update_button);
        updateBtn.setOnClickListener(this);


        getExtras();
        appDatabase = AppDatabase.getAppDatabase(this);


        fillData();

    }//end onCreate

    private void getExtras(){
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            national_id =intent.getExtras().getLong(Constants.keys.USER_ID);
        }//end if

    }//end getExtras()



    private void fillData() {

        current =appDatabase.getUserDao().getUser(national_id);

        firstName.setText(current.getFirst_name());
        lastName.setText(current.getLast_name());
        email.setText(current.getEmail());
        national_id_editText.setText(""+current.getNationalID());
        birthDate.setText(current.getBirth_date());
        nationality.setText(current.getNationality());
        status.setText(current.getStatus());
        phone_number.setText(current.getPhone());

    }//end fillData()


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.update_button:
                firstName_input = firstName.getText().toString().trim();
                lastName_input = lastName.getText().toString().trim();
                email_input = email.getText().toString().trim();
                id_input = id.getText().toString().trim();
                phone_number_input = phone_number.getText().toString().trim();
                nationality_input = nationality.getText().toString().trim();
                status_input = status.getText().toString().trim();

                if( firstName_input.isEmpty() && lastName_input.isEmpty() && email_input.isEmpty() && id_input.isEmpty() && phone_number_input.isEmpty() && nationality_input.isEmpty() && status_input.isEmpty() && password_input.isEmpty()){

                    final AlertDialog alertDialog= new AlertDialog.Builder(this,R.style.AlertDialogCustom).create();
                    alertDialog.setMessage(getString(R.string.GenderMsg));
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }

                    });
                    alertDialog.show();
                }
                else {

                    current.setFirst_name(firstName_input);
                    current.setLast_name(lastName_input);
                    current.setNationalID(Long.parseLong(national_id_editText.getText().toString()));
                    current.setEmail(email_input);
                    current.setPhone(phone_number_input);
                    current.setNationality(nationality_input);
                    current.setStatus(status_input);
                    current.setBirth_date(birthDate_input);

                    appDatabase.getUserDao().update(current);

                    onBackPressed();


                }//end else
                break;

        }//end switch
    }//end onClick

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
}//end class PersonalInfoUpdate

