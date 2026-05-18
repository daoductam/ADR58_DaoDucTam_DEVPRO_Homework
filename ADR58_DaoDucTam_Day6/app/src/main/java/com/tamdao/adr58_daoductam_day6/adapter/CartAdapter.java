package com.tamdao.adr58_daoductam_day6.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tamdao.adr58_daoductam_day6.R;
import com.tamdao.adr58_daoductam_day6.model.CartItem;

import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private OnCartChangeListener listener;

    public interface OnCartChangeListener {
        void onQuantityChanged(int position, int newQuantity);
    }

    public CartAdapter(List<CartItem> cartItems, OnCartChangeListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.tvCartItemName.setText(item.getCoffee().getName());
        holder.tvCartItemPrice.setText(String.format(Locale.US, "$%.0f", item.getCoffee().getPrice()));
        holder.tvCartQuantity.setText(String.valueOf(item.getQuantity()));
        holder.imgCartItem.setImageResource(item.getCoffee().getImageResId());
        holder.tvCartItemRating.setText(String.valueOf(item.getCoffee().getRating()));

        holder.btnCartPlus.setOnClickListener(v -> {
            listener.onQuantityChanged(position, item.getQuantity() + 1);
        });

        holder.btnCartMinus.setOnClickListener(v -> {
            listener.onQuantityChanged(position, item.getQuantity() - 1);
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCartItem;
        TextView tvCartItemName, tvCartItemPrice, tvCartQuantity, btnCartMinus, btnCartPlus, tvCartItemRating;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCartItem = itemView.findViewById(R.id.imgCartItem);
            tvCartItemName = itemView.findViewById(R.id.tvCartItemName);
            tvCartItemPrice = itemView.findViewById(R.id.tvCartItemPrice);
            tvCartQuantity = itemView.findViewById(R.id.tvCartQuantity);
            btnCartMinus = itemView.findViewById(R.id.btnCartMinus);
            btnCartPlus = itemView.findViewById(R.id.btnCartPlus);
            tvCartItemRating = itemView.findViewById(R.id.tvCartItemRating);
        }
    }
}