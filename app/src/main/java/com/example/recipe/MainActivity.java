package com.example.recipe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recipes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recipeList = getRecipes(); // Replace with your method to fetch recipes
        adapter = new RecipeAdapter(recipeList);
        recyclerView.setAdapter(adapter);
        filterRecipes(0);

        // Initialize TabLayout and set listener
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Filter recipes based on selected tab
                filterRecipes(tab.getPosition()); // Assuming position corresponds to category index
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    // Method to filter recipes based on selected category
    @SuppressLint("NotifyDataSetChanged")
    private void filterRecipes(int categoryIndex) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        String selectedCategory = getCategoryName(categoryIndex);

        for (Recipe recipe : recipeList) {
            if (recipe.getCategory().equalsIgnoreCase(selectedCategory)) {
                filteredRecipes.add(recipe);
            }
        }

        // Update RecyclerView with filtered recipes
        adapter.setRecipeList(filteredRecipes);
        adapter.notifyDataSetChanged();
    }

    // Method to get recipe data (replace with your actual data retrieval method)
    private List<Recipe> getRecipes() {
        // Dummy data for demonstration
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Blueberry Pancake", "Breakfast", "Easy", "20 min", new ArrayList<String>(), R.drawable.image));
        recipes.add(new Recipe("Hercules Waffle", "Breakfast", "Medium", "12 min", new ArrayList<String>(), R.drawable.image));
        recipes.add(new Recipe("Caesar Salad", "Lunch", "Easy", "30 min", new ArrayList<String>(), R.drawable.image));
        recipes.add(new Recipe("Beef Stew", "Lunch", "Hard", "60 min", new ArrayList<String>(), R.drawable.image));
        recipes.add(new Recipe("Grilled Salmon", "Dinner", "Medium", "40 min", new ArrayList<String>(), R.drawable.image));
        recipes.add(new Recipe("Spaghetti Carbonara", "Dinner", "Hard", "90 min", new ArrayList<String>(), R.drawable.image));
        return recipes;
    }

    // Method to get category name based on index (replace with your actual method if needed)
    private String getCategoryName(int index) {
        switch (index) {
            case 0:
                return "Breakfast";
            case 1:
                return "Lunch";
            case 2:
                return "Dinner";
            default:
                return "";
        }
    }
}
