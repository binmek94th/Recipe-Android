package com.example.recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_DURATION = "duration";
    public static final String EXTRA_DIFFICULTY = "difficulty";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_INGREDIENTS = "ingredients";
    public static final String EXTRA_IMAGE_RESOURCE_ID = "image_resource_id"; // Add this line

    static String PREFS_NAME = "MyFavourites";

    private ImageView detailImageView; // Add this line
    private TextView nameTextView;
    private TextView categoryTextView;
    private TextView durationTextView;
    private TextView difficultyTextView;
    private TextView descriptionTextView;
    private TextView ingredientsTextView;
    private Button favoriteButton;

    // Constants for menu IDs
    private static final int MENU_HOME = R.id.navigation_home;
    private static final int MENU_FAVORITES = R.id.Favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize ImageView, TextViews, and Button
        detailImageView = findViewById(R.id.imageView); // Add this line
        nameTextView = findViewById(R.id.detail_name);
        categoryTextView = findViewById(R.id.detail_category);
        durationTextView = findViewById(R.id.detail_duration);
        difficultyTextView = findViewById(R.id.detail_difficulty);
        descriptionTextView = findViewById(R.id.detail_description);
        ingredientsTextView = findViewById(R.id.detail_ingredients);
        favoriteButton = findViewById(R.id.button_favorite);

        // Get data from Intent
        String name = getIntent().getStringExtra(EXTRA_NAME);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        String duration = getIntent().getStringExtra(EXTRA_DURATION);
        String difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra(EXTRA_INGREDIENTS);
        int imageResourceId = getIntent().getIntExtra(EXTRA_IMAGE_RESOURCE_ID, -1); // Add this line

        // Set data to ImageView and TextViews
        if (imageResourceId != -1) {
            detailImageView.setImageResource(imageResourceId); // Add this line
        }
        nameTextView.setText(name);
        categoryTextView.setText(category);
        durationTextView.setText(duration);
        difficultyTextView.setText(difficulty);
        descriptionTextView.setText(description);
        ingredientsTextView.setText(String.join(", ", ingredients));

        // Set click listener for favorite button
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save recipe to favorites
                saveToFavorites(name, category, duration, difficulty, description, ingredients, imageResourceId); // Add imageResourceId
            }
        });
    }

    // Method to save recipe details to SharedPreferences as favorites
    private void saveToFavorites(String name, String category, String duration, String difficulty, String description, ArrayList<String> ingredients, int imageResourceId) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Construct a unique key based on recipe name for saving
        String key = name.replaceAll("\\s+", "_").toLowerCase(); // Replace spaces and make lowercase

        // Save recipe details using the constructed key
        editor.putString(key + "_name", name);
        editor.putString(key + "_category", category);
        editor.putString(key + "_duration", duration);
        editor.putString(key + "_difficulty", difficulty);
        editor.putString(key + "_description", description);
        editor.putStringSet(key + "_ingredients", new HashSet<>(ingredients));
        editor.putInt(key + "_image_resource_id", imageResourceId); // Add this line

        // Apply changes
        editor.apply();
        Toast.makeText(DetailActivity.this, "Recipe saved to favorites!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
