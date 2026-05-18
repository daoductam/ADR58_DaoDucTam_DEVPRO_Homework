package com.tamdao.adr58_daoductam_day6.model;

import java.io.Serializable;

public class Coffee implements Serializable {
    private int id;
    private String name;
    private double price;
    private int imageResId;
    private float rating;

    public Coffee(int id, String name, double price, int imageResId, float rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public float getRating() { return rating; }
}