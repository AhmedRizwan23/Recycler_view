package com.example.recycler_view;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact_model> arrcontacts = new ArrayList<>();
    Recycler_contact_adapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton = findViewById(R.id.floating_button);

        floatingActionButton.setOnClickListener(v -> {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog_layout);
            EditText name = dialog.findViewById(R.id.edtname);
            EditText number = dialog.findViewById(R.id.edtnumber);
            Button buttonaction = dialog.findViewById(R.id.action_button);

            buttonaction.setOnClickListener(v2 -> {
                String namee = "";
                String numberr = "";
                if (!name.getText().toString().equals("")) {
                    namee = name.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "enter name", Toast.LENGTH_SHORT).show();
                }
                if (!number.getText().toString().equals("")) {
                    numberr = number.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "enter number", Toast.LENGTH_SHORT).show();
                }
                arrcontacts.add(new Contact_model(namee, numberr));
                adapter.notifyItemInserted(arrcontacts.size() - 1);
                recyclerView.scrollToPosition(arrcontacts.size() - 1);
                dialog.dismiss();
            });
            dialog.show();

        });
        recyclerView = findViewById(R.id.Recyclerid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        arrcontacts.add(new Contact_model(R.drawable.c, "Phil", "3454"));
        arrcontacts.add(new Contact_model(R.drawable.d, "spell", "1223"));
        arrcontacts.add(new Contact_model(R.drawable.e, "Vro", "234"));
        arrcontacts.add(new Contact_model(R.drawable.f, "pop", "99999"));
        arrcontacts.add(new Contact_model(R.drawable.g, "elon", "443"));
        arrcontacts.add(new Contact_model(R.drawable.h, "wick", "3333"));
        arrcontacts.add(new Contact_model(R.drawable.i, "thor", "2222"));
        arrcontacts.add(new Contact_model(R.drawable.c, "Phil", "3454"));
        arrcontacts.add(new Contact_model(R.drawable.d, "spell", "1223"));
        arrcontacts.add(new Contact_model(R.drawable.e, "Vro", "234"));
        arrcontacts.add(new Contact_model(R.drawable.f, "pop", "99999"));

        adapter = new Recycler_contact_adapter(this, arrcontacts);
        recyclerView.setAdapter(adapter);


    }
}