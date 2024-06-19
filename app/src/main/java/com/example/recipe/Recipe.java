package com.example.recipe;
public class Recipe {
    private String name;
    private String category;
    private String duration;
    private String difficulty;

    // Constructor
    public Recipe(String name, String category, String difficulty, String duration) {
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.duration = duration;
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
}
