package com.example.btl.models;

public class DetailedDailyModel {

    int image;
    String name;
    String description;
    String rating;
    String price;
    String timing;

    public DetailedDailyModel(int image, String name, String description,
                              String rating, String price, String timing) {

        this.image = image;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.timing = timing;
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

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }

    public String getTiming() {
        return timing;
    }
}