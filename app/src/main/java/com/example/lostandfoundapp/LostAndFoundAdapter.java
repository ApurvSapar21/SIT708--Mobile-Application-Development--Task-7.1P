package com.example.lostandfoundapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LostAndFoundAdapter extends RecyclerView.Adapter<LostAndFoundAdapter.ViewHolder> {

    private Context context;
    private List<LostAndFoundItem> itemList;
    private OnItemClickListener listener;

    public LostAndFoundAdapter(Context context, List<LostAndFoundItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lost_and_found, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LostAndFoundItem item = itemList.get(position);

        holder.itemNameTextView.setText("Name: " + item.getName());
        holder.itemPhoneTextView.setText("Phone: " + item.getPhone());
        holder.itemDescriptionTextView.setText("Description: " + item.getDescription());
        holder.itemDateTextView.setText("Date: " + item.getDate());
        holder.itemLocationTextView.setText("Location: " + item.getLocation());

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTextView;
        TextView itemPhoneTextView;
        TextView itemDescriptionTextView;
        TextView itemDateTextView;
        TextView itemLocationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemPhoneTextView = itemView.findViewById(R.id.itemPhoneTextView);
            itemDescriptionTextView = itemView.findViewById(R.id.itemDescriptionTextView);
            itemDateTextView = itemView.findViewById(R.id.itemDateTextView);
            itemLocationTextView = itemView.findViewById(R.id.itemLocationTextView);
        }
    }
}
