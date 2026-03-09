package com.example.btl.models;

public class CartModel {
    int image;
    String name;
    String price;
    String rating;
    int quantity;

    // Required for JSON (Gson) parsing / reflection
    public CartModel() {
        this.quantity = 1;
    }

    public CartModel(int image, String name, String price, String rating) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.quantity = 1;
    }

    public CartModel(int image, String name, String price, String rating, int quantity) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.quantity = quantity <= 0 ? 1 : quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getQuantity() {
        return quantity <= 0 ? 1 : quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity <= 0 ? 1 : quantity;
    }
}


