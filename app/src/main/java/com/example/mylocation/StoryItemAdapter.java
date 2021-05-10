package com.example.mylocation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryItemAdapter extends RecyclerView.Adapter<StoryItemAdapter.MyViewHolder>{
    private Context context;
    private List<StoryItem> storiesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView story;
        public ImageView img;
        public TextView name;


        public MyViewHolder(View view) {
            super(view);
            story = view.findViewById(R.id.story);
            img = view.findViewById(R.id.person_pic);
            name = view.findViewById(R.id.orphan_name);
        }//End method
    }//End Class


    public StoryItemAdapter(Context context, List<StoryItem> storiesList) {
        this.context = context;
        this.storiesList = storiesList;
    }//End method

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }//End method

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StoryItem storyItem = storiesList.get(position);
        holder.story.setText(storyItem.getStory());
        holder.img.setImageResource(storyItem.getIm());
        holder.name.setText(storyItem.getName());
    }//End method

    @Override
    public int getItemCount() {
        return storiesList.size();
    }//End method
}//End Class
