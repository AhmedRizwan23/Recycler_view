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
    // Context and data source (ArrayList of Contact_model)
    Context context;
    ArrayList<Contact_model> arr;

    // Constructor to initialize the context and data source
    Recycler_contact_adapter(Context context, ArrayList<Contact_model> arr) {
        this.context = context;
        this.arr = arr;
    }

    // Method to inflate the item layout and create the ViewHolder
    @NonNull
    @Override
    public Recycler_contact_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the contact item
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_contact, parent, false);
        // Create a new ViewHolder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Method to bind data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull Recycler_contact_adapter.ViewHolder holder, int position) {
        // Set the image resource for the ImageView
        holder.img.setImageResource(arr.get(position).img);
        // Set the name text for the TextView
        holder.txtname.setText(arr.get(position).name);
        // Set the number text for the TextView
        holder.txtnumber.setText(arr.get(position).number);

        // Set the click listener for the LinearLayout (for updating the contact)
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current position of the item in the adapter
                final int currentPosition = holder.getAdapterPosition();

                // Ensure the position is valid
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // Create and set up a dialog for updating the contact
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_layout);

                    // Get references to the dialog's views
                    TextView txtname = dialog.findViewById(R.id.title_text);
                    EditText editname = dialog.findViewById(R.id.edtname);
                    EditText editnumber = dialog.findViewById(R.id.edtnumber);
                    Button btnupdate = dialog.findViewById(R.id.action_button);

                    // Set the dialog title and button text
                    btnupdate.setText("Update");
                    txtname.setText("Update Contact");

                    // Pre-fill the EditTexts with the current contact information
                    editname.setText(arr.get(currentPosition).name);
                    editnumber.setText(arr.get(currentPosition).number);

                    // Set the click listener for the update button
                    btnupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Get the updated name and number from the EditTexts
                            String namee = editname.getText().toString();
                            String numberr = editnumber.getText().toString();

                            // Validate the name and number fields
                            if (namee.isEmpty()) {
                                Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (numberr.isEmpty()) {
                                Toast.makeText(context, "Enter number", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            // Update the contact in the data source
                            arr.set(currentPosition, new Contact_model(namee, numberr));
                            // Notify the adapter that the item has changed
                            notifyItemChanged(currentPosition);
                            // Dismiss the dialog
                            dialog.dismiss();
                        }
                    });

                    // Show the dialog
                    dialog.show();
                }
            }
        });

        // Set the long click listener for the LinearLayout (for deleting the contact)
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Get the current position of the item in the adapter
                final int currentPosition = holder.getAdapterPosition();

                // Ensure the position is valid
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // Create an AlertDialog to confirm the deletion
                    new AlertDialog.Builder(context)
                            .setMessage("Are you sure you want to delete this item?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Remove the item from the data source
                                    arr.remove(currentPosition);
                                    // Notify the adapter that the item has been removed
                                    notifyItemRemoved(currentPosition);
                                    // Notify that the item range has changed for proper updates
                                    notifyItemRangeChanged(currentPosition, arr.size());
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }

                // Return true to indicate that the long click was handled
                return true;
            }
        });
    }

    // Method to get the total number of items in the data source
    @Override
    public int getItemCount() {
        return arr.size();
    }

    // ViewHolder class to hold references to the views in each item layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtnumber;
        ImageView img;
        LinearLayout linearLayout;

        // Constructor to initialize the views
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.textview_name);
            txtnumber = itemView.findViewById(R.id.textview_contact);
            img = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.linearLayoutrow);
        }
    }
}
