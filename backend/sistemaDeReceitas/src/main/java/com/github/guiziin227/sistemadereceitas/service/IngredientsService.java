package com.github.guiziin227.sistemadereceitas.service;

import com.github.guiziin227.sistemadereceitas.dtos.request.IngredientsRequestDTO;
import com.github.guiziin227.sistemadereceitas.dtos.response.IngredientsResponseDTO;
import com.github.guiziin227.sistemadereceitas.entities.Ingredients;
import com.github.guiziin227.sistemadereceitas.repository.IngredientsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientsService {

    Logger logger = LoggerFactory.getLogger(IngredientsService.class);

    private final IngredientsRepository ingredientsRepository;

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public IngredientsResponseDTO findOrCreate(IngredientsRequestDTO displayName) {
        Ingredients tempIngredient = new Ingredients(displayName.displayName());
        String normalizedName = tempIngredient.getNormalizedName();


        Ingredients ingredient = ingredientsRepository.findByNormalizedName(normalizedName)
                .orElseGet(() -> ingredientsRepository.save(tempIngredient));

        return new IngredientsResponseDTO(ingredient.getDisplayName(), ingredient.getNormalizedName());
    }

}
