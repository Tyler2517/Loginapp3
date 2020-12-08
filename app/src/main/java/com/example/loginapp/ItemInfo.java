package com.example.loginapp;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ItemInfo extends AppCompatActivity {
    TextView eventName, eventDesc, eventQuant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_details);
        TextView eventName = (TextView) findViewById(R.id.event_Name);
        TextView eventDesc = (TextView) findViewById(R.id.event_Desc);
        TextView eventQuant = (TextView) findViewById(R.id.event_Quantity);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        String currenteName = getIntent().getStringExtra("Name");
        String currenteDesc = getIntent().getStringExtra("Description");
        String currenteQuant = getIntent().getStringExtra("Quantity");

        eventName.setText(currenteName);
        eventDesc.setText(currenteDesc);
        eventQuant.setText(currenteQuant);


    }
}
