package com.example.recycler_view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

        // Click listener for updating the contact
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int currentPosition = holder.getAdapterPosition();

                if (currentPosition != RecyclerView.NO_POSITION) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_layout);

                    TextView txtname = dialog.findViewById(R.id.title_text);
                    EditText editname = dialog.findViewById(R.id.edtname);
                    EditText editnumber = dialog.findViewById(R.id.edtnumber);
                    Button btnupdate = dialog.findViewById(R.id.action_button);

                    btnupdate.setText("Update");
                    txtname.setText("Update Contact");
                    editname.setText(arr.get(currentPosition).name);
                    editnumber.setText(arr.get(currentPosition).number);

                    btnupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String namee = editname.getText().toString();
                            String numberr = editnumber.getText().toString();

                            if (namee.isEmpty()) {
                                Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (numberr.isEmpty()) {
                                Toast.makeText(context, "Enter number", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            arr.set(currentPosition, new Contact_model(namee, numberr));
                            notifyItemChanged(currentPosition);
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

        // Long click listener for deleting the contact
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final int currentPosition = holder.getAdapterPosition();

                if (currentPosition != RecyclerView.NO_POSITION) {
                    new AlertDialog.Builder(context)
                            .setMessage("Are you sure you want to delete this item?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    arr.remove(currentPosition);
                                    notifyItemRemoved(currentPosition);
                                    notifyItemRangeChanged(currentPosition, arr.size());
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtnumber;
        ImageView img;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.textview_name);
            txtnumber = itemView.findViewById(R.id.textview_contact);
            img = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.linearLayoutrow);
        }
    }
}
