package com.tamdao.adr58_daoductam_day7;

import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private String name;
    private String description;
    private String cookingTime;
    private String difficulty;
    private String servings;
    private double rating;
    private int imageRes;
    private boolean isFavorite;

    public Food(int id, String name, String description, String cookingTime, String difficulty, String servings, double rating, int imageRes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
        this.servings = servings;
        this.rating = rating;
        this.imageRes = imageRes;
        this.isFavorite = false;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCookingTime() { return cookingTime; }
    public String getDifficulty() { return difficulty; }
    public String getServings() { return servings; }
    public double getRating() { return rating; }
    public int getImageRes() { return imageRes; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}
