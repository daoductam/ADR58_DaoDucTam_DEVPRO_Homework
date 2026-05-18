package com.tamdao.adr58_daoductam_day6;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tamdao.adr58_daoductam_day6.adapter.CartAdapter;
import com.tamdao.adr58_daoductam_day6.model.CartItem;
import com.tamdao.adr58_daoductam_day6.utils.CartManager;

import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private CartManager cartManager;
    private CartAdapter adapter;
    private List<CartItem> cartItems;

    private RecyclerView rvCart;
    private View layoutEmptyCart;
    private TextView tvItemCount, tvSubTotal, tvTotal;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartManager = new CartManager(this);
        
        rvCart = findViewById(R.id.rvCart);
        layoutEmptyCart = findViewById(R.id.layoutEmptyCart);
        tvItemCount = findViewById(R.id.tvItemCount);
        tvSubTotal = findViewById(R.id.tvSubTotal);
        tvTotal = findViewById(R.id.tvTotal);
        btnBack = findViewById(R.id.btnBack);

        initView();
    }

    private void initView() {
        cartItems = cartManager.getCartItems();
        
        adapter = new CartAdapter(cartItems, (position, newQuantity) -> {
            if (newQuantity <= 0) {
                cartItems.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, cartItems.size());
            } else {
                cartItems.get(position).setQuantity(newQuantity);
                adapter.notifyItemChanged(position);
            }
            cartManager.saveCartItems(cartItems);
            updateUI();
        });

        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());

        updateUI();
    }

    private void updateUI() {
        if (cartItems.isEmpty()) {
            rvCart.setVisibility(View.GONE);
            layoutEmptyCart.setVisibility(View.VISIBLE);
            tvItemCount.setText("0 Item(s)");
            tvSubTotal.setText("0");
            tvTotal.setText("$0");
        } else {
            rvCart.setVisibility(View.VISIBLE);
            layoutEmptyCart.setVisibility(View.GONE);
            
            int count = 0;
            for (CartItem item : cartItems) count += item.getQuantity();
            
            tvItemCount.setText(count + " Item(s)");
            
            double total = 0;
            for (CartItem item : cartItems) {
                total += item.getCoffee().getPrice() * item.getQuantity();
            }
            
            tvSubTotal.setText(String.format(Locale.US, "%.0f", total));
            tvTotal.setText(String.format(Locale.US, "$%.0f", total));
        }
    }
}