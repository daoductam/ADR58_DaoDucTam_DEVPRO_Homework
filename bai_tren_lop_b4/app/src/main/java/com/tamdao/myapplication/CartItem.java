package com.tamdao.myapplication;

public class CartItem {
    private String name;
    private String quantityInfo;
    private double price;
    private int imageRes;
    private int count;

    public CartItem(String name, String quantityInfo, double price, int imageRes, int count) {
        this.name = name;
        this.quantityInfo = quantityInfo;
        this.price = price;
        this.imageRes = imageRes;
        this.count = count;
    }

    public String getName() { return name; }
    public String getQuantityInfo() { return quantityInfo; }
    public double getPrice() { return price; }
    public int getImageRes() { return imageRes; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}