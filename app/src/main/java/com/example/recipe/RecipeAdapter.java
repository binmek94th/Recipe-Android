package com.example.recipe;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;

    // Constructor
    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    // ViewHolder class
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public ImageView recipeImageView; // Add this line
        public TextView nameTextView;
        public TextView difficultyTextView;
        public TextView durationTextView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeImageView = itemView.findViewById(R.id.recipe_image); // Add this line
            nameTextView = itemView.findViewById(R.id.recipe_name);
            difficultyTextView = itemView.findViewById(R.id.difficulty);
            durationTextView = itemView.findViewById(R.id.duration);
        }
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.recipeImageView.setImageResource(recipe.getImageResourceId()); // Add this line
        holder.nameTextView.setText(recipe.getName());
        holder.difficultyTextView.setText(recipe.getDifficulty());
        holder.durationTextView.setText(recipe.getDuration());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_NAME, recipe.getName());
            intent.putExtra(DetailActivity.EXTRA_CATEGORY, recipe.getCategory());
            intent.putExtra(DetailActivity.EXTRA_DURATION, recipe.getDuration());
            intent.putExtra(DetailActivity.EXTRA_DIFFICULTY, recipe.getDifficulty());
            intent.putExtra(DetailActivity.EXTRA_DESCRIPTION, recipe.getDescription());
            intent.putStringArrayListExtra(DetailActivity.EXTRA_INGREDIENTS, recipe.getIngredients());
            intent.putExtra(DetailActivity.EXTRA_IMAGE_RESOURCE_ID, recipe.getImageResourceId()); // Add this line
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    // Setter method to update data set
    public void setRecipeList(List<Recipe> recipes) {
        this.recipeList = recipes;
    }
}
