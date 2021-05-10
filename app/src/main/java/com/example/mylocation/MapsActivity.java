package com.example.mylocation;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mylocation.DatabaseClasses.CreditCard;
import com.example.mylocation.DatabaseClasses.Dates;
import com.example.mylocation.DatabaseClasses.Orphanage;
import com.example.mylocation.Model.AppDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends BaseActivity implements AdapterView.OnItemSelectedListener,OnMapReadyCallback {

    private List<Orphanage> orphanagesList;
    private RecyclerView recyclerView;
    private Spinner spinner;
    private ItemAdapter adapter, adapter2;
    private AppDatabase db;
    private String arabic_names [] = {"دار الحضانة الاجتماعية", "دار التربية الاجتماعية للبنات", "مركز الامير سلطان للتربية الاجتماعية", "دار الحضان فلل الربوه","دار التربية الاجتماعية للبنين","دار التربية الاجتماعية والمؤسسة النموذجية "};
    private String names[] = {"Orphanage 1", "House Social Education for Girls", "Sultan", "Orphanage 4", "House social education for boys", "House social education for boys door"};
    private String location[] = {"Riyadh", "Al-Ahsa", "Dammam", "Riyadh","Riyadh","Abha"};
    private double latitudes [] = {24.642416,25.389861,26.383472,24.707456,24.664517,18.244802};
    private double longitude [] = {46.690034,49.600867,50.114932,46.739736,46.698334,42.496307};
    private String aboutUs [];
    private String aboutUs_arabic[];
    private String location_arabic[] = {"الرياض", "الأحساء", "الدمام", "الرياض","الرياض","ابها"};
    private String phone [] = {"0114421532", "0135824942", "0138611593", "XXXXXXXXXX", "0538774629", "0172308801"};
    private String Links [] = {"https://www.google.com/maps/place/%D8%AF%D8%A7%D8%B1+%D8%A7%D9%84%D8%AD%D8%B6%D8%A7%D9%86%D8%A9+%D8%A7%D9%84%D8%A7%D8%AC%D8%AA%D9%85%D8%A7%D8%B9%D9%8A%D8%A9+%D8%A8%D8%A7%D9%84%D8%B1%D9%8A%D8%A7%D8%B6+%D8%B9%D9%84%D9%8A%D8%B4%D8%A9%E2%80%AD/@24.6380428,46.6903104,15z/data=!4m5!3m4!1s0x0:0x8bb030fe125cb028!8m2!3d24.6380428!4d46.6903104",
                               "https://www.google.com/maps/place/Dar+Social+Education+for+Girls+in+Al-Ahsa/@25.3896236,49.6009096,15z/data=!4m2!3m1!1s0x0:0xf191e347d6ff6403?sa=X&ved=2ahUKEwii97aolevjAhUEz4UKHcBPCbIQ_BIwE3oECA4QCA",
                                "https://www.google.com/maps/place/%D9%85%D8%B1%D9%83%D8%B2+%D8%A7%D9%84%D8%A7%D9%85%D9%8A%D8%B1+%D8%B3%D9%84%D8%B7%D8%A7%D9%86+%D9%84%D9%84%D8%AA%D8%B1%D8%A8%D9%8A%D8%A9+%D8%A7%D9%84%D8%A7%D8%AC%D8%AA%D9%85%D8%A7%D8%B9%D9%8A%D8%A9+%D8%A8%D8%A7%D9%84%D8%AF%D9%85%D8%A7%D9%85%E2%80%AD/@26.3812597,50.1150232,15z/data=!4m2!3m1!1s0x0:0xe13b45b12d9a7c76?sa=X&ved=2ahUKEwie1L-5levjAhWxxoUKHek9DdQQ_BIwDXoECAoQBg",
                                "https://www.google.com/search?q=%D8%AF%D8%A7%D8%B1%20%D8%A7%D9%84%D8%AD%D8%B6%D8%A7%D9%86%D8%A9%20%D9%81%D9%84%D9%84%20%D8%A7%D9%84%D8%B1%D8%A8%D9%88%D9%87&rlz=1C1GCEU_enSA854SA854&oq=%D8%AF%D8%A7%D8%B1+%D8%A7%D9%84%D8%AD%D8%B6%D8%A7%D9%86+%D9%81%D9%84%D9%84+%D8%A7%D9%84%D8%B1%D8%A8%D9%88%D9%87&aqs=chrome..69i57j0.479j0j4&sourceid=chrome&ie=UTF-8&safe=strict&npsic=0&rflfq=1&rlha=0&rllag=24667474,46715186,4123&tbm=lcl&rldimm=10747106181161622357&ved=2ahUKEwjxwObLlevjAhUDxhoKHbtfCHIQvS4wAXoECAoQGg&rldoc=1&tbs=lrf:!2m1!1e2!3sIAE,lf:1,lf_ui:2#rlfi=hd:;si:10747106181161622357;mv:!1m2!1d24.700436999999997!2d46.7430482!2m2!1d24.634511!2d46.687325200000004!3m12!1m3!1d31951.730941443562!2d46.715186700000004!3d24.667474!2m3!1f0!2f0!3f0!3m2!1i325!2i423!4f13.1;tbs:lrf:!2m1!1e2!3sIAE,lf:1,lf_ui:2",
                                "https://www.google.com/maps/place/House+social+education+for+boys+in+Riyadh/@24.6642387,46.6983452,15z/data=!4m5!3m4!1s0x0:0x41aeb611a7513888!8m2!3d24.6642387!4d46.6983452",
                                "https://www.google.com/maps/place/House+social+education+for+boys+door/@18.2444528,42.4963322,15z/data=!4m5!3m4!1s0x0:0x843dc811c69b4156!8m2!3d18.2444528!4d42.4963322"};
    private GoogleMap mMap;
    private Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        recyclerView = findViewById(R.id.recycler_view);
        db = AppDatabase.getAppDatabase(this);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        addItemsOnSpinner();
        prepareData();
        mTopToolbar = findViewById(R.id.toolbar66);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Orphanage orphanage = orphanagesList.get(position);
                Intent intent = new Intent(MapsActivity.this, MA.class);
                MySharedPreference.putLong(MapsActivity.this, Constants.keys.ORPHANAGE_ID,orphanage.getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void prepareData(){
        Orphanage orphanage;
        if(!MySharedPreference.getBoolean(this,Constants.keys.ADD_DATABASE,false)){
            for (int i = 0; i < arabic_names.length; i++) {
                MySharedPreference.putBoolean(this, Constants.keys.ADD_DATABASE, true);
                orphanage = new Orphanage();
                orphanage.setLocation(location[i]);
                orphanage.setName(names[i]);
                orphanage.setArabic_name(arabic_names[i]);
                orphanage.setPhone(phone[i]);
                orphanage.setArabic_location(location_arabic[i]);
                orphanage.setLink(Links[i]);
                //   orphanage.setAboutUS(aboutUs[i]);
                //  orphanage.setAboutUS_arabic(aboutUs_arabic[i]);
                orphanage.setLatitude(latitudes[i]);
                orphanage.setLongitude(longitude[i]);
                long id = db.getOrphanageDao().insert(orphanage);
                prepareCreditCardData(id);
                preparedatesData(id);
            }
        }
    }

    private void prepareCreditCardData(long id){
        String [] bankNameAr = {"الاهلي","الراجحي","سامبا"};
        String [] bankNameEn = {"Al-Ahli","Al-Rajhi","Samba"};
        for (int i = 0; i < 3; i++) {
            CreditCard creditCard = new CreditCard();
            creditCard.setArabic_bank(bankNameAr[i]);
            creditCard.setBank(bankNameEn[i]);
            creditCard.setOrphanage_id(id);
            creditCard.setNumber("0000000000");
            db.getCreditcardDao().insert(creditCard);

        }
    }

    private void preparedatesData(long id){
        String [] date = {"Feb 20","Mar 5","Apr 15","Jun 18","Sep 7"};
        String [] dateArabic = {"فبراير 20","مارس 5","ابريل 15","يونيو 18","سبتيمبر 7"};
        String [] time = {"8:20 AM","10:00 AM","1:00 PM","3:14 PM"};
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 4;j++){
                Dates d = new Dates();
                d.setDate(date[i]);
                d.setDateArabic(dateArabic[i]);
                d.setTime(time[j]);
                d.setOrphanage_id(id);
                db.getDatesDao().insert(d);

            }
        }
    }

    public void addItemsOnSpinner() {
        boolean flag=true;
        List<String> list = new ArrayList<String>();
        list.add(getString(R.string.allcities));
        for(int i = 0; i< location.length;i++){
            for(int j=0;j<list.size();j++){
                if(list.get(j).matches(location[i]) || list.get(j).matches(location_arabic[i]))
                    flag=false;
            }
            if(flag) {
                if (MySharedPreference.getString(MapsActivity.this, Constants.keys.APP_LANGUAGE, "en").matches("en"))
                    list.add(location[i]);
                else
                    list.add(location_arabic[i]);
            }
            flag=true;
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner, list);
        spinner.setAdapter(dataAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(!adapterView.getItemAtPosition(i).toString().matches(getString(R.string.allcities))) {
            orphanagesList = new ArrayList<>();
            if(MySharedPreference.getString(this, Constants.keys.APP_LANGUAGE, "en").matches("en"))
                orphanagesList.addAll(db.getOrphanageDao().filterOrphanage(adapterView.getItemAtPosition(i).toString()));
            else
                orphanagesList.addAll(db.getOrphanageDao().filterArabicOrphanage(adapterView.getItemAtPosition(i).toString()));
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MapsActivity.this, 1);
            recyclerView.setLayoutManager(layoutManager);
            adapter2 = new ItemAdapter(MapsActivity.this, orphanagesList);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter2);
            changeMap(orphanagesList);
        }
        else {
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(layoutManager);
            orphanagesList = new ArrayList<>();
            orphanagesList.addAll(db.getOrphanageDao().getAllOrphanages());
            adapter = new ItemAdapter(this, orphanagesList);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            changeMap(orphanagesList);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for(int i=0;i<6;i++) {
            LatLng riyadh = new LatLng(latitudes[i], longitude[i]);
            mMap.addMarker(new MarkerOptions().position(riyadh).title(arabic_names[i]));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(riyadh, 10F));
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.LanguageOption:
                Intent i = new Intent(this, Language.class);
                startActivity(i);
                return true;
            case R.id.UpdateInfoOption:
                Intent j = new Intent(this, NationalIDUpdate.class);
                startActivity(j);
                return true;
            case android.R.id.home:
                Intent intent = new Intent(this, Stories.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void changeMap(List<Orphanage> orp){
        mMap.clear();
        for(int i=0;i<orp.size();i++) {
            Orphanage o = orp.get(i);
            LatLng riyadh = new LatLng(o.getLatitude(), o.getLongitude());
            mMap.addMarker(new MarkerOptions().position(riyadh).title(o.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(riyadh, 10F));
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
