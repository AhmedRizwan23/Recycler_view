package com.example.recycler_view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact_model> arrcontacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton = findViewById(R.id.floating_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("floating button tapped");

            }
        });
        RecyclerView recyclerView = findViewById(R.id.Recyclerid);
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

        Recycler_contact_adapter adapter = new Recycler_contact_adapter(this, arrcontacts);
        recyclerView.setAdapter(adapter);


    }
}