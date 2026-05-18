package com.tamdao.adr58_daoductam_day6.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tamdao.adr58_daoductam_day6.R;
import com.tamdao.adr58_daoductam_day6.model.Coffee;

import java.util.List;
import java.util.Locale;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private List<Coffee> coffeeList;
    private OnCoffeeClickListener listener;

    public interface OnCoffeeClickListener {
        void onUpdateCart(Coffee coffee, int quantity);
        int getQuantity(Coffee coffee);
    }

    public CoffeeAdapter(List<Coffee> coffeeList, OnCoffeeClickListener listener) {
        this.coffeeList = coffeeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coffee, parent, false);
        return new CoffeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        Coffee coffee = coffeeList.get(position);
        holder.tvCoffeeName.setText(coffee.getName());
        holder.tvCoffeePrice.setText(String.format(Locale.US, "$%.2f", coffee.getPrice()));
        holder.imgCoffee.setImageResource(coffee.getImageResId());
        
        int currentQty = listener.getQuantity(coffee);
        holder.tvQuantity.setText(String.valueOf(currentQty));

        holder.btnPlus.setOnClickListener(v -> {
            listener.onUpdateCart(coffee, 1);
            notifyItemChanged(position);
        });

        holder.btnMinus.setOnClickListener(v -> {
            if (listener.getQuantity(coffee) > 0) {
                listener.onUpdateCart(coffee, -1);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCoffee;
        TextView tvCoffeeName, tvCoffeePrice, tvQuantity, btnMinus, btnPlus;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCoffee = itemView.findViewById(R.id.imgCoffee);
            tvCoffeeName = itemView.findViewById(R.id.tvCoffeeName);
            tvCoffeePrice = itemView.findViewById(R.id.tvCoffeePrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
        }
    }
}