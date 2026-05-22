package com.tamdao.adr58_daoductam_day7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.card.MaterialCardView;

public class FragmentDetail extends Fragment {

    private FoodViewModel viewModel;
    private ImageView imgDetail, ivFavoriteIcon;
    private MaterialCardView btnFavorite, btnBack;
    private TextView tvDetailName, tvDetailDesc, tvDetailTime, tvDetailDiff, tvDetailServings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        viewModel = new ViewModelProvider(requireActivity()).get(FoodViewModel.class);

        viewModel.getSelectedFood().observe(getViewLifecycleOwner(), food -> {
            if (food != null) {
                displayFood(food);
            }
        });

        btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
        
        btnFavorite.setOnClickListener(v -> {
            Food currentFood = viewModel.getSelectedFood().getValue();
            if (currentFood != null) {
                viewModel.toggleFavorite(currentFood.getId());
            }
        });
    }

    private void initViews(View view) {
        imgDetail = view.findViewById(R.id.imgDetail);
        btnFavorite = view.findViewById(R.id.btnFavorite);
        ivFavoriteIcon = view.findViewById(R.id.ivFavoriteIcon);
        btnBack = view.findViewById(R.id.btnBack);
        tvDetailName = view.findViewById(R.id.tvDetailName);
        tvDetailDesc = view.findViewById(R.id.tvDetailDesc);
        tvDetailTime = view.findViewById(R.id.tvDetailTime);
        tvDetailDiff = view.findViewById(R.id.tvDetailDiff);
        tvDetailServings = view.findViewById(R.id.tvDetailServings);
    }

    private void displayFood(Food food) {
        imgDetail.setImageResource(food.getImageRes());
        tvDetailName.setText(food.getName());
        tvDetailDesc.setText(food.getDescription());
        tvDetailTime.setText(food.getCookingTime());
        tvDetailDiff.setText(food.getDifficulty());
        tvDetailServings.setText(food.getServings());
        
        if (food.isFavorite()) {
            ivFavoriteIcon.setImageResource(R.drawable.ic_heart_filled);
        } else {
            ivFavoriteIcon.setImageResource(R.drawable.ic_heart);
        }
    }
}
