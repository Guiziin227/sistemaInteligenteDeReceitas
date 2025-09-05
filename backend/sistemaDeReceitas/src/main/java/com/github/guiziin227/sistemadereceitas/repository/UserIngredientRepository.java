package com.github.guiziin227.sistemadereceitas.repository;

import com.github.guiziin227.sistemadereceitas.entities.UserIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredient, Long> {
}
