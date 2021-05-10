package com.example.mylocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylocation.DatabaseClasses.User;
import com.example.mylocation.Model.AppDatabase;


public class NationalID extends Fragment implements View.OnClickListener {



    EditText nationalId , password ;
    TextView register_click;
    Button nextBtn;
    private AppDatabase appDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.activity_national_id, container, false);
        nationalId = rootView.findViewById(R.id.national_id_1);
        nextBtn = (Button) rootView.findViewById(R.id.next_button_1);
        nextBtn.setOnClickListener(this);
        password = rootView.findViewById(R.id.password_1);
        appDatabase = AppDatabase.getAppDatabase(getContext());
        long orph_id = MySharedPreference.getLong(getContext(), Constants.keys.ORPHANAGE_ID, 0l);
        if(MySharedPreference.getString(getContext(), Constants.keys.APP_LANGUAGE, "en").matches("en"))
            getActivity().setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getName());
        else
            getActivity().setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getArabic_name());
        register_click = rootView.findViewById(R.id.register_click);
        register_click.setOnClickListener(this);


        appDatabase = AppDatabase.getAppDatabase(getContext());
        return rootView;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.next_button_1:
                if (!nationalId.getText().toString().equals("")){
                    User current = appDatabase.getUserDao().getUser(Long.parseLong(nationalId.getText().toString()));
                    if(current!=null){
                        if(password.getText().toString().equals(current.getPassword())){
                            MySharedPreference.clearValue(getContext(),Constants.keys.USER_ID);
                            MySharedPreference.putLong(getContext(),Constants.keys.USER_ID,current.getId());
                            Intent i = new Intent(getContext(),dates.class);
                            startActivity(i);
                        }//end if
                        else{
                            final AlertDialog alertDialog= new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom).create();
                            alertDialog.setMessage(getString(R.string.not_registered));
                            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }

                            });
                            alertDialog.show();
                        }//end else
                    }else{
                        final AlertDialog alertDialog= new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom).create();
                        alertDialog.setMessage(getString(R.string.not_registered));
                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                            }

                        });
                        alertDialog.show();
                    }//end else
                }else{
                    final AlertDialog alertDialog= new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom).create();
                    alertDialog.setMessage(getString(R.string.empty_fields));
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }

                    });
                    alertDialog.show();
                }//end else

                break;

            case R.id.register_click:
                Intent intent = new Intent(getContext() , PersonalInfo.class);
                startActivity(intent);

                break;
                }//end switch

        }//end onClick
}//end class NationalID

