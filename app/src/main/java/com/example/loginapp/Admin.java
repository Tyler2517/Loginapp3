package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Admin extends AppCompatActivity {
    EditText eventName, eventDetail, Quantity, price;
    Button eventAdd, viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events");

        eventName = findViewById(R.id.eventName);
        eventDetail = findViewById(R.id.eventDetail);
        Quantity = findViewById(R.id.Quantity);
        eventAdd = findViewById(R.id.eventAdd);
        viewList = findViewById(R.id.viewList);
        price = findViewById(R.id.price);


        eventAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mEventName = eventName.getText().toString().trim();
                final String mDetail = eventDetail.getText().toString().trim();
                final String mQuantity = Quantity.getText().toString();
                final String mPrice = price.getText().toString();
                ListHelperClass list = new ListHelperClass(mEventName, mDetail, mQuantity, mPrice);
                reference.child(mEventName).setValue(list);
            }
        });

        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),List_view.class));
            }
        });

    }
}