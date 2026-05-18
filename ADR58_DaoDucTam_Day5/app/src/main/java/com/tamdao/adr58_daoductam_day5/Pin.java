package com.tamdao.adr58_daoductam_day5;

public class Pin {
    private int imageResId; // ID ảnh trong drawable
    private int height;

    public Pin(int imageResId, int height) {
        this.imageResId = imageResId;
        this.height = height;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getHeight() {
        return height;
    }
}