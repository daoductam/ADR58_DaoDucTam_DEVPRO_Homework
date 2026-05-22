package com.tamdao.adr58_daoductam_day7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Food> foodList = new ArrayList<>();
    private final OnFoodClickListener listener;

    public interface OnFoodClickListener {
        void onFoodClick(Food food);
    }

    public FoodAdapter(OnFoodClickListener listener) {
        this.listener = listener;
    }

    public void setFoodList(List<Food> foods) {
        this.foodList = foods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.bind(food, listener);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood, ivFavorite;
        TextView tvFoodName, tvTime, tvRating;
        MaterialCardView cvImage;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            ivFavorite = itemView.findViewById(R.id.ivFavorite);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvRating = itemView.findViewById(R.id.tvRating);
            cvImage = itemView.findViewById(R.id.cvImage);
        }

        public void bind(final Food food, final OnFoodClickListener listener) {
            tvFoodName.setText(food.getName());
            tvTime.setText(food.getCookingTime());
            tvRating.setText(String.valueOf(food.getRating()));
            imgFood.setImageResource(food.getImageRes());
            
            // Remove individual background color logic
            cvImage.setCardBackgroundColor(0xFFF5F5F5); // Default light gray

            ivFavorite.setVisibility(food.isFavorite() ? View.VISIBLE : View.GONE);

            itemView.setOnClickListener(v -> listener.onFoodClick(food));
        }
    }
}
