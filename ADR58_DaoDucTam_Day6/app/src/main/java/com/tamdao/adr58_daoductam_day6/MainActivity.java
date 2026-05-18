package com.tamdao.adr58_daoductam_day6;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tamdao.adr58_daoductam_day6.adapter.CoffeeAdapter;
import com.tamdao.adr58_daoductam_day6.model.CartItem;
import com.tamdao.adr58_daoductam_day6.model.Coffee;
import com.tamdao.adr58_daoductam_day6.utils.CartManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Coffee> coffeeList;
    private List<Coffee> filteredList;
    private CoffeeAdapter adapter;
    private CartManager cartManager;
    
    private RecyclerView rvCoffee;
    private View btnGoToCart;
    private TextView tvCartBadge;
    private EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartManager = new CartManager(this);
        
        rvCoffee = findViewById(R.id.rvCoffee);
        btnGoToCart = findViewById(R.id.btnGoToCart);
        tvCartBadge = findViewById(R.id.tvCartBadge);
        edtSearch = findViewById(R.id.edtSearch);

        initData();
        initView();
    }

    private void initData() {
        coffeeList = new ArrayList<>();
        coffeeList.add(new Coffee(1, "Mixed Black Coffee", 12.0, R.drawable.espresso, 4.5f));
        coffeeList.add(new Coffee(2, "Mixed Black Coffee", 12.0, R.drawable.caramel, 4.2f));
        coffeeList.add(new Coffee(3, "Mixed Black Coffee", 12.0, R.drawable.ice_coffee, 4.8f));
        coffeeList.add(new Coffee(4, "Hot Chocolate", 12.0, R.drawable.hot_chocolate, 4.5f));
        
        filteredList = new ArrayList<>(coffeeList);
    }

    private void initView() {
        adapter = new CoffeeAdapter(filteredList, new CoffeeAdapter.OnCoffeeClickListener() {
            @Override
            public void onUpdateCart(Coffee coffee, int quantity) {
                cartManager.addToCart(coffee, quantity);
                updateCartBadge();
            }

            @Override
            public int getQuantity(Coffee coffee) {
                for (CartItem item : cartManager.getCartItems()) {
                    if (item.getCoffee().getId() == coffee.getId()) {
                        return item.getQuantity();
                    }
                }
                return 0;
            }
        });

        rvCoffee.setLayoutManager(new GridLayoutManager(this, 2));
        rvCoffee.setAdapter(adapter);

        btnGoToCart.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        updateCartBadge();
    }

    private void filter(String text) {
        filteredList.clear();
        for (Coffee item : coffeeList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void updateCartBadge() {
        int count = cartManager.getCartCount();
        tvCartBadge.setText(String.valueOf(count));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartBadge();
        adapter.notifyDataSetChanged();
    }
}