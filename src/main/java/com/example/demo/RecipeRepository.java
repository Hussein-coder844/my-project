package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends MongoRepository<Recipe,String> {
    Optional<Recipe> findByTitle(String title);
    List<Recipe> findByCookingTime(int time);
    List<Recipe> findByCategory(String category);
    List<Recipe> findByIngredientsContainingIgnoreCase(String ingredient);
    List<Recipe> findByInstructionsContainingIgnoreCase(String instruction);
}
