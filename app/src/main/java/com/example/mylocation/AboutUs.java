package com.example.mylocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mylocation.DatabaseClasses.Orphanage;
import com.example.mylocation.Model.AppDatabase;


public class AboutUs  extends Fragment {

    View view2;
    TextView description;
    AppDatabase appDatabase;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view2 = inflater.inflate(R.layout.about_us, container, false);
        description = view2.findViewById(R.id.about_us_text);

        appDatabase = AppDatabase.getAppDatabase(getContext());
        long orph_id = MySharedPreference.getLong(getContext(), Constants.keys.ORPHANAGE_ID, 0l);
        if(MySharedPreference.getString(getContext(), Constants.keys.APP_LANGUAGE, "en").matches("en"))
            getActivity().setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getName());
        else
            getActivity().setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getArabic_name());
        long id = MySharedPreference.getLong(getContext(),Constants.keys.ORPHANAGE_ID,0L);
        appDatabase = AppDatabase.getAppDatabase(getContext());


        Orphanage orphanage = appDatabase.getOrphanageDao().getOrphanage(id);
        String language = MySharedPreference.getString(getContext(),Constants.keys.APP_LANGUAGE,"");
        if(language.equals("ar")){
            //name.setText(orphanage.getArabic_name());
            if(orphanage.getAboutUS_arabic()!=null) {
                description.setText(orphanage.getAboutUS_arabic());
            }
            else if(orphanage.getAboutUS()!=null) {
                description.setText(orphanage.getAboutUS());
            }
        } else{
            //name.setText(orphanage.getName());
            if(orphanage.getAboutUS()!=null) {
                description.setText(orphanage.getAboutUS());
            }else if(orphanage.getAboutUS_arabic()!=null) {
                description.setText(orphanage.getAboutUS_arabic());
            }
        }
        return view2;
    }




}

