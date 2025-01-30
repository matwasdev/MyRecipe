package com.example.MyRecipe.controller;

import com.example.MyRecipe.model.MyUser;
import com.example.MyRecipe.model.Recipe;
import com.example.MyRecipe.repository.MyUserRepository;
import com.example.MyRecipe.repository.RecipeRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CreatedRecipeController {

    private final RecipeRepository recipeRepository;
    private final MyUserRepository myUserRepository;

    public CreatedRecipeController(RecipeRepository recipeRepository, MyUserRepository myUserRepository) {
        this.recipeRepository = recipeRepository;
        this.myUserRepository = myUserRepository;
    }

    @GetMapping("/recipe/created/list")
    public String showCreatedRecipes(Model model) {
        String userLogged =  SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<MyUser> myUser = myUserRepository.findByUsername(userLogged);

        List<Recipe> createdRecipes = recipeRepository.findByAuthorId(myUser.get().getId());

        model.addAttribute("recipes", createdRecipes);

        return "created-recipes";
    }

}
