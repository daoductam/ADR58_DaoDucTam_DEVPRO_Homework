package com.tamdao.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCart;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;
    private TextView tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        updateTotalPrice();
    }

    private void initViews() {
        rvCart = findViewById(R.id.rvCart);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
    }

    private void setupRecyclerView() {
        cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem("Bell Pepper Red", "1kg, Price", 4.99, R.drawable.item1, 1));
        cartItemList.add(new CartItem("Egg Chicken Red", "4pcs, Price", 1.99, R.drawable.item2, 1));
        cartItemList.add(new CartItem("Organic Bananas", "12kg, Price", 3.00, R.drawable.item3, 1));
        cartItemList.add(new CartItem("Ginger", "250gm, Price", 2.99, R.drawable.item4, 1));

        cartAdapter = new CartAdapter(cartItemList);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(cartAdapter);
    }

    private void updateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItemList) {
            total += item.getPrice() * item.getCount();
        }
        tvTotalPrice.setText(String.format(Locale.getDefault(), "$%.2f", total));
    }
}