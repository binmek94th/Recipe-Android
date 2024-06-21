package com.example.recipe;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_DURATION = "duration";
    public static final String EXTRA_DIFFICULTY = "difficulty";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_INGREDIENTS = "ingredients";

    private TextView nameTextView;
    private TextView categoryTextView;
    private TextView durationTextView;
    private TextView difficultyTextView;
    private TextView descriptionTextView;
    private TextView ingredientsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize TextViews
        nameTextView = findViewById(R.id.detail_name);
        categoryTextView = findViewById(R.id.detail_category);
        durationTextView = findViewById(R.id.detail_duration);
        difficultyTextView = findViewById(R.id.detail_difficulty);
        descriptionTextView = findViewById(R.id.detail_description);
        ingredientsTextView = findViewById(R.id.detail_ingredients);

        // Get data from Intent
        String name = getIntent().getStringExtra(EXTRA_NAME);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        String duration = getIntent().getStringExtra(EXTRA_DURATION);
        String difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra(EXTRA_INGREDIENTS);

        // Set data to TextViews
        nameTextView.setText(name);
        categoryTextView.setText(category);
        durationTextView.setText(duration);
        difficultyTextView.setText(difficulty);
        descriptionTextView.setText(description);
        ingredientsTextView.setText(String.join(", ", ingredients));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
