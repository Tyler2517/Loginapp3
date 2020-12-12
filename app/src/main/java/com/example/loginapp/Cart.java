package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    ListView mListView;
    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;
    public class FirebaseHelper {
        DatabaseReference db;
        ArrayList<Cart_Items> list2 = new ArrayList<>();
        ListView mListview;
        Context c;

        public FirebaseHelper(DatabaseReference db, Context context, ListView mListview){
            this.db = db;
            this.c = context;
            this.mListview = mListview;
            this.retrieve();
        }
        public ArrayList<Cart_Items> retrieve() {
            db.child("Cart").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list2.clear();
                    if (snapshot.exists() && snapshot.getChildrenCount() > 0){
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            Cart_Items list12 = ds.getValue(Cart_Items.class);
                            list2.add(list12);
                        }
                    }
                    adapter = new CustomAdapter(c, list2);
                    mListview.setAdapter(adapter);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            mListview.smoothScrollToPosition(list2.size());
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
                }
            });
            return list2;
        }
    }

    class CustomAdapter extends BaseAdapter{
        Context c;
        ArrayList<Cart_Items> list1;

        public CustomAdapter(Context c, ArrayList<Cart_Items> list1){
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
                view = LayoutInflater.from(c).inflate(R.layout.cart_list, viewGroup, false);
            }

            TextView nameTextView = view.findViewById(R.id.nameTextView);
            TextView quantityTextView = view.findViewById(R.id.quantityTextView);
            TextView priceTextView = view.findViewById(R.id.priceTextView);

            final Cart_Items s = (Cart_Items) this.getItem(i);

            nameTextView.setText(s.getName());
            quantityTextView.setText(s.getQuantity());
            priceTextView.setText(s.getPrice());





            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView mListView = findViewById(R.id.current_List);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db, this, mListView);





    }
}