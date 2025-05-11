package com.example.demo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;


@Data
@Document
@Validated
public class Recipe {
    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "Recipe title is required")
    private String title;

    @Min(value = 1, message = "Cooking time must be greater than 0")
    private int cookingTime;

    @NotBlank(message = "Recipe category is required")
    private String category;

    @NotBlank(message = "At least one ingredient is required")
    private String ingredients;

    @NotBlank(message = "At least one ingredient is required")
    private String instructions;

    public Recipe(String title, int cookingTime, String category, String ingredients, String instructions) {
        this.title = title;
        this.cookingTime = cookingTime;
        this.category = category;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    public Recipe(){
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
