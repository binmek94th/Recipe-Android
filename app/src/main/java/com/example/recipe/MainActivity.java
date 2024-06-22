package com.example.recipe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Handling menu item clicks using if-else statements
            if (item.getItemId() == R.id.navigation_home) {
                // Open Home or MainActivity
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;
            } else if (item.getItemId() == R.id.Favourites) {
                // Open FavoritesActivity
                startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
                return true;
            }

            return false;
        });

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
        recipes.add(new Recipe("Blueberry Pancake", "Breakfast", "Easy", "20 min", "A delicious blueberry pancake. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse consequat, turpis vel malesuada sodales, massa justo egestas risus, in accumsan lectus augue in dui. Aliquam blandit consectetur elit, in egestas leo auctor a", new ArrayList<>(List.of("Blueberries", "Pancake Mix", "Milk")), R.drawable.pancake));
        recipes.add(new Recipe("Hercules Wrap", "Lunch", "Medium", "12 min", "A nutritious Hercules wrap. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse consequat, turpis vel malesuada sodales, massa justo egestas risus, in accumsan lectus augue in dui. Aliquam blandit consectetur elit, in egestas leo auctor a", new ArrayList<>(List.of("Wrap", "Chicken", "Vegetables")), R.drawable.download));
        recipes.add(new Recipe("Spaghetti Carbonara", "Dinner", "Hard", "30 min", "Classic Italian pasta dish. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse consequat, turpis vel malesuada sodales, massa justo egestas risus, in accumsan lectus augue in dui. Aliquam blandit consectetur elit, in egestas leo auctor a", new ArrayList<>(List.of("Spaghetti", "Eggs", "Pancetta")), R.drawable.download1));
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
