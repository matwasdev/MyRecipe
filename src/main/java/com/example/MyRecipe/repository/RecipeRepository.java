package com.example.MyRecipe.repository;

import com.example.MyRecipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByAuthorId(Long id);
    Optional<Recipe> findByIdAndAuthorId(Long id, Long author_id);


}
