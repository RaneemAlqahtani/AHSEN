package com.example.mylocation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.mylocation.DatabaseClasses.Orphanage;
import com.example.mylocation.Model.AppDatabase;


import static android.Manifest.permission.CALL_PHONE;

public class Call extends Fragment {

    AppDatabase appDatabase;
    TextView number, findUs;
    LinearLayout lin;
    private String link;
    Spanned text;
    ImageView Call;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.call, container, false);
        Call=(ImageView)rootView.findViewById(R.id.CallNumber);
        appDatabase = AppDatabase.getAppDatabase(getContext());
        long orph_id = MySharedPreference.getLong(getContext(), Constants.keys.ORPHANAGE_ID, 0l);
        findUs = rootView.findViewById(R.id.find_us);
        number = rootView.findViewById(R.id.Number);
        link = appDatabase.getOrphanageDao().getOrphanage(orph_id).getLink();
        text = Html.fromHtml("<a href="+link+">"+getString(R.string.find_us)+"</a>");
        findUs.setMovementMethod(LinkMovementMethod.getInstance());
        findUs.setText(text);
        if(MySharedPreference.getString(getContext(), Constants.keys.APP_LANGUAGE, "en").matches("en"))
            getActivity().setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getName());
        else
            getActivity().setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getArabic_name());
        Orphanage orphanage = appDatabase.getOrphanageDao().getOrphanage(MySharedPreference.getLong(getContext(),Constants.keys.ORPHANAGE_ID,0L));
        final String phone = orphanage.getPhone();
        number.setText(phone);
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phone));

                if (ContextCompat.checkSelfPermission(getActivity(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });

            return rootView;

    }


}
