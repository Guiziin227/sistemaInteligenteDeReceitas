package com.github.guiziin227.sistemadereceitas.dtos.response;

import java.util.List;

public record UserResponseDTO(
        String firstName,
        String email,
        List<String> role
) {
}
