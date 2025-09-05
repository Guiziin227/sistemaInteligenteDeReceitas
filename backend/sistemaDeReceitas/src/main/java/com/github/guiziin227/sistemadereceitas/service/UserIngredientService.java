package com.github.guiziin227.sistemadereceitas.service;

import com.github.guiziin227.sistemadereceitas.repository.UserIngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserIngredientService {

    Logger logger = LoggerFactory.getLogger(UserIngredientService.class);

    private final UserIngredientRepository userIngredientRepository;

    public UserIngredientService(UserIngredientRepository userIngredientRepository) {
        this.userIngredientRepository = userIngredientRepository;
    }

    public void create(R) {
        // Implement service methods here
    }

}
