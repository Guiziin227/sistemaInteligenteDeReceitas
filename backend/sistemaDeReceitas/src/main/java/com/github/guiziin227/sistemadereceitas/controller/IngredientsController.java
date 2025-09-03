package com.github.guiziin227.sistemadereceitas.controller;

import com.github.guiziin227.sistemadereceitas.dtos.request.IngredientsRequestDTO;
import com.github.guiziin227.sistemadereceitas.dtos.response.IngredientsResponseDTO;
import com.github.guiziin227.sistemadereceitas.service.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(final IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    public ResponseEntity<IngredientsResponseDTO> create(@RequestBody IngredientsRequestDTO ingredientsRequestDTO) {
        var ingredientResp = ingredientsService.findOrCreate(ingredientsRequestDTO);
        return ResponseEntity.ok(ingredientResp);
    }

}
