package com.github.guiziin227.sistemadereceitas.repository;

import com.github.guiziin227.sistemadereceitas.entities.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    Optional<Ingredients> findByNormalizedName(String normalizedName);
}
