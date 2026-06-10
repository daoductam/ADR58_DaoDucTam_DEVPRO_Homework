package com.rsui.rs_network.network.dto;

import com.google.gson.annotations.SerializedName;
import com.rsui.rs_network.model.Product;
import com.rsui.rs_network.model.User;

import java.util.List;

public class ProductListResponse {
    @SerializedName("user")
    private User user;

    @SerializedName("products")
    private List<Product> products;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
