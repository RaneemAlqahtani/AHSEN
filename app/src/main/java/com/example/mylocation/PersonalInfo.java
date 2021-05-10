package com.example.mylocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.mylocation.DatabaseClasses.User;
import com.example.mylocation.Model.AppDatabase;




public class PersonalInfo extends BaseActivity implements View.OnClickListener {

    EditText firstName, lastName , email , id , phone_number , nationality , status , password , confirm_password , birthDate;
    String firstName_input , lastName_input , email_input , id_input , phone_number_input , nationality_input , status_input , password_input , confirm_password_input , birthDate_input ;
    Button registerBtn;
    private Toolbar mTopToolbar;
    private AppDatabase appDatabase;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        mTopToolbar = findViewById(R.id.toolbar_personal_info);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        radioGroup=findViewById(R.id.gender_radio_group);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password  = (EditText) findViewById(R.id.password);
        confirm_password  = (EditText) findViewById(R.id.confirm_password);
        id = (EditText) findViewById(R.id.national_id);
        phone_number = (EditText) findViewById(R.id.phone_number);
        nationality = (EditText) findViewById(R.id.nationality);
        birthDate = (EditText) findViewById(R.id.birth_date);
        status = (EditText) findViewById(R.id.status);
        registerBtn = (Button) findViewById(R.id.register_button);
        registerBtn.setOnClickListener(this);

        appDatabase = AppDatabase.getAppDatabase(this);
    }

    @Override
    public void onClick(View view) {
        int radioButtonID = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton)

                radioGroup.findViewById(radioButtonID);
        switch (view.getId()) {
            case R.id.register_button:
                firstName_input = firstName.getText().toString().trim();
                lastName_input = lastName.getText().toString().trim();
                email_input = email.getText().toString().trim();
                password_input = password.getText().toString().trim();
                confirm_password_input =confirm_password.getText().toString().trim();
                id_input = id.getText().toString().trim();
                phone_number_input = phone_number.getText().toString().trim();
                nationality_input = nationality.getText().toString().trim();
                status_input = status.getText().toString().trim();
                birthDate_input = birthDate.getText().toString().trim();

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
                    //Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                    User user = appDatabase.getUserDao().getUser(Long.parseLong(id_input));
                    if(user==null) {
                        User current = new User();
                        current.setFirst_name(firstName_input);
                        current.setLast_name(lastName_input);
                        current.setEmail(email_input);
                        current.setPassword(password_input);
                        current.setPhone(phone_number_input);
                        current.setNationality(nationality_input);
                        current.setStatus(status_input);
                        current.setBirth_date(birthDate_input);
                        current.setNationalID(Long.parseLong(id_input));
                        Toast.makeText(this,radioButtonID+"   ,  "+R.id.male_radio_btn,Toast.LENGTH_LONG).show();
                        if (radioButtonID==R.id.male_radio_btn)
                            current.setGender("Male");
                        else if(radioButtonID==R.id.female_radio_btn)
                            current.setGender("Female");
                        else{
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
                        if(current.getGender() != null){
                            long USER_id = appDatabase.getUserDao().insert(current);

                            MySharedPreference.clearValue(this,Constants.keys.USER_ID);
                            MySharedPreference.putLong(this,Constants.keys.USER_ID,USER_id);
                            Intent intent = new Intent(PersonalInfo.this, dates.class);
                            startActivity(intent);
                            finish();
                        }
                    } else{

                    }

                }//end else
                break;

        }//end switch
    }//end onClick

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}//end class PersonalInfo
