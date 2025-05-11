package com.example.demo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Document(collection = "recipes")
@RestController
@RequestMapping("/api/v1/Recipe")
@Validated
public class RecipeController {

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping
    public List<Recipe> fetchAllRecipes() {
        return recipeService.getAllRecipes();
    }


    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe recipeCreated = recipeService.createRecipe(recipe);
        return new ResponseEntity<>(recipeCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Recipe> getById(@PathVariable("Id") String Id) {
        Optional<Recipe> recipe = recipeService.getById(Id);
        return recipe.map(recipe1 -> new ResponseEntity<>(recipe1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateSpecificRecipe(@PathVariable("id") String id,@Valid @RequestBody Recipe updatedRecipe) {
        Optional<Recipe> recipe = recipeService.updateSpecificRecipe(id, updatedRecipe);
        return recipe.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteById(@PathVariable("Id") String Id) {
        if (recipeService.deleteById(Id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sorted")
    public Page<Recipe> getSortedRecipe(@RequestParam @Min(0) int page, @RequestParam @Min(1) int size, @RequestParam @NotBlank String element) {
        return recipeService.getSortedRecipes(page, size, element);
    }


    @GetMapping("/filter/cooking-time")
    public ResponseEntity<List<Recipe>> searchByCookingTime(@RequestParam @NotNull int time) {
        List<Recipe> recipe = recipeService.searchByCookingTime(time);
        if (recipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }


    @GetMapping("/filter/category")
    public ResponseEntity<List<Recipe>> searchByCategory(@RequestParam @NotBlank String category) {
        List<Recipe> recipe = recipeService.searchByCategory(category);
        if (recipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }


    @GetMapping("/filter/ingredient")
    public ResponseEntity<List<Recipe>> searchByIngredients(@RequestParam @NotBlank String ingredient) {
        List<Recipe> recipe = recipeService.searchByIngredients(ingredient);
        if (recipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }


    @GetMapping("/filter/instruction")
    public ResponseEntity<List<Recipe>> searchByInstructions(@RequestParam @NotBlank String instruction) {
        List<Recipe> recipe = recipeService.searchByInstructions(instruction);
        if (recipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }
    @GetMapping("/filter/title")
    public ResponseEntity<Optional<Recipe>> searchByTitle(@RequestParam @NotBlank String title) {
        Optional<Recipe> recipe = recipeService.searchByTitle(title);
        if (recipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe,HttpStatus.OK);
    }

}
