package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    private  void isUser() {
        final String userEnteredEmail = mEmail.getText().toString().trim();
        final String userEnteredPass = mPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();


        Query checkUser = reference.child(user).orderByChild("password").equalTo(userEnteredPass);


        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Toast.makeText(Login.this, "asdfasdfasdfasdf", Toast.LENGTH_SHORT).show();
                    String passwordFromDB = snapshot.child(fAuth.getUid()).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPass)){

                    }
                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);



        mLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    mEmail.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    mPassword.requestFocus();
                    return;
                }

                if(password.length() < 6) {
                    mPassword.setError("Password Must Be >= 6 Characters");
                    mPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            if (user.equals("rOl2D9BuTlT4PV7OgCETceRPWTg2")){
                                startActivity(new Intent(getApplicationContext(),Admin.class));


                            }
                            else{
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                            }

                        } else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }



}