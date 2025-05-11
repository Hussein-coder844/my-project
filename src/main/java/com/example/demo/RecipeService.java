package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private Object Id;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.insert(recipe);
    }
    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }
    public Optional<Recipe> getById(String Id){
        return recipeRepository.findById(Id);
    }
    public Optional<Recipe> updateSpecificRecipe(String id, Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(existingRecipe -> {
            existingRecipe.setTitle(updatedRecipe.getTitle());
            existingRecipe.setCookingTime(updatedRecipe.getCookingTime());
            existingRecipe.setCategory(updatedRecipe.getCategory());
            existingRecipe.setIngredients(updatedRecipe.getIngredients());
            existingRecipe.setInstructions(updatedRecipe.getInstructions());
            return recipeRepository.save(existingRecipe);
        });
    }

    public boolean deleteById(String Id){
        Optional<Recipe> optionalRecipe=recipeRepository.findById(Id);
        if(optionalRecipe.isPresent()){
            recipeRepository.deleteById(Id);
            return true;
        }
        else
        {
            return false;
        }
    }
    public Page<Recipe> getSortedRecipes(int page,int size,String element){
    Pageable pageable=PageRequest.of(page,size,Sort.by(element));
    return recipeRepository.findAll(pageable);
    }
    public List<Recipe> searchByCookingTime(int time){
    return recipeRepository.findByCookingTime(time);
    }
    List<Recipe> searchByCategory(String category){
    return recipeRepository.findByCategory(category);
    }
    List<Recipe> searchByIngredients(String ingredient){
    return recipeRepository.findByIngredientsContainingIgnoreCase(ingredient);
    }
    List<Recipe> searchByInstructions(String instruction){
    return recipeRepository.findByInstructionsContainingIgnoreCase(instruction);
    }
    Optional<Recipe> searchByTitle(String title){
    return recipeRepository.findByTitle(title);
    }

    public void display(List<Recipe> recipes)
    {
        for (Recipe r : recipes)
        {
            System.out.println("Recipe id is: " + r.getId());
            System.out.println("Recipe title is: " + r.getTitle());
            System.out.println("Recipe category is: " + r.getCategory());
            System.out.println("Recipe cooking time is: " + r.getCookingTime());
            System.out.println("Recipe ingredients are: " + r.getIngredients());
            System.out.println("Recipe instructions are: " + r.getInstructions());
            System.out.println("------------------------------");
        }
    }

    public void display_recipe(Recipe recipe)
    {
        System.out.println("Recipe id is: " + recipe.getId());
        System.out.println("Recipe title is: " + recipe.getTitle());
        System.out.println("Recipe category is: " + recipe.getCategory());
        System.out.println("Recipe cooking time is: " + recipe.getCookingTime());
        System.out.println("Recipe ingredients are: " + recipe.getIngredients());
        System.out.println("Recipe instructions are: " + recipe.getInstructions());
        System.out.println("------------------------------");
    }
}
