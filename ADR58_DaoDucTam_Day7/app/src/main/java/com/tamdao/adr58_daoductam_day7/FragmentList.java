package com.tamdao.adr58_daoductam_day7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentList extends Fragment {

    private FoodViewModel viewModel;
    private FoodAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(FoodViewModel.class);

        RecyclerView rvFood = view.findViewById(R.id.rvFood);
        rvFood.setLayoutManager(new LinearLayoutManager(getContext()));
        
        adapter = new FoodAdapter(food -> {
            viewModel.selectFood(food);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentDetail())
                    .addToBackStack(null)
                    .commit();
        });
        
        rvFood.setAdapter(adapter);

        viewModel.getFoods().observe(getViewLifecycleOwner(), foods -> {
            adapter.setFoodList(foods);
        });
    }
}
