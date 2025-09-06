package com.github.guiziin227.sistemadereceitas.service;

import com.github.guiziin227.sistemadereceitas.dtos.request.UserIngredientRequestDTO;
import com.github.guiziin227.sistemadereceitas.entities.Ingredients;
import com.github.guiziin227.sistemadereceitas.entities.User;
import com.github.guiziin227.sistemadereceitas.entities.UserIngredient;
import com.github.guiziin227.sistemadereceitas.repository.IngredientsRepository;
import com.github.guiziin227.sistemadereceitas.repository.UserIngredientRepository;
import com.github.guiziin227.sistemadereceitas.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserIngredientsService {

    Logger logger = LoggerFactory.getLogger(UserIngredientsService.class);

    private final UserIngredientRepository userIngredientRepository;
    private final IngredientsRepository ingredientsRepository;
    private final UserRepository userRepository;

    public UserIngredientsService(UserIngredientRepository userIngredientRepository, IngredientsRepository ingredientsRepository, UserRepository userRepository) {
        this.userIngredientRepository = userIngredientRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserIngredient addIngredientToUser(String userEmail, UserIngredientRequestDTO requestDTO) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o email: " + userEmail));


        Ingredients ingredient = ingredientsRepository.findById(requestDTO.ingredientId())
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));


        UserIngredient userIngredient = userIngredientRepository.findByUserAndIngredient(user, ingredient)
                .orElseGet(() -> {
                    UserIngredient newItem = new UserIngredient();
                    newItem.setUser(user);
                    newItem.setIngredient(ingredient);
                    return newItem;
                });


        userIngredient.setQuantity(requestDTO.quantity());
        userIngredient.setUnit(requestDTO.unit());

        UserIngredient savedItem = userIngredientRepository.save(userIngredient);

        logger.info("Item da despensa atualizado para o usuário: {} - Item: {} - Quantidade: {}",
                userEmail, ingredient.getDisplayName(), savedItem.getQuantity());

        return savedItem;
    }
}
