package com.tamdao.adr58_daoductam_day6.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tamdao.adr58_daoductam_day6.model.CartItem;
import com.tamdao.adr58_daoductam_day6.model.Coffee;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREF_NAME = "CoffeeCart";
    private static final String KEY_CART = "cart_items";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public CartManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public List<CartItem> getCartItems() {
        String json = sharedPreferences.getString(KEY_CART, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveCartItems(List<CartItem> items) {
        String json = gson.toJson(items);
        sharedPreferences.edit().putString(KEY_CART, json).apply();
    }

    public void addToCart(Coffee coffee, int quantity) {
        List<CartItem> items = getCartItems();
        boolean exists = false;
        for (CartItem item : items) {
            if (item.getCoffee().getId() == coffee.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                if (item.getQuantity() <= 0) {
                    items.remove(item);
                }
                exists = true;
                break;
            }
        }
        if (!exists && quantity > 0) {
            items.add(new CartItem(coffee, quantity));
        }
        saveCartItems(items);
    }

    public int getCartCount() {
        List<CartItem> items = getCartItems();
        int count = 0;
        for (CartItem item : items) {
            count += item.getQuantity();
        }
        return count;
    }

    public double getTotalPrice() {
        List<CartItem> items = getCartItems();
        double total = 0;
        for (CartItem item : items) {
            total += item.getCoffee().getPrice() * item.getQuantity();
        }
        return total;
    }
}