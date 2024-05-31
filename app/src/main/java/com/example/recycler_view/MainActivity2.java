package com.example.recycler_view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("this is title");
        getSupportActionBar().setSubtitle("this is subtitle");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //this enable backbutton on appbar


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();

        if (itemid == R.id.item1) {
            Toast.makeText(this, "item1 clicked", Toast.LENGTH_SHORT).show();
        } else if (itemid == R.id.item2) {
            Toast.makeText(this, "item2 clicked", Toast.LENGTH_SHORT).show();
        } else if (itemid == R.id.item3) {
            Toast.makeText(this, "item3 clicked", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "homeclicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }
}

