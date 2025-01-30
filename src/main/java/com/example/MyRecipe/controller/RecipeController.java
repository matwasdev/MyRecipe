package com.example.MyRecipe.controller;


import com.example.MyRecipe.model.Recipe;
import com.example.MyRecipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe) {
       String usernameLogged = SecurityContextHolder.getContext().getAuthentication().getName();
       Recipe createdRecipe = recipeService.createRecipe(usernameLogged, recipe);
       if(createdRecipe == null)
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Recipe NOT CREATED - something went wrong");
       return ResponseEntity.status(HttpStatus.OK).body("Recipe CREATED successfully");
    }

    @GetMapping("/save/{id}")
    public ResponseEntity<?> saveRecipe(@PathVariable Long id) {
        String usernameLogged = SecurityContextHolder.getContext().getAuthentication().getName();
        Recipe savedRecipe = recipeService.saveRecipe(usernameLogged, id);
        if(savedRecipe == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe NOT FOUND");
        return ResponseEntity.status(HttpStatus.OK).body("Recipe SAVED successfully");
    }

//    @GetMapping("/created/list")
//    public ResponseEntity<?> getCreatedRecipes() {
//        String usernameLogged = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<Recipe> createdRecipes = recipeService.getCreatedRecipes(usernameLogged);
//        if(createdRecipes == null)
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You have not created any recipe yet");
//        return ResponseEntity.status(HttpStatus.OK).body(createdRecipes);
//    }

    @GetMapping("/created/{id}")
    public ResponseEntity<?> getCreatedRecipe(@PathVariable Long id) {
        String usernameLogged = SecurityContextHolder.getContext().getAuthentication().getName();
        Recipe createdRecipe = recipeService.getCreatedRecipe(id , usernameLogged);
        if(createdRecipe == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such recipe with id: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(createdRecipe);
    }



}
