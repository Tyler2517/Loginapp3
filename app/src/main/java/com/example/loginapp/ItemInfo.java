package com.example.loginapp;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemInfo extends AppCompatActivity {
    TextView eventName, eventDesc, eventQuant, eventPrice;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_details);
        TextView eventName = (TextView) findViewById(R.id.event_Name);
        TextView eventDesc = (TextView) findViewById(R.id.event_Desc);
        TextView eventQuant = (TextView) findViewById(R.id.event_Quantity);
        TextView eventPrice = (TextView) findViewById(R.id.event_Price);
        addBtn = findViewById(R.id.addBtn);
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Cart");
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final String currenteName = getIntent().getStringExtra("Name");
        final String currenteDesc = getIntent().getStringExtra("Description");
        final String currenteQuant = getIntent().getStringExtra("Quantity");
        final String currentPrice = getIntent().getStringExtra("Price");

        eventName.setText(currenteName);
        eventDesc.setText(currenteDesc);
        eventQuant.setText(currenteQuant);
        eventPrice.setText(currentPrice);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCart = new Intent(ItemInfo.this, Cart.class);
                toCart.putExtra("CName", currenteName);
                toCart.putExtra("CDescription", currenteDesc);
                toCart.putExtra("CQuantity", currenteQuant);
                toCart.putExtra("CPrice", currentPrice);
                Toast.makeText(ItemInfo.this, "Added to Cart!", Toast.LENGTH_LONG).show();
                Cart_Items cart_items = new Cart_Items(currenteName, currenteQuant, currentPrice);
                reference.child(currenteName).setValue(cart_items);
                finish();
            }
        });
    }
}
