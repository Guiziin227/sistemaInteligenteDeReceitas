package com.github.guiziin227.sistemadereceitas.dtos.request;

import java.math.BigDecimal;

public record UserIngredientRequestDTO(
        Long ingredientId,
        BigDecimal quantity,
        String unit
) {
}
