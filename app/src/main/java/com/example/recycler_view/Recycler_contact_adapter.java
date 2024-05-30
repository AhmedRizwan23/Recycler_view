package com.example.recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_contact_adapter extends RecyclerView.Adapter<Recycler_contact_adapter.ViewHolder> {
    Context context;
    ArrayList<Contact_model> arr;

    Recycler_contact_adapter(Context context, ArrayList<Contact_model> arr) {
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public Recycler_contact_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_contact_adapter.ViewHolder holder, int position) {

        holder.img.setImageResource(arr.get(position).img);
        holder.txtname.setText(arr.get(position).name);
        holder.txtnumber.setText(arr.get(position).number);


    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtnumber;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            txtname = itemView.findViewById(R.id.textview_name);
            txtnumber = itemView.findViewById(R.id.textview_contact);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
