package com.tamdao.adr58_daoductam_day7;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoodViewModel extends ViewModel {
    private final MutableLiveData<List<Food>> foods = new MutableLiveData<>();
    private final MutableLiveData<Food> selectedFood = new MutableLiveData<>();

    public FoodViewModel() {
        loadFoods();
    }

    private void loadFoods() {
        List<Food> list = new ArrayList<>();
        list.add(new Food(1, "Telor ceplok", "Delicious fried egg with simple seasoning. Perfect for a quick breakfast.", "9 mins", "Easy", "1 person", 4.3, R.drawable.telor_ceplok));
        list.add(new Food(2, "Salad vegetarian", "Fresh vegetable salad with olive oil and seasonal greens.", "10 mins", "Easy", "1 person", 4.3, R.drawable.salad_vegetarian));
        list.add(new Food(3, "Toast with egg", "Crispy toast topped with a perfectly cooked egg and herbs.", "30 mins", "Medium", "1 person", 4.3, R.drawable.toast_with_egg));
        list.add(new Food(4, "Salmon sauce", "Fresh salmon served with a special creamy sauce and lemon.", "45 mins", "Hard", "2 people", 4.3, R.drawable.salmon_sauce));
        list.add(new Food(5, "Mashroom soup", "Creamy and rich mushroom soup with fresh herbs.", "15 mins", "Easy", "2 people", 4.3, R.drawable.mashroom_soup));
        list.add(new Food(6, "Gourmet dessert", "Elegant layered mousse cake with fresh fruit toppings, including blueberries, kiwi.", "25 mins", "Medium", "4 people", 4.3, R.drawable.gourmet_desssert));
        foods.setValue(list);
    }

    public LiveData<List<Food>> getFoods() {
        return foods;
    }

    public void selectFood(Food food) {
        selectedFood.setValue(food);
    }

    public LiveData<Food> getSelectedFood() {
        return selectedFood;
    }

    public void toggleFavorite(int foodId) {
        List<Food> currentList = foods.getValue();
        if (currentList != null) {
            for (Food food : currentList) {
                if (food.getId() == foodId) {
                    food.setFavorite(!food.isFavorite());
                    break;
                }
            }
            foods.setValue(new ArrayList<>(currentList));
            
            Food selected = selectedFood.getValue();
            if (selected != null && selected.getId() == foodId) {
                selectedFood.setValue(selected);
            }
        }
    }
}
