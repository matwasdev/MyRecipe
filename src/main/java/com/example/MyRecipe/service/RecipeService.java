package com.example.MyRecipe.service;

import com.example.MyRecipe.model.MyUser;
import com.example.MyRecipe.model.Recipe;
import com.example.MyRecipe.repository.MyUserRepository;
import com.example.MyRecipe.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {


    RecipeRepository recipeRepository;
    MyUserRepository myUserRepository;

    public RecipeService(RecipeRepository recipeRepository, MyUserRepository myUserRepository) {
        this.recipeRepository = recipeRepository;
        this.myUserRepository = myUserRepository;
    }

    public Recipe createRecipe(String usernameLogged, Recipe recipe) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(usernameLogged);
        if(myUser.isEmpty())
            return null;

        recipe.setAuthor(myUser.get());
        return recipeRepository.save(recipe);
    }


    @Transactional /// essential here
    public Recipe saveRecipe(String usernameLogged, Long recipeId) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(usernameLogged);
        if(myUser.isEmpty())
            return null;

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if(recipe.isEmpty())
            return null;

        myUser.get().getSavedRecipes().add(recipe.get());
        recipe.get().getUsersWhoSaved().add(myUser.get());
        return recipe.get();
    }

    public List<Recipe> getCreatedRecipes(String usernameLogged) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(usernameLogged);
        if(myUser.isEmpty())
            return null;

        return recipeRepository.findByAuthorId(myUser.get().getId());
    }

    public Recipe getCreatedRecipe(Long recipeId, String usernameLogged) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(usernameLogged);
        if(myUser.isEmpty())
            return null;

        Optional<Recipe> recipe = recipeRepository.findByIdAndAuthorId(recipeId,myUser.get().getId());
        return recipe.orElse(null);
    }

}
