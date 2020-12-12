package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Cart_Items {
    String cname, quantity, price;

    public Cart_Items(){}

    public Cart_Items(String cname, String quantity, String price) {
        this.cname = cname;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return cname;
    }

    public void setName(String cname) {
        this.cname = cname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}