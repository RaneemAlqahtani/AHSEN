package com.example.mylocation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylocation.DatabaseClasses.CreditCard;

import java.util.List;


public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHolder> {

    List<CreditCard> creditCards;
    Context context;
    String language;


    public AccountAdapter(List<CreditCard> creditCards, Context context) {
        this.creditCards = creditCards;
        this.context = context;
        language = MySharedPreference.getString(context,Constants.keys.APP_LANGUAGE,"");

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_item, parent, false);

        return new AccountAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CreditCard creditCard = creditCards.get(position);
        if(language.equals("ar")){
            holder.name.setText(creditCard.getArabic_bank());
            holder.number.setText(creditCard.getNumber());
        } else{
            holder.name.setText(creditCard.getBank());
            holder.number.setText(creditCard.getNumber());
        }
    }

    @Override
    public int getItemCount() {
        return creditCards.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, number;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.Name_orphanageAcc);
            number = view.findViewById(R.id.Number_orphanageAcc);
        }//End method
    }//End Class
}
