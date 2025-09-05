package com.github.guiziin227.sistemadereceitas.dtos.request;

import java.math.BigDecimal;

public record UserIngredientRequest(
        Long ingredientId,
        BigDecimal quantity,
        String unit
) {
}
