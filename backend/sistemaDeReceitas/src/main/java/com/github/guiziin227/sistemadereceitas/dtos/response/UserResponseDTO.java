package com.github.guiziin227.sistemadereceitas.dtos.response;

import com.github.guiziin227.sistemadereceitas.entities.Role;

public record UserResponseDTO(
        String firstName,
        String email,
        Role role
) {
}
