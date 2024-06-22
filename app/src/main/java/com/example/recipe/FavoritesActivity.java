package com.example.recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavorites;
    private List<Recipe> favoriteRecipes;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);

        // Initialize RecyclerView
        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this));
        favoriteRecipes = new ArrayList<>();
        adapter = new RecipeAdapter(favoriteRecipes);
        recyclerViewFavorites.setAdapter(adapter);

        // Load favorite recipes
        loadFavoriteRecipes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_home) {
            startActivity(new Intent(FavoritesActivity.this, MainActivity.class));
            return true;
        } else if (id == R.id.Favourites) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFavoriteRecipes() {
        // Retrieve favorite recipes from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(DetailActivity.PREFS_NAME, MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().endsWith("_name")) {
                // Retrieve recipe details
                String name = sharedPreferences.getString(entry.getKey(), "");
                String category = sharedPreferences.getString(entry.getKey().replace("_name", "_category"), "");
                String duration = sharedPreferences.getString(entry.getKey().replace("_name", "_duration"), "");
                String difficulty = sharedPreferences.getString(entry.getKey().replace("_name", "_difficulty"), "");
                String description = sharedPreferences.getString(entry.getKey().replace("_name", "_description"), "");
                ArrayList<String> ingredients = new ArrayList<>(sharedPreferences.getStringSet(entry.getKey().replace("_name", "_ingredients"), new HashSet<>())); // Add this line
                int imageResourceId = sharedPreferences.getInt(entry.getKey().replace("_name", "_image_resource_id"),0); // Add this line

                Recipe recipe = new Recipe(name, category, duration, difficulty, description, ingredients, imageResourceId); // Update constructor call
                favoriteRecipes.add(recipe);
            }
        }

        // Notify adapter that data set has changed
        adapter.notifyDataSetChanged();
    }
}
