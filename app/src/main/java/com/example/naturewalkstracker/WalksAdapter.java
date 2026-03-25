package com.example.naturewalkstracker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WalksAdapter extends RecyclerView.Adapter<WalksAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Walk> walks;

    private int selectedCount = 0;

    public WalksAdapter(ArrayList<Walk> walks, Context context) {
        this.walks = walks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_item_walk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Walk walk = walks.get(position);

        holder.imageView.setImageResource(walk.imageResId);
        holder.location.setText(walk.location);
        holder.title.setText(walk.title);

        if (walk.isSelected) {
            holder.selected.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.GREEN);
        } else {
            holder.selected.setVisibility(View.GONE);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(v -> {
            if (!walk.isSelected) {
                if (selectedCount < 2) {
                    walk.isSelected = true;
                    selectedCount++;
                    Toast.makeText(context, "Walk selected (" + selectedCount + "/2)", Toast.LENGTH_SHORT).show();
                    notifyItemChanged(position);
                } else {
                    Toast.makeText(context, "Maximum 2 walks per week", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if(walk.isSelected){
                walk.isSelected = false;
                selectedCount--;
                Toast.makeText(context, "Walk deselected", Toast.LENGTH_SHORT).show();
                notifyItemChanged(position);
            }
            return true;
        });


    }

    @Override
    public int getItemCount() {
        return walks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        TextView location;

        TextView selected;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            title = itemView.findViewById(R.id.textView3);
            location = itemView.findViewById(R.id.textView4);
            selected = itemView.findViewById(R.id.selectionText);
        }
    }

}
