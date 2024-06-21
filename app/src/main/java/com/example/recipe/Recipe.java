package com.example.recipe;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private String category;
    private String duration;
    private String difficulty;
    private String description;
    private ArrayList<String> ingredients;
    private int imageResId; // New field for the image resource ID

    // Constructor
    public Recipe(String name, String category, String difficulty, String duration, ArrayList<String> ingredients, int imageResId) {
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.duration = duration;
        this.ingredients = ingredients;
        this.imageResId = imageResId; // Initialize the new field
    }

    public String getDuration() {
        return duration;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public int getImageResId() {
        return imageResId; // Getter for the image resource ID
    }
}
