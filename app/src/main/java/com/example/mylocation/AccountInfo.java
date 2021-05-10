package com.example.mylocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylocation.DatabaseClasses.CreditCard;
import com.example.mylocation.Model.AppDatabase;

import java.util.ArrayList;
import java.util.List;


public class AccountInfo  extends Fragment {

    View view;

    AccountAdapter adapter;
    List<CreditCard> creditCards;
    AppDatabase appDatabase;
    RecyclerView rc;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.ophanage_account, container, false);
        creditCards = new ArrayList<>();
        rc = view.findViewById(R.id.account_recycler);
        appDatabase = AppDatabase.getAppDatabase(getContext());
        long orph_id = MySharedPreference.getLong(getContext(), Constants.keys.ORPHANAGE_ID, 0l);
        if(MySharedPreference.getString(getContext(), Constants.keys.APP_LANGUAGE, "en").matches("en"))
           getActivity().setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getName());
        else
            getActivity().setTitle(appDatabase.getOrphanageDao().getOrphanage(orph_id).getArabic_name());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rc.setLayoutManager(layoutManager);
        creditCards = new ArrayList<>();
        creditCards.addAll(appDatabase.getCreditcardDao().getAllCreditCards(MySharedPreference.getLong(getContext(), Constants.keys.ORPHANAGE_ID, 0l)));
        adapter = new AccountAdapter(creditCards, getContext());
        rc.setItemAnimator(new DefaultItemAnimator());
        rc.setAdapter(adapter);



        return view;

    }


}
