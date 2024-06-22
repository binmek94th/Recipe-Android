package com.example.recipe;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private String category;
    private String duration;
    private String difficulty;
    private String description;
    private ArrayList<String> ingredients;
    private int imageResourceId; // Add this line



    // Constructor
    public Recipe(String name, String category, String difficulty, String duration, String description, ArrayList<String> ingredients, int imageResourceId) {
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.duration = duration;
        this.description = description;
        this.ingredients = ingredients;
        this.imageResourceId = imageResourceId; // Add this line
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

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }
    public int getImageResourceId() {
        return imageResourceId;
    }
    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
