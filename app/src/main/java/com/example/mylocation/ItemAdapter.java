package com.example.mylocation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylocation.DatabaseClasses.Orphanage;

import java.util.List;



public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{
    private Context context;
    private List<Orphanage> orphanageList;
    String language;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, location;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            location = view.findViewById(R.id.location);
        }//End method
    }//End Class


    public ItemAdapter(Context context, List<Orphanage> orphanagesList) {
        this.context = context;
        this.orphanageList = orphanagesList;
        language = MySharedPreference.getString(context,Constants.keys.APP_LANGUAGE,"");
    }//End method

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }//End method

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Orphanage orphanage = orphanageList.get(position);
        if(language.equals("ar")){
            holder.name.setText(orphanage.getArabic_name());
            holder.location.setText(orphanage.getArabic_location());
        } else{
            holder.name.setText(orphanage.getName());
            holder.location.setText(orphanage.getLocation());
        }

    }//End method

    @Override
    public int getItemCount() {
        return orphanageList.size();
    }//End method
}//End Class
