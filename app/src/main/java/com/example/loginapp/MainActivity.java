package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;
    TextView nameText, destText, quantityText;

    public class FirebaseHelper {
        DatabaseReference db;
        ArrayList<ListHelperClass> list1 = new ArrayList<>();
        ListView mListview;
        Context c;

        public FirebaseHelper(DatabaseReference db, Context context, ListView mListview){
            this.db = db;
            this.c = context;
            this.mListview = mListview;
            this.retrieve();
        }
        public ArrayList<ListHelperClass> retrieve() {
            db.child("Events").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list1.clear();
                    if (snapshot.exists() && snapshot.getChildrenCount() > 0){
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            ListHelperClass list12 = ds.getValue(ListHelperClass.class);
                            list1.add(list12);
                        }
                    }
                    adapter = new CustomAdapter(c, list1);
                    mListview.setAdapter(adapter);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            mListview.smoothScrollToPosition(list1.size());
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
                }
            });
            return list1;
        }
    }

    class CustomAdapter extends BaseAdapter{
        Context c;
        ArrayList<ListHelperClass> list1;

        public CustomAdapter(Context c, ArrayList<ListHelperClass> list1){
            this.c = c;
            this.list1 = list1;
        }

        @Override
        public int getCount() {
            return list1.size();
        }

        @Override
        public Object getItem(int i) {
            return list1.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(c).inflate(R.layout.model, viewGroup, false);
            }

            TextView nameTextView = view.findViewById(R.id.nameTextView);
            TextView descTextView = view.findViewById(R.id.descTextView);
            TextView quantityTextView = view.findViewById(R.id.quantityTextView);

            final ListHelperClass s = (ListHelperClass) this.getItem(i);

            nameTextView.setText(s.getName());
            descTextView.setText(s.getDescription());
            quantityTextView.setText(s.getQuantity());



            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.eventList);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db, this, mListView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemInfo = new Intent(MainActivity.this, ItemInfo.class);
                itemInfo.getExtras()
                startActivity(itemInfo);
            }
        });
    }



    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}