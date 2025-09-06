package com.github.guiziin227.sistemadereceitas.repository;

import com.github.guiziin227.sistemadereceitas.entities.Ingredients;
import com.github.guiziin227.sistemadereceitas.entities.User;
import com.github.guiziin227.sistemadereceitas.entities.UserIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredient, Long> {
    Optional<UserIngredient> findByUserAndIngredient(User user, Ingredients ingredient);
}
