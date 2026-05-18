package com.tamdao.adr58_daoductam_day6.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Coffee coffee;
    private int quantity;

    public CartItem(Coffee coffee, int quantity) {
        this.coffee = coffee;
        this.quantity = quantity;
    }

    public Coffee getCoffee() { return coffee; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}