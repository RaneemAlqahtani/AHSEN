package com.example.mylocation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylocation.DatabaseClasses.Dates;

import java.util.List;



public class DateAdapter extends RecyclerView.Adapter<DateAdapter.MyViewHolder>{

    private Context context;
    private List<Dates> datesList;

    public DateAdapter(Context context, List<Dates> datesList) {
        this.context = context;
        this.datesList = datesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date_item, parent, false);

        return new DateAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Dates date = datesList.get(position);
        holder.date.setText(date.getDate());
        holder.time.setText(date.getTime());
    }

    @Override
    public int getItemCount() {
        return datesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, time;


        public MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
            time = view.findViewById(R.id.time);
        }//End method
    }//End Class
}
