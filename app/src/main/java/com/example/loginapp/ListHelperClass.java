package com.example.loginapp;

public class ListHelperClass {
    String name, description, quantity;

    public ListHelperClass(){}

    public ListHelperClass(String name, String description, String quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String toString(){
        return this.name + "." + description + "-" + quantity;
    }
}
