package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView managButton, userButton;
    ListView editList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managButton = findViewById(R.id.managButton);
        userButton = findViewById(R.id.userButton);

        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (user.equals("rOl2D9BuTlT4PV7OgCETceRPWTg2")){
            managButton.setVisibility(View.VISIBLE);
            userButton.setVisibility(View.INVISIBLE);

        }
        else{
            managButton.setVisibility(View.INVISIBLE);
            userButton.setVisibility(View.VISIBLE);

        }

    }



    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}